package com.example.boot.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.boot.dao.FirstDao;
import com.example.boot.vo.SbUser;


@Service
@Transactional
public class FirstService {
	
	@Autowired
	private FirstDao dao;

	public SbUser getUser() {
		return dao.findAll().iterator().next();
	}

}
