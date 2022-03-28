package com.ms.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelWriter;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

public class ExcelUtil {

    public static void exporExcel(List list, String fileName, HttpServletResponse response) throws IOException {
        // 2. 写excel
        ExcelWriter writer = cn.hutool.poi.excel.ExcelUtil.getWriter(true);
        writer.write(list, true);

        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        String file = URLEncoder.encode(fileName, "UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + file + ".xlsx");

        ServletOutputStream out = response.getOutputStream();
        writer.flush(out, true);
        writer.close();
        IoUtil.close(System.out);
    }

}
