package com.yhq.dao;

import java.util.List;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.dao.BaseDao;
import com.me.dao.PageDao;
import com.yhq.model.Equipment;
import com.yhq.model.User;

@Repository
public class EquipmentDao extends PageDao<Equipment>{
	
	/**
	 * 添加设备
	 * @param equipment
	 */
	public void save(Equipment equipment) {
		getSession().save(equipment);
	}
	
	public boolean existed(String code) {
		StringBuilder hql = new StringBuilder(" select count(*) ");
		hql.append("from Equipment ");
		hql.append("where code = :code ");
		Query query = getSession().createQuery(hql.toString());
		query.setString("code", code);
		long count = (long)query.uniqueResult();
		return count > 0;
	}

	public List<Equipment> getEquipments() {
		StringBuilder hql = new StringBuilder(" ");
		hql.append("from Equipment ");
		Query<Equipment> query = getSession().createQuery(hql.toString(), Equipment.class);
		List<Equipment> list = query.getResultList();
		return list;
	}
	
}
