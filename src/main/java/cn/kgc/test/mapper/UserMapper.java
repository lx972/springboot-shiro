package cn.kgc.test.mapper;

import cn.kgc.test.model.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    User selectByPrimaryKey(Integer id);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectLogin(User user);

    User selectOneByUser(User user);

    /**
     * 批量删除
     *
     * @param uids
     * @return
     */
    int deleteBatchByIds(@Param("uids") List<Integer> uids);


}
