package com.lal.mall.service;

import com.lal.mall.entity.Address;

import java.util.List;

public interface AddressService {
    boolean add(Address address);

    boolean update(Address address);

    List<Address> getList(String address_name, String address_regionId);

    Address get(String address_areaId);

    List<Address> getRoot();
}
