package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(Integer id);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);

    /**
     * 根据用户id查询该用户所拥有的的角色id
     *
     * @param uid
     * @return
     */
    List<Integer> selectRoleIdByUserId(@Param("uid") Integer uid);


    /**
     * 根据用户id查询该用户所拥有的的角色详情
     *
     * @param uid
     * @return
     */
    List<AdminRole> selectRolesByUserId(@Param("uid") Integer uid);


    /**
     * 查询所有可用角色
     *
     * @return
     */
    List<AdminRole> selectAllEnabledRole();
}
