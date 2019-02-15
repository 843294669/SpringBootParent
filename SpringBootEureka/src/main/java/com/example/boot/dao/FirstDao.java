package com.example.boot.dao;

import com.example.boot.vo.SbUser;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * 在Spring Boot项目中数据访问层无需提供实现，直接继承数据访问接口即可。
 * <SbUser, Integer> 指定实体类和主键类型
 * @author Administrator
 *
 */
@Repository
public interface FirstDao extends CrudRepository<SbUser, Integer> {

}
