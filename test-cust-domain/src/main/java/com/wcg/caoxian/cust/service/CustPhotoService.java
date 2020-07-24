package com.wcg.caoxian.cust.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wcg.caoxian.cust.vo.CustPhotoVO;

public interface CustPhotoService extends MongoRepository<CustPhotoVO, String>{

	public CustPhotoVO findById(String id);

	public CustPhotoVO save(CustPhotoVO custPhotoVO);

	
}
