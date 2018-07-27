package com.yidi.jerseyREST;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

public class RestApplication extends ResourceConfig{
	public RestApplication() {
		packages("com.xiansky.action");
        //注册JSON转换器
        register(JacksonJsonProvider.class);
        register(JspMvcFeature.class);
        property(JspMvcFeature.TEMPLATE_BASE_PATH, "/");
	}

}
