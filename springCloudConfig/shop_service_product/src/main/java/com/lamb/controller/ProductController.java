package com.lamb.controller;

import com.lamb.pojo.Product;
import com.lamb.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
@RefreshScope //开启动态刷新
public class ProductController {

	@Value("${name}")
	private String name;

	@Value("${server.port}")
	private String port;

	@Value("${spring.cloud.client.ip-address}") //spring cloud 自动的获取当前应用的ip地址
	private String ip;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = "/test")
	public String test() {
		return name;
	}

	@GetMapping
	public List<Product> findAll() {
		System.out.println(ip+":"+port);
		return productService.findAll();
	}
	@GetMapping("/{id}")
	public Product findById(@PathVariable Long id) {

//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {
//			e.printStackTrace();
//		}

		if(id ==3 ) {
			throw new RuntimeException("太忙了");
		}
		Product product = productService.findById(id);
		//设置端口
		product.setProductDesc("调用shop-service-product服务,ip:"+ip+",服务提供者端口:"+port);
		return product;
	}
	@GetMapping("/find/{id}")
	public Product findById2(@PathVariable Long id) {


		Product product = productService.findById(id);
		//设置端口
		product.setProductDesc("调用shop-service-product服务,ip:"+ip+",服务提供者端口:"+port);
		return product;
	}
	@PostMapping
	public String save(@RequestBody Product product) {
		productService.save(product);
		return "保存成功";
	}
	@PutMapping("/{id}")
	public String update(@RequestBody Product product) {
		productService.update(product);
		return "修改成功";
	}
	@DeleteMapping("/{id}")
	public String delete(Long id) {
		productService.delete(id);
		return "删除成功";
	}
}