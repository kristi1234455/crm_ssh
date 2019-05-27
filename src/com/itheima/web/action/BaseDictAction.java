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
		
		//ͨ��dictTypeCode�õ���Ӧ��baseDict���󣬸ö���Ϊ���
		//������ת��Ϊjson���ݣ������з���
		JsonConfig jsonConfig=new JsonConfig();
		//��jsonConfig�����е������ֶ��ų��������ص�add.jspҳ����
		jsonConfig.setExcludes(new String[]{"dict_sort","dict_enable","dict_memo"});
		//��list���͵�����ת��json���͵��ַ�������
		JSONArray jsonArray=JSONArray.fromObject(list,jsonConfig);
		System.out.println(jsonArray);
		
		//��json����ת��json���͵��ַ������з���
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		
		return NONE;
	}
}
