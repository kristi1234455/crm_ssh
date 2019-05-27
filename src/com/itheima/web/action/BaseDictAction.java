package com.itheima.web.action;

import com.itheima.domain.BaseDict;
import com.itheima.service.BaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

import java.io.IOException;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.struts2.ServletActionContext;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {
    BaseDict baseDict=new BaseDict();
	public BaseDict getModel(){
		return baseDict;
	}
	BaseDictService baseDictService;
	public void setBaseDictService(BaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}
	
	public String findByTypeCode() throws Exception{
		List<BaseDict> list=baseDictService.findByTypeCode(baseDict.getDict_type_code());
		
		//通过dictTypeCode得到对应的baseDict对象，该对象为多个
		//将对象转化为json数据，并进行返回
		JsonConfig jsonConfig=new JsonConfig();
		//将jsonConfig数据中的三个字段排除，不返回到add.jsp页面中
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		//将list类型的数据转成json类型的字符串数据
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray);
		
		//将json数据转成json类型的字符串进行返回
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
