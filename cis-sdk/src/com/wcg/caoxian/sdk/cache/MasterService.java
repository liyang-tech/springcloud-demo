package com.wcg.caoxian.sdk.cache;

import java.util.List;

import com.wcg.caoxian.sdk.bean.MasterBean;

public interface MasterService {

	public List<MasterBean> searchByObjectCd(String objectCd);
	
	public String getNameByObjectCdAndCode(String objectCd, String code);
	
}
