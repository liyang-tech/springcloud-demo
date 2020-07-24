package com.wcg.caoxian.master.service;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.wcg.caoxian.master.vo.MasterSolrVo;

public interface MasterSolrService extends SolrCrudRepository<MasterSolrVo, String>{

	List<MasterSolrVo> findByNameContaining(String name);

}
