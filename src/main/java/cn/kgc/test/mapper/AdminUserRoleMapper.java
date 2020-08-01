package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminUserRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminUserRoleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminUserRole record);

    AdminUserRole selectByPrimaryKey(Integer id);

    List<AdminUserRole> selectAll();

    int updateByPrimaryKey(AdminUserRole record);


    /**
     * 根据用户id删除用户所拥有的角色
     *
     * @param uid
     * @return
     */
    int deleteByUid(@Param("uid") Integer uid);

    /**
     * 根据多个用户id删除用户所拥有的角色
     *
     * @param uids
     * @return
     */
    int deleteAllByUids(@Param("uids") List<Integer> uids);
}
