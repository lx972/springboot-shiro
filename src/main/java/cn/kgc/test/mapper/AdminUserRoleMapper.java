package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminUserRole;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUserRole record);

    AdminUserRole selectByPrimaryKey(Integer id);

    List<AdminUserRole> selectAll();

    int updateByPrimaryKey(AdminUserRole record);
}