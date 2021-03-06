package com.tduck.cloud.project.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.map.MapUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import cn.hutool.http.HttpUtil;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Sets;
import com.tduck.cloud.common.constant.CommonConstants;
import com.tduck.cloud.common.entity.BaseEntity;
import com.tduck.cloud.common.exception.BaseException;
import com.tduck.cloud.common.util.AddressUtils;
import com.tduck.cloud.common.util.AsyncProcessUtils;
import com.tduck.cloud.common.util.RedisUtils;
import com.tduck.cloud.common.util.Result;
import com.tduck.cloud.project.entity.Nine;
import com.tduck.cloud.project.entity.UserProjectItemEntity;
import com.tduck.cloud.project.entity.UserProjectResultEntity;
import com.tduck.cloud.project.entity.enums.ProjectItemTypeEnum;
import com.tduck.cloud.project.entity.struct.UploadResultStruct;
import com.tduck.cloud.project.mapper.UserProjectResultMapper;
import com.tduck.cloud.project.request.QueryProjectResultRequest;
import com.tduck.cloud.project.service.UserProjectItemService;
import com.tduck.cloud.project.service.UserProjectResultService;
import com.tduck.cloud.project.vo.ExportProjectResultVO;
import com.tduck.cloud.storage.cloud.OssStorageFactory;
import com.tduck.cloud.storage.util.StorageUtils;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import static com.tduck.cloud.project.constant.ProjectRedisKeyConstants.PROJECT_RESULT_NUMBER;

/**
 * ???????????????(ProjectResult)??????????????????
 *
 * @author smalljop
 * @since 2020-11-23 14:09:22
 */
@Service("projectResultService")
@RequiredArgsConstructor
public class UserProjectResultServiceImpl extends ServiceImpl<UserProjectResultMapper, UserProjectResultEntity> implements UserProjectResultService {

    private final UserProjectItemService userProjectItemService;
    private final RedisUtils redisUtils;
    private final UserProjectItemService projectItemService;

    /**
     * ??????????????????
     */
    private final Set<ProjectItemTypeEnum> needProcessItemTypeSet =
            Sets.newHashSet(ProjectItemTypeEnum.SELECT, ProjectItemTypeEnum.RADIO, ProjectItemTypeEnum.CHECKBOX, ProjectItemTypeEnum.CASCADER);


    @Override
    public void saveProjectResult(UserProjectResultEntity entity) {
        String projectKey = entity.getProjectKey();
        entity.setSerialNumber(redisUtils.incr(StrUtil.format(PROJECT_RESULT_NUMBER, projectKey), CommonConstants.ConstantNumber.ONE));
        entity.setSubmitAddress(AddressUtils.getRealAddressByIP(entity.getSubmitRequestIp()));
        this.save(entity);

    }

    @Override
    public Page listByQueryConditions(QueryProjectResultRequest request) {
        LambdaQueryWrapper<UserProjectResultEntity> lambdaQueryWrapper = Wrappers.<UserProjectResultEntity>lambdaQuery()
                .eq(UserProjectResultEntity::getProjectKey, request.getProjectKey())
                .le(ObjectUtil.isNotNull(request.getEndDateTime()), UserProjectResultEntity::getCreateTime, request.getEndDateTime())
                .ge(ObjectUtil.isNotNull(request.getBeginDateTime()), UserProjectResultEntity::getCreateTime, request.getBeginDateTime())
                .orderByDesc(BaseEntity::getCreateTime);
        if (ObjectUtil.isNotNull(request.getExtParamsMap())) {
            request.getExtParamsMap().keySet().forEach(item -> {
                String comparison = MapUtil.getStr(request.getExtComparisonsMap(), item);
                QueryProjectResultRequest.QueryComparison queryComparison = QueryProjectResultRequest.QueryComparison.get(comparison);
                Object value = request.getExtParamsMap().get(item);
                if (queryComparison == QueryProjectResultRequest.QueryComparison.LIKE) {
                    value = "'%" + value + "%'";
                }
                lambdaQueryWrapper.apply(StrUtil.format("original_data ->'$.{}' {} {} ", item, queryComparison.getKey(), value));
            });
        }
        // ??????page??????
        Page page = page(request.toMybatisPage(), lambdaQueryWrapper);
        List<UserProjectResultEntity> records = page.getRecords();

        List<UserProjectItemEntity> itemEntityList = projectItemService.list(Wrappers.<UserProjectItemEntity>lambdaQuery()
                .eq(UserProjectItemEntity::getProjectKey, request.getProjectKey())
                .orderByAsc(UserProjectItemEntity::getSort)
        );
        int size = itemEntityList.size();
        AtomicInteger init = new AtomicInteger(100);
        String pre = "field";
        records.forEach(userProjectResultEntity -> {
            // {"field101": "???", "field102": "???", "field103": "???", "field104": "???", "field101other": "", "field102other": "", "field103other": "", "field104other": ""}
            Map<String, Object> processData = userProjectResultEntity.getProcessData();
            Nine nine = new Nine();
            for (int i = 0; i < size; i++) {
                init.addAndGet(1);
                String isCheck = (String) processData.get(pre + (init));
                String answer = itemEntityList.get(i).getAnswer();
                if (StringUtils.isNotBlank(isCheck) && StringUtils.isNotBlank(answer)) {
                    calcNine(nine, isCheck, answer);
                }
            }
            userProjectResultEntity.setNine(nine);
            ArrayList<Double> objects = new ArrayList<>();
            objects.add(nine.getOneScale());
            objects.add(nine.getTwoScale());
            objects.add(nine.getThreeScale());

            objects.add(nine.getFourScale());
            objects.add(nine.getFiveScale());
            objects.add(nine.getSixScale());

            objects.add(nine.getSevenScale());
            objects.add(nine.getEightScale());
            objects.add(nine.getNineScale());
            userProjectResultEntity.setNineArray(objects);

        });
        return page;
    }

    private void calcNine(Nine nine, String isCheck, String answer) {
        if (answer.contains("??????")) {
            nine.setOneTotal(nine.getOneTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setOneSelect(nine.getOneSelect() + 1);
            }
        } else if (answer.contains("??????")) {
            nine.setTwoTotal(nine.getTwoTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setTwoSelect(nine.getTwoSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setThreeTotal(nine.getThreeTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setThreeSelect(nine.getThreeSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setFourTotal(nine.getFourTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setFourSelect(nine.getFourSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setFiveTotal(nine.getFiveTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setFiveSelect(nine.getFiveSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setSixTotal(nine.getSixTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setSixSelect(nine.getSixSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setSevenTotal(nine.getSevenTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setSevenSelect(nine.getSevenSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setEightTotal(nine.getEightTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setEightSelect(nine.getEightSelect()+ 1);
            }
        } else if (answer.contains("??????")) {
            nine.setNineTotal(nine.getNineTotal() + 1);
            if (isCheck.contains("???")) {
                nine.setNineSelect(nine.getNineSelect()+ 1);
            }
        }
    }

    @Override
    public ExportProjectResultVO exportProjectResult(QueryProjectResultRequest request) {
        //????????????
        String projectKey = request.getProjectKey();
        List<UserProjectItemEntity> userProjectItemEntityList = userProjectItemService.listByProjectKey(projectKey);
        // excel ?????????
        List<ExportProjectResultVO.ExcelHeader> titleList = userProjectItemEntityList.stream()
                .map(item -> new ExportProjectResultVO.ExcelHeader(item.getFormItemId().toString(), item.getLabel()))
                .collect(Collectors.toList());
        //??????
        List<UserProjectResultEntity> resultEntityList = this.list(Wrappers.<UserProjectResultEntity>lambdaQuery()
                .eq(UserProjectResultEntity::getProjectKey, request.getProjectKey())
                .le(ObjectUtil.isNotNull(request.getEndDateTime()), UserProjectResultEntity::getCreateTime, request.getEndDateTime())
                .ge(ObjectUtil.isNotNull(request.getBeginDateTime()), UserProjectResultEntity::getCreateTime, request.getBeginDateTime())
                .orderByDesc(BaseEntity::getCreateTime));
        if (CollectionUtil.isEmpty(resultEntityList)) {
            throw new BaseException("???????????????????????????????????????");
        }
        // ?????????????????????????????????????????? ???????????????????????????????????????????????? ??????????????????????????????
        AtomicReference<Boolean> isFillRow = new AtomicReference<>(false);
        List<Map<String, Object>> resultList = resultEntityList.stream().map(item -> {
            Map<String, Object> processData = item.getProcessData();
            if (!isFillRow.get()) {
                titleList.stream()
                        .map(ExportProjectResultVO.ExcelHeader::getFieldKey).collect(Collectors.toList()).forEach(key -> {
                            if (!processData.containsKey(key))
                                processData.put(key, StrUtil.EMPTY);
                        });
                isFillRow.set(true);
            }
            Iterator<String> iterator = processData.keySet().iterator();
            while (iterator.hasNext()) {
                String key = iterator.next();
                List<String> titleStrList = titleList.stream()
                        .map(ExportProjectResultVO.ExcelHeader::getFieldKey).collect(Collectors.toList());
                // ???????????????????????????????????? ????????????????????????excel????????????
                if (!titleStrList.contains(key)) {
                    iterator.remove();
                }
            }
            processData.put(BaseEntity.Fields.createTime, item.getCreateTime());
            processData.put(UserProjectResultEntity.Fields.submitAddress, item.getSubmitAddress());
            return processData;
        }).collect(Collectors.toList());
        List<ExportProjectResultVO.ExcelHeader> allHeaderList = new ArrayList<>();
        allHeaderList.addAll(ExportProjectResultVO.DEFAULT_HEADER_NAME);
        allHeaderList.addAll(titleList);
        return new ExportProjectResultVO(allHeaderList, resultList);
    }

    /**
     * ??????????????????????????????
     *
     * @param request
     * @return
     */
    @Override
    public Result downloadProjectResultFile(QueryProjectResultRequest request) {
        List<UserProjectItemEntity> userProjectItemEntityList = userProjectItemService.list(Wrappers.<UserProjectItemEntity>lambdaQuery()
                .eq(UserProjectItemEntity::getProjectKey, request.getProjectKey())
                .eq(UserProjectItemEntity::getType, ProjectItemTypeEnum.UPLOAD));
        String filed = "field";
        // ????????????????????????
        ApplicationHome home = new ApplicationHome(getClass());
        File path = home.getSource();
        String uuid = IdUtil.fastSimpleUUID();
        StringBuffer downloadPath = new StringBuffer(path.getParentFile().toString()).append(File.separator).append(uuid).append(File.separator);
        System.out.println(downloadPath);
        //??????
        List<UserProjectResultEntity> resultEntityList = this.list(Wrappers.<UserProjectResultEntity>lambdaQuery()
                .eq(UserProjectResultEntity::getProjectKey, request.getProjectKey())
                .le(ObjectUtil.isNotNull(request.getEndDateTime()), UserProjectResultEntity::getCreateTime, request.getEndDateTime())
                .ge(ObjectUtil.isNotNull(request.getBeginDateTime()), UserProjectResultEntity::getCreateTime, request.getBeginDateTime())
                .orderByDesc(BaseEntity::getCreateTime));
        if (CollectionUtil.isEmpty(resultEntityList) || CollectionUtil.isEmpty(userProjectItemEntityList)) {
            return Result.failed("?????????????????????????????????");
        }

        ThreadUtil.execAsync(() -> {
            try {
                resultEntityList.forEach(result -> {
                    int index = 0;
                    userProjectItemEntityList.forEach(item -> {
                        StringBuffer tempDownloadPath = new StringBuffer(downloadPath).append(item.getFormItemId());
                        UploadResultStruct uploadResult = MapUtil.get(result.getProcessData(), filed + item.getFormItemId(), UploadResultStruct.class);
                        if (ObjectUtil.isNotNull(uploadResult) && CollectionUtil.isNotEmpty(uploadResult.getFiles())) {
                            uploadResult.getFiles().forEach(uFile -> {
                                if (StrUtil.isNotBlank(uFile.getUrl())) {
                                    File downFile = FileUtil.file(new StringBuffer(tempDownloadPath).append(File.separator)
                                            .append(result.getId()).append(CharUtil.DASHED).append(uFile.getFileName()).toString());
                                    HttpUtil.downloadFile(uFile.getUrl(), downFile);
                                }
                            });
                        }
                    });
                    AsyncProcessUtils.setProcess(uuid, ++index / resultEntityList.size() + 1);
                });
                // ????????????oss
                File zip = ZipUtil.zip(downloadPath.toString());
                String downloadUrl = OssStorageFactory.build().upload(new FileInputStream(zip), StorageUtils.generateFileName("download", ".zip"));
                AsyncProcessUtils.setProcess(uuid, downloadUrl);
                //??????????????????
                FileUtil.del(zip);
                FileUtil.del(downloadPath.toString());
            } catch (Exception e) {
                log.error("download file", e);
            }
        });
        return Result.success(uuid);
    }
}