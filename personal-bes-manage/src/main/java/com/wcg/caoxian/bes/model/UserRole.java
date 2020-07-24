package com.wcg.caoxian.bes.model;

import java.util.Date;

public class UserRole {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.user_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String userCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.role_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String roleCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.des
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String des;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Integer versionNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String createdByCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Date lastUpdatedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String lastUpdatedByCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_user_role.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Boolean deletedFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.user_cd
     *
     * @return the value of ur_user_role.user_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getUserCd() {
        return userCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.user_cd
     *
     * @param userCd the value for ur_user_role.user_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setUserCd(String userCd) {
        this.userCd = userCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.role_cd
     *
     * @return the value of ur_user_role.role_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getRoleCd() {
        return roleCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.role_cd
     *
     * @param roleCd the value for ur_user_role.role_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setRoleCd(String roleCd) {
        this.roleCd = roleCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.des
     *
     * @return the value of ur_user_role.des
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getDes() {
        return des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.des
     *
     * @param des the value for ur_user_role.des
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setDes(String des) {
        this.des = des;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.version_no
     *
     * @return the value of ur_user_role.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.version_no
     *
     * @param versionNo the value for ur_user_role.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.created_time
     *
     * @return the value of ur_user_role.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.created_time
     *
     * @param createdTime the value for ur_user_role.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.created_by_cd
     *
     * @return the value of ur_user_role.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getCreatedByCd() {
        return createdByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.created_by_cd
     *
     * @param createdByCd the value for ur_user_role.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setCreatedByCd(String createdByCd) {
        this.createdByCd = createdByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.last_updated_time
     *
     * @return the value of ur_user_role.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.last_updated_time
     *
     * @param lastUpdatedTime the value for ur_user_role.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.last_updated_by_cd
     *
     * @return the value of ur_user_role.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getLastUpdatedByCd() {
        return lastUpdatedByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.last_updated_by_cd
     *
     * @param lastUpdatedByCd the value for ur_user_role.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setLastUpdatedByCd(String lastUpdatedByCd) {
        this.lastUpdatedByCd = lastUpdatedByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_user_role.deleted_flag
     *
     * @return the value of ur_user_role.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_user_role.deleted_flag
     *
     * @param deletedFlag the value for ur_user_role.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }
}