package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminRolePermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AdminRolePermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRolePermission record);

    AdminRolePermission selectByPrimaryKey(Integer id);

    List<AdminRolePermission> selectAll();

    int updateByPrimaryKey(AdminRolePermission record);

    /**
     * 根据角色id删除对应的所有功能
     *
     * @param rid
     * @return
     */
    int deleteByRid(@Param("rid") Integer rid);

    /**
     * 根据角色id和权限id删除对应功能
     *
     * @param rid
     * @return
     */
    int deleteByRidAndPid(@Param("rid") Integer rid,@Param("pid") Integer pid);
}
