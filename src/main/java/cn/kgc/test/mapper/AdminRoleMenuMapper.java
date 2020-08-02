package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminRoleMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRoleMenu record);

    AdminRoleMenu selectByPrimaryKey(Integer id);

    List<AdminRoleMenu> selectAll();

    int updateByPrimaryKey(AdminRoleMenu record);

    /**
     * 根据角色id删除对应的所有菜单
     *
     * @param rid
     * @return
     */
    int deleteByRid(@Param("rid") Integer rid);
}
