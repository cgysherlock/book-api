package com.me.dao;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.me.model.Concern;

@Repository
public class ConcernDao extends BaseDao {
	
	public boolean concernOthers(Concern concern)
	{
		StringBuilder sql = new StringBuilder("select ");
		sql.append(str)
		Query<Concern> query=getSession().createNativeQuery(sql.toString(), Concern.class);
		boolean result=true;
		try {
			getSession().save(concern);
		} catch (HibernateException e) {
			// TODO: handle exception
			result=false;
		}
		return result;
	}
	
	public List<Concern> getConcernByIds(long concernerId, long concernedId) {
		Query<Concern> query=getSession().createQuery("from Concern c where c.concerner.id=? and c.concerned.id=? ",Concern.class);
		query.setParameter(1, concernerId);
		query.setParameter(2, concernedId);
		return query.getResultList();
	}
	
}
