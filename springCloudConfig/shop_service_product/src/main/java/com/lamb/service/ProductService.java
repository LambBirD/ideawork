package com.lamb.service;


import com.lamb.pojo.Product;

import java.util.List;

public interface ProductService {

	public List<Product> findAll() ;
	/**
	 * 根据id查询
	 */
	Product findById(Long id);

	/**
	 * 保存
	 */
	void save(Product product);
	/**
	 * 更新
	 */
	void update(Product product);
	/**
	 * 删除
	 */
	void delete(Long id);
}
