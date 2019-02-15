package com.example.boot;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.example.boot.vo.SbUser;

/**
 * Controller测试类
 * @author Administrator
 * 
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ControllerTest {

	@Autowired
	private WebApplicationContext wac;

	private MockMvc mvc;
	private MockHttpSession session;

	@Before
	public void setupMockMvc() {
		// 初始化MockMvc对象
		mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		session = new MockHttpSession();
		SbUser user = new SbUser();
		// 拦截器那边会判断用户是否登录，所以这里注入一个用户
		session.setAttribute("user", user);
	}

	/**
	 * 新增教程测试用例
	 * @throws Exception
	 */
	@Test
	public void addUser() throws Exception {
		String json = "{\"author\":\"HAHAHAA\",\"title\":\"Spring\",\"url\":\"http://tengj.top/\"}";
		mvc.perform(MockMvcRequestBuilders.post("/user/add")
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.content(json.getBytes()) // 传json参数
				.session(session))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andDo(MockMvcResultHandlers.print());
	}

	/**
	 * 获取教程测试用例
	 * @throws Exception
	 */
	@Test
	public void getUser() throws Exception {
		mvc.perform(MockMvcRequestBuilders.get("/listUser")
				.contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)
				.session(session))
			.andExpect(MockMvcResultMatchers.status().isOk())
			//.andExpect(MockMvcResultMatchers.jsonPath("$.author").value("嘟嘟MD独立博客"))
			//.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Spring Boot干货系列"))
			.andDo(MockMvcResultHandlers.print());
	}

}
