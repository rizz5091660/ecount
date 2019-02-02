package com.sanisoft.ecount.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.sanisoft.ecount.dao.CoaCustomLevelN1Dao;
import com.sanisoft.ecount.entity.CoaCustomLevelN1;

@Repository("CoaCustomLevelN1Dao")
public class CoaCustomLevelN1DaoImpl extends EcountDao implements CoaCustomLevelN1Dao {

	@Override
	public List<CoaCustomLevelN1> getAllForDropDown() {
		List<CoaCustomLevelN1> customLevels = new ArrayList<CoaCustomLevelN1>();
		List<Map<String, Object>> resultSet = jdbcTemplate.queryForList(DaoConstant.SELECT_COA_CUSTOM_LEVELN1_DROP_DOWN);
		for(Map<String,Object> map :resultSet) {
			CoaCustomLevelN1 cs = new CoaCustomLevelN1();
			//cs.setId((int) map.get("id"));
			//cs.setName((String) map.get("name"));
			customLevels.add(cs);
		} 
		return customLevels;
	}

}
