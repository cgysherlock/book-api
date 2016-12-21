package com.yhq.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yhq.dao.ConcernDao;
import com.yhq.model.Concern;
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
			concern.setConcernedId(concerned_id);
			concern.setConcernerId(concerner_id);
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
