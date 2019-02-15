package com.example.boot.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.example.boot.service.FirstService;
import com.example.boot.vo.SbUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class FirstController {
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private FirstService service;
	
	@RequestMapping("/firstPage")
	public String firstPage() {
		//查找资源文件下templates中的模板
		return "index";
	}
	
	@RequestMapping("/listUser")
	@ResponseBody
	public SbUser listUser(HttpServletRequest request) throws SQLException, InterruptedException {
		System.out.println(dataSource.getConnection());
		SbUser user = service.getUser();
		System.out.println(user.toString());
		return user;
	}

}
