package com.wcg.caoxian.user.model;

import java.util.Date;

public class Permission {
    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.code
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String code;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.name
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String name;
    
    private String enName;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.uri
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String uri;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Integer versionNo;
    
    private Integer sortNo;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Date createdTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String createdByCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Date lastUpdatedTime;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private String lastUpdatedByCd;

    /**
     *
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ur_permission.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    private Boolean deletedFlag;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.code
     *
     * @return the value of ur_permission.code
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getCode() {
        return code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.code
     *
     * @param code the value for ur_permission.code
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.name
     *
     * @return the value of ur_permission.name
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getName() {
        return name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.name
     *
     * @param name the value for ur_permission.name
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.uri
     *
     * @return the value of ur_permission.uri
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getUri() {
        return uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.uri
     *
     * @param uri the value for ur_permission.uri
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setUri(String uri) {
        this.uri = uri;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.version_no
     *
     * @return the value of ur_permission.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Integer getVersionNo() {
        return versionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.version_no
     *
     * @param versionNo the value for ur_permission.version_no
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setVersionNo(Integer versionNo) {
        this.versionNo = versionNo;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.created_time
     *
     * @return the value of ur_permission.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.created_time
     *
     * @param createdTime the value for ur_permission.created_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.created_by_cd
     *
     * @return the value of ur_permission.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getCreatedByCd() {
        return createdByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.created_by_cd
     *
     * @param createdByCd the value for ur_permission.created_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setCreatedByCd(String createdByCd) {
        this.createdByCd = createdByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.last_updated_time
     *
     * @return the value of ur_permission.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Date getLastUpdatedTime() {
        return lastUpdatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.last_updated_time
     *
     * @param lastUpdatedTime the value for ur_permission.last_updated_time
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setLastUpdatedTime(Date lastUpdatedTime) {
        this.lastUpdatedTime = lastUpdatedTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.last_updated_by_cd
     *
     * @return the value of ur_permission.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public String getLastUpdatedByCd() {
        return lastUpdatedByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.last_updated_by_cd
     *
     * @param lastUpdatedByCd the value for ur_permission.last_updated_by_cd
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setLastUpdatedByCd(String lastUpdatedByCd) {
        this.lastUpdatedByCd = lastUpdatedByCd;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ur_permission.deleted_flag
     *
     * @return the value of ur_permission.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public Boolean getDeletedFlag() {
        return deletedFlag;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ur_permission.deleted_flag
     *
     * @param deletedFlag the value for ur_permission.deleted_flag
     *
     * @mbg.generated Fri Sep 22 13:40:32 CST 2017
     */
    public void setDeletedFlag(Boolean deletedFlag) {
        this.deletedFlag = deletedFlag;
    }

	
	public String getEnName() {
		return enName;
	}

	public void setEnName(String enName) {
		this.enName = enName;
	}

	public Integer getSortNo() {
		return sortNo;
	}

	public void setSortNo(Integer sortNo) {
		this.sortNo = sortNo;
	}
}