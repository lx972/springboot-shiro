package cn.kgc.test.utils;

import cn.kgc.test.mapper.AdminPermissionMapper;
import cn.kgc.test.model.AdminPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 获取上下文
 *
 * @Author Administrator
 * @date 9:26
 */
public class PermissionMapUtils {
    private Logger logger = LoggerFactory.getLogger(PermissionMapUtils.class);

    @Autowired
    private AdminPermissionMapper adminPermissionMapper;

    /**
     * 加载权限
     *
     * @return
     */
    public Map<String, String> generatePermissionMap() {
        if (null == adminPermissionMapper) {
            logger.info("context获取Mapper");
            adminPermissionMapper = SpringContextUtils.getContext().getBean(AdminPermissionMapper.class);
        }
        List<AdminPermission> adminPermissions = adminPermissionMapper.selectAll();
        Map<String, String> map = new HashMap<>(16);
        for (AdminPermission permission : adminPermissions) {
            map.put(permission.getUrl(), "perms[" + permission.getUrl() + "]");
        }
        return map;
    }
}
