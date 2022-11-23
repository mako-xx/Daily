package com.travelthree.daily.domain;

import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName department
 */
@Data
public class Department implements Serializable {
    /**
     * 部门id
     */
    private String id;

    /**
     * 部门名称
     */
    private String name;

    /**
     * 上级部门id
     */
    private String superiorId;

    private static final long serialVersionUID = 1L;

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        Department other = (Department) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getSuperiorId() == null ? other.getSuperiorId() == null : this.getSuperiorId().equals(other.getSuperiorId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getSuperiorId() == null) ? 0 : getSuperiorId().hashCode());
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", name=").append(name);
        sb.append(", superiorId=").append(superiorId);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}