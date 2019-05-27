package com.itheima.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.domain.Customer;
import com.itheima.service.CustomerService;
import com.itheima.utils.PageBean;
import com.itheima.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {
	Customer customer = new Customer();

	@Override
	public Customer getModel() {
		return customer;
	}

	CustomerService customerService;

	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	// 用set属性的方法接收数据
	Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		// 设置当前页参数的默认值为1
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	Integer pageSize = 3;

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	// 设置三个属性，用来接收form表单中的文件相关信息
	private String uploadFileName;// 文件名字
	private String uploadContentType;// 文件类型
	private File upload;// 文件本身

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String saveUI() {
		return "saveUI";
	}

	public String save() throws Exception {
		// customerService.save(customer);
		if (upload != null) {// 即文件本身有数据，即上传了文件
			// 如果该目录下有重名的文件，可能引起文件覆盖，解决重名覆盖问题

			// 创建文件在服务器上的保存路径
			String path = "F:/upload";
			String uuidFileName = UploadUtils.getUUIDName(uploadFileName);// 生成随机文件名+后缀名
			String realDir = UploadUtils.getDir(uuidFileName);// 根据uuid+后缀名得到随机目录

			// 创建服务器上文件保存的目录，位置+不重名目录
			String url = path + realDir;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 创建文件，根据：位置+不重名目录+不重名文件名+文件名后缀
			File dictFile = new File(url + "/" + uuidFileName);

			// 将upload文件中的内容复制到dictFile文件中，即将表单文件中的内容复制到服务器的文件中
			FileUtils.copyFile(upload, dictFile);

			// 保存文件的文件名
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}

	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		if (customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		if (customer.getBaseDictSource() != null) {
			if (customer.getBaseDictSource().getDict_id() != null
					&& !"".equals(customer.getBaseDictSource().getDict_id()))
				detachedCriteria
						.add(Restrictions.eq("baseDictSource.dict_id", customer.getBaseDictSource().getDict_id()));
		}
		if (customer.getBaseDictLevel() != null) {
			if (customer.getBaseDictLevel().getDict_id() != null
					&& !"".equals(customer.getBaseDictLevel().getDict_id()))
				detachedCriteria
						.add(Restrictions.eq("baseDictLevel.dict_id", customer.getBaseDictLevel().getDict_id()));
		}
		if (customer.getBaseDictIndustry() != null) {
			if (customer.getBaseDictIndustry().getDict_id() != null
					&& !"".equals(customer.getBaseDictIndustry().getDict_id()))
				detachedCriteria
						.add(Restrictions.eq("baseDictIndustry.dict_id", customer.getBaseDictIndustry().getDict_id()));
		}
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String delete() {
		customer = customerService.findById(customer.getCust_id());
		if (customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			if (file.exists()) {
				file.delete();
			}
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}

	public String edit() {
		// 根据id查询到customer的信息后，跳转页面，将其进行回显
		customer = customerService.findById(customer.getCust_id());

		// 将customer对象传递到jsp页面有两种方式：
		// 一：手动压栈；二：直接调用模型驱动的对象得到customer数据，因为模型驱动的对象，默认在栈顶。
		// 如果使用第一种方式：回显数据: <s:property value="cust_name"/> <s: name="cust_name"
		// value="">
		// 如果使用第二种方式：回显数据: <s:property value="model.cust_name"/>
		// ActionContext.getContext().getValueStack().push(customer);

		// 跳转页面
		return "editSuccess";
	}

	public String update() throws Exception {
		// 判断文件项是否为空，如果不是，直接删除
		if (upload != null) {
			// 删除该customer中对应的cust_image
			String cust_image = customer.getCust_image();
			if (cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				if (file.exists()) {
					file.delete();
				}
			}
			// 根据上传的文件项保存文件
			// 创建文件在服务器上的保存路径
			String path = "F:/upload";
			String uuidFileName = UploadUtils.getUUIDName(uploadFileName);// 生成随机文件名+后缀名
			String realDir = UploadUtils.getDir(uuidFileName);// 根据uuid+后缀名得到随机目录

			// 创建服务器上文件保存的目录，位置+不重名目录
			String url = path + realDir;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// 创建文件，根据：位置+不重名目录+不重名文件名+文件名后缀
			File dictFile = new File(url + "/" + uuidFileName);

			// 将upload文件中的内容复制到dictFile文件中，即将表单文件中的内容复制到服务器的文件中
			FileUtils.copyFile(upload, dictFile);

			// 保存文件的文件名
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}

	public String findAllCustomer() throws Exception {
		List<Customer> list = customerService.findAll();
		// 将list转换为json数据，便于jsp页面进行异步查询后添加到option子标签中
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "linkMans", "baseDictSource", "baseDictLevel", "baseDictIndustry" });
		// 转成JSOn:
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
