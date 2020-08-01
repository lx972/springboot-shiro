package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminMenu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminMenuMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminMenu record);

    AdminMenu selectByPrimaryKey(Integer id);

    List<AdminMenu> selectAll();

    int updateByPrimaryKey(AdminMenu record);

    /**
     * 根据用户角色查询其所拥有的菜单
     *
     * @param rids
     * @return
     */
    List<AdminMenu> selectMenusByRids(@Param("rids") List<Integer> rids);

    /**
     * 根据父菜单id查询其所拥有的子菜单
     *
     * @param parentId
     * @return
     */
    List<AdminMenu> selectMenusByParentId(@Param("parentId") Integer parentId);



    /**
     * 根据角色id查询拥有的菜单id
     *
     * @param rid
     * @return
     */
    List<Integer> selectMenuIdsByRid(@Param("rid") Integer rid);
}
