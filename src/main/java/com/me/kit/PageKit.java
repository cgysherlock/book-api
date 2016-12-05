package com.me.kit;

import java.util.List;

import com.me.dao.PageDao;
import com.me.model.Page;
import com.me.model.Pageable;

public class PageKit {

	/**
	 * 分页
	 * 
	 * @param pageable
	 * @param pageDao
	 * @return
	 */
	public static <T> Page<T> page(Pageable pageable, PageDao<T> pageDao, Class<T> clazz) {
		int pageNumber = pageable.getPageNumber();
		int pageSize = pageable.getPageSize();
		int totalPage = 0;
		long totalRow = 0;
		int preRow = 0;
		if (pageNumber > 1) {
			preRow = (pageNumber - 1) * pageSize;
		}
		pageable.setPreRow(preRow);
		totalRow = pageDao.getCount(pageable, clazz);
		totalPage = (int) (totalRow / pageSize);
		if (totalRow % pageSize != 0) {
			totalPage++;
		}
		List<T> list = pageDao.paginate(pageable, clazz);
		return new Page<T>(list, pageNumber, pageSize, totalPage, (int) totalRow);
	}

}
