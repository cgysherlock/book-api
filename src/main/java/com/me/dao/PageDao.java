package com.me.dao;


import java.math.BigInteger;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.query.NativeQuery;

import com.me.annotation.TableBind;
import com.me.kit.ParaKit;
import com.me.model.Pageable;

public class PageDao<T> extends BaseDao {

	/**
	 * 分页操作
	 * @param pageable
	 * @param clazz
	 * @return
	 */
	public List<T> paginate(Pageable pageable, Class<T> clazz) {
		TableBind tableBind = clazz.getAnnotation(TableBind.class);
		String tableName = tableBind.tableName();
		StringBuilder sql = new StringBuilder("select * ");
		sql.append("from " + tableName + " ");
		sql.append("where 1 = 1 ");
		List<String> params = new ArrayList<String>();
		if (ParaKit.notBlank(pageable.getSearchProperty())) {
			sql.append("and " + pageable.getSearchProperty() + " like ? ");
			params.add(pageable.getSearchValue());
		}
		if (ParaKit.notBlank(pageable.getOrderProperty())) {
			sql.append("order by " + pageable.getOrderProperty() + " ? ");
			params.add(pageable.getOrderDirection());
		}
		sql.append("limit " + pageable.getPreRow() + ", " + pageable.getPageSize());
		NativeQuery<T> query = getSession().createNativeQuery(sql.toString(), clazz);
		for (int i = 0 ; i < params.size() ; i++) {
			query.setParameter(i, params.get(i));
		}
		return query.getResultList();
	}

	@SuppressWarnings("rawtypes")
	public long getCount(Pageable pageable, Class<T> clazz) {
		TableBind tableBind = clazz.getAnnotation(TableBind.class);
		String tableName = tableBind.tableName();
		StringBuilder sql = new StringBuilder("select count(id) ");
		sql.append("from " + tableName + " ");
		NativeQuery query = getSession().createNativeQuery(sql.toString());
		long num= (long)(((BigInteger) query.getResultList().get(0)).intValue());
		return num;
	}
}
