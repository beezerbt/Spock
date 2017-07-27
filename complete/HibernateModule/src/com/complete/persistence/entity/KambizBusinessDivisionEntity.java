package com.complete.persistence.entity;

import javax.persistence.*;

/**
 * Created by kambiz on 20/07/2017.
 */
@Entity
@Table(name = "BUSINESS_DIVISION", schema = "SCH_CONTRACT", catalog = "")
public class KambizBusinessDivisionEntity {
    private Long businessDivisionId;
    private String businessDivisionName;
    private String businessDivisionGroupName;
    private String businessDivisionCode;

    @Id
    @Column(name = "BUSINESS_DIVISION_ID", nullable = false, precision = 0)
    public Long getBusinessDivisionId() {
        return businessDivisionId;
    }

    public void setBusinessDivisionId(Long businessDivisionId) {
        this.businessDivisionId = businessDivisionId;
    }

    @Basic
    @Column(name = "BUSINESS_DIVISION_NAME", nullable = false, length = 200)
    public String getBusinessDivisionName() {
        return businessDivisionName;
    }

    public void setBusinessDivisionName(String businessDivisionName) {
        this.businessDivisionName = businessDivisionName;
    }

    @Basic
    @Column(name = "BUSINESS_DIVISION_GROUP_NAME", nullable = true, length = 200)
    public String getBusinessDivisionGroupName() {
        return businessDivisionGroupName;
    }

    public void setBusinessDivisionGroupName(String businessDivisionGroupName) {
        this.businessDivisionGroupName = businessDivisionGroupName;
    }

    @Basic
    @Column(name = "BUSINESS_DIVISION_CODE", nullable = false, length = 50)
    public String getBusinessDivisionCode() {
        return businessDivisionCode;
    }

    public void setBusinessDivisionCode(String businessDivisionCode) {
        this.businessDivisionCode = businessDivisionCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KambizBusinessDivisionEntity that = (KambizBusinessDivisionEntity) o;

        if (businessDivisionId != null ? !businessDivisionId.equals(that.businessDivisionId) : that.businessDivisionId != null)
            return false;
        if (businessDivisionName != null ? !businessDivisionName.equals(that.businessDivisionName) : that.businessDivisionName != null)
            return false;
        if (businessDivisionGroupName != null ? !businessDivisionGroupName.equals(that.businessDivisionGroupName) : that.businessDivisionGroupName != null)
            return false;
        if (businessDivisionCode != null ? !businessDivisionCode.equals(that.businessDivisionCode) : that.businessDivisionCode != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = businessDivisionId != null ? businessDivisionId.hashCode() : 0;
        result = 31 * result + (businessDivisionName != null ? businessDivisionName.hashCode() : 0);
        result = 31 * result + (businessDivisionGroupName != null ? businessDivisionGroupName.hashCode() : 0);
        result = 31 * result + (businessDivisionCode != null ? businessDivisionCode.hashCode() : 0);
        return result;
    }
}
