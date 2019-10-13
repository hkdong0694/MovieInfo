package com.example.movieinfo_mvp.Network.Model.KMDetail;

import com.google.gson.annotations.SerializedName;

public class MovieStaff {

    @SerializedName("staffNm")
    private String staffMn;
    private String staffRoleGroup;
    private String staffRole;
    private String staffEtc;
    private String staffId;

    public String getStaffMn() {
        return staffMn;
    }

    public void setStaffMn(String staffMn) {
        this.staffMn = staffMn;
    }

    public String getStaffRoleGroup() {
        return staffRoleGroup;
    }

    public void setStaffRoleGroup(String staffRoleGroup) {
        this.staffRoleGroup = staffRoleGroup;
    }

    public String getStaffRole() {
        return staffRole;
    }

    public void setStaffRole(String staffRole) {
        this.staffRole = staffRole;
    }

    public String getStaffEtc() {
        return staffEtc;
    }

    public void setStaffEtc(String staffEtc) {
        this.staffEtc = staffEtc;
    }

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

}
