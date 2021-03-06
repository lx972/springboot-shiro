package cn.kgc.test.model;

import java.io.Serializable;
import java.util.List;

public class AdminRole implements Serializable {
    private Integer id;

    private String name;

    private String name_zh;

    private Boolean enabled;

    private List<Integer> mids;

    private List<Integer> pids;

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Integer> getMids() {
        return mids;
    }

    public void setMids(List<Integer> mids) {
        this.mids = mids;
    }

    public List<Integer> getPids() {
        return pids;
    }

    public void setPids(List<Integer> pids) {
        this.pids = pids;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", name_zh=").append(name_zh);
        sb.append(", enabled=").append(enabled);
        sb.append(", mids=").append(mids);
        sb.append(", pids=").append(pids);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}
