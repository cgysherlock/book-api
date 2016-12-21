package com.me.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.me.dao.ConcernDao;
import com.me.model.Concern;
import com.me.model.Message;

@Service
public class ConcernService {
	@Autowired ConcernDao concernDao;
	
	public Message concernOthers(long concerner_id,long concerned_id) {
		Message message;
		List<Concern> concerns=concernDao.getConcernByIds(concerner_id, concerned_id);
		Concern concern=concerns.isEmpty()?null:concerns.get(0);
		if (concern==null) {
			concern=new Concern();
			concern.setConcernedid(concerned_id);
			concern.setConcernerid(concerner_id);
			concern.setCreateDate(new Date());
			concern.setModifyDate(new Date());
			concernDao.concernOthers(concern);
//			message=concernDao.concernOthers(concern)?Message.success("关注成功"):Message.error("关注失败");
			message=Message.success("关注成功");
		}else {
			message=Message.warn("已经关注该用户");
		}
		return message;
	}

}
