package com.yhq.service;

import java.util.List;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.me.kit.PageKit;
import com.me.model.Page;
import com.me.model.Pageable;
import com.yhq.dao.EquipmentDao;
import com.yhq.dao.UserDao;
import com.me.model.Message;
import com.yhq.model.Equipment;
import com.yhq.model.User;

@Service
@Transactional
public class EquipmentService {
	
	@Autowired
	EquipmentDao equipmentDao;

	public Message save(Equipment equipment) {
		String code = equipment.getCode();
		if (equipmentDao.existed(code)) {
			return Message.error("编号已存在");
		}
		equipmentDao.save(equipment);
		return Message.success("添加成功");
	}
	
	public Page<Equipment> pageable(Pageable pageable) {
		return PageKit.page(pageable, equipmentDao, Equipment.class);
	}

	public List<Equipment> getEquipments() {
		return equipmentDao.getEquipments();
	}
}
