package com.wcg.caoxian.sdk.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.wcg.caoxian.sdk.bean.MasterBean;

@Service("masterService")
public class MasterServiceImp implements MasterService {

	@Autowired
	@Qualifier("masterRedisTemplate")
	private RedisTemplate<String, String > redisTemplate;
	
	@Override
	public List<MasterBean> searchByObjectCd(String objectCd) {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		List<MasterBean> masterList = new ArrayList<MasterBean>();
		Cursor<Entry<Object, Object>> scan = opsForHash.scan("mst_"+objectCd, null);
		while(scan.hasNext()){
			Entry<Object, Object> next = scan.next();
			MasterBean masterBean = new MasterBean();
			masterBean.setObjectCd(objectCd);
			masterBean.setCode(next.getKey().toString());
			masterBean.setName(next.getValue().toString());
			masterList.add(masterBean);
		}
		return masterList;
	}

	@Override
	public String getNameByObjectCdAndCode(String objectCd, String code) {
		HashOperations<String, Object, Object> opsForHash = redisTemplate.opsForHash();
		Object masterName = opsForHash.get("mst_"+objectCd, code);
		if(masterName != null){
			return masterName.toString();
		}else{
			return null;
		}
	}

}