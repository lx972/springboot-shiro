package cn.kgc.test.model;

import java.io.Serializable;
import java.util.List;

public class AdminMenu implements Serializable {
    private Integer id;

    private String path;

    private String name;

    private String name_zh;

    private String icon_cls;

    private String component;

    private Integer parent_id;

    /**
     * 自己添加的
     * 代表该菜单的子菜单
     */
    private List<AdminMenu> children;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path == null ? null : path.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getName_zh() {
        return name_zh;
    }

    public void setName_zh(String name_zh) {
        this.name_zh = name_zh == null ? null : name_zh.trim();
    }

    public String getIcon_cls() {
        return icon_cls;
    }

    public void setIcon_cls(String icon_cls) {
        this.icon_cls = icon_cls == null ? null : icon_cls.trim();
    }

    public String getComponent() {
        return component;
    }

    public void setComponent(String component) {
        this.component = component == null ? null : component.trim();
    }

    public Integer getParent_id() {
        return parent_id;
    }

    public void setParent_id(Integer parent_id) {
        this.parent_id = parent_id;
    }

    public List<AdminMenu> getChildren() {
        return children;
    }

    public void setChildren(List<AdminMenu> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", path=").append(path);
        sb.append(", name=").append(name);
        sb.append(", name_zh=").append(name_zh);
        sb.append(", icon_cls=").append(icon_cls);
        sb.append(", component=").append(component);
        sb.append(", parent_id=").append(parent_id);
        sb.append(", children=").append(children);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}