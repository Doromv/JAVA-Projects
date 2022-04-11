package com.doromv.store.mapper;

import com.doromv.store.entity.Address;

import java.util.Date;
import java.util.List;

/**
 * @author Doromv
 * @create 2022-04-04-8:38
 */
public interface AddressMapper {
    Integer insert(Address address);
    Integer countByUid(Integer uid);
    List<Address> findByUid(Integer uid);
    Address findByAid(Integer aid);
    Integer updateNonDefault(Integer uid);
    Integer updateDefaultByAid(Integer aid, String modifiedUser, Date modifiedTime);
    Integer deleteByAid(Integer aid);
    Address findLastModified(Integer uid);
    Integer updateByUid(Address address);
}
