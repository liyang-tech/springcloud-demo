package com.wcg.caoxian.bes.service;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.wcg.caoxian.bes.vo.custvo.CustImageVo;

public interface CustImageService extends MongoRepository<CustImageVo, String>{

	public CustImageVo findById(String id);

	public CustImageVo save(CustImageVo custImageVo);

	
}
