package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminRoleMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleMenu record);

    AdminRoleMenu selectByPrimaryKey(Integer id);

    List<AdminRoleMenu> selectAll();

    int updateByPrimaryKey(AdminRoleMenu record);
}