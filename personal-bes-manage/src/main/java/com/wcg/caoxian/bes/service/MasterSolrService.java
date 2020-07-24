package com.wcg.caoxian.bes.service;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.wcg.caoxian.bes.vo.mastervo.MasterSolrVo;

public interface MasterSolrService extends SolrCrudRepository<MasterSolrVo, String>{

	List<MasterSolrVo> findByNameContaining(String name);

}
