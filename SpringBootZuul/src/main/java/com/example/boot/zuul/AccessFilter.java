package com.example.boot.zuul;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Component;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * 前置过滤器
 * @author Administrator
 * PRE： 这种过滤器在请求被路由之前调用。我们可利用这种过滤器实现身份验证、在集群中选择请求的微服务、记录调试信息等。
 * ROUTING：这种过滤器将请求路由到微服务。这种过滤器用于构建发送给微服务的请求，并使用Apache HttpClient或Netfilx Ribbon请求微服务。
 * POST：这种过滤器在路由到微服务以后执行。这种过滤器可用来为响应添加标准的HTTP Header、收集统计信息和指标、将响应从微服务发送给客户端等。
 * ERROR：在其他阶段发生错误时执行该过滤器。 除了默认的过滤器类型，Zuul还允许我们创建自定义的过滤器类型。例如，我们可以定制一种STATIC类型的过滤器，直接在Zuul中生成响应，而不将请求转发到后端的微服务。
 *
 */
@Component
public class AccessFilter extends ZuulFilter {

	@Override
	public Object run() throws ZuulException {
		RequestContext ctx = RequestContext.getCurrentContext();
		HttpServletRequest request = ctx.getRequest();
		// 获取传来的参数accessToken
		String accessToken = request.getParameter("accessToken");
		if (accessToken == null) {
			// 过滤该请求，不往下级服务去转发请求，到此结束
			ctx.setSendZuulResponse(false);
			ctx.setResponseStatusCode(401);
			ctx.setResponseBody("{\"result\":\"accessToken为空!\"}");
			ctx.getResponse().setContentType("text/html;charset=UTF-8");
			return null;
		}
		// 如果有token，则进行路由转发
		ctx.addZuulRequestHeader("accessToken", accessToken);
		// 这里return的值没有意义，zuul框架没有使用该返回值
		return null;
	}

	@Override
	public boolean shouldFilter() {
		// 是否执行该过滤器，true代表需要过滤
		return true;
	}

	@Override
	public int filterOrder() {
		// 优先级，数字越大，优先级越低
		return 0;
	}

	@Override
	public String filterType() {
		// 前置过滤器 还有post error
		return "pre";
	}

}
