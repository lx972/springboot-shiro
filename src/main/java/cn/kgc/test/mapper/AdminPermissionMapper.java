package cn.kgc.test.mapper;

import cn.kgc.test.model.AdminPermission;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AdminPermissionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(AdminPermission record);

    AdminPermission selectByPrimaryKey(Integer id);

    List<AdminPermission> selectAll();

    int updateByPrimaryKey(AdminPermission record);

    /**
     * 根据角色id查询出该角色拥有的功能id
     *
     * @param rid
     * @return
     */
    List<Integer> selectPidsByRid(@Param("rid") Integer rid);

    /**
     * 根据用户id查询出该用户拥有的功能
     *
     * @param uid
     * @return
     */
    List<String> selectPermNameByUid(@Param("uid") Integer uid);
}
