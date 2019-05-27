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

	// ��set���Եķ�����������
	Integer currPage = 1;

	public void setCurrPage(Integer currPage) {
		// ���õ�ǰҳ������Ĭ��ֵΪ1
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

	// �����������ԣ���������form���е��ļ������Ϣ
	private String uploadFileName;// �ļ�����
	private String uploadContentType;// �ļ�����
	private File upload;// �ļ�����

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
		if (upload != null) {// ���ļ����������ݣ����ϴ����ļ�
			// �����Ŀ¼�����������ļ������������ļ����ǣ����������������

			// �����ļ��ڷ������ϵı���·��
			String path = "F:/upload";
			String uuidFileName = UploadUtils.getUUIDName(uploadFileName);// ��������ļ���+��׺��
			String realDir = UploadUtils.getDir(uuidFileName);// ����uuid+��׺���õ����Ŀ¼

			// �������������ļ������Ŀ¼��λ��+������Ŀ¼
			String url = path + realDir;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �����ļ������ݣ�λ��+������Ŀ¼+�������ļ���+�ļ�����׺
			File dictFile = new File(url + "/" + uuidFileName);

			// ��upload�ļ��е����ݸ��Ƶ�dictFile�ļ��У��������ļ��е����ݸ��Ƶ����������ļ���
			FileUtils.copyFile(upload, dictFile);

			// �����ļ����ļ���
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
		// ����id��ѯ��customer����Ϣ����תҳ�棬������л���
		customer = customerService.findById(customer.getCust_id());

		// ��customer���󴫵ݵ�jspҳ�������ַ�ʽ��
		// һ���ֶ�ѹջ������ֱ�ӵ���ģ�������Ķ���õ�customer���ݣ���Ϊģ�������Ķ���Ĭ����ջ����
		// ���ʹ�õ�һ�ַ�ʽ����������: <s:property value="cust_name"/> <s: name="cust_name"
		// value="">
		// ���ʹ�õڶ��ַ�ʽ����������: <s:property value="model.cust_name"/>
		// ActionContext.getContext().getValueStack().push(customer);

		// ��תҳ��
		return "editSuccess";
	}

	public String update() throws Exception {
		// �ж��ļ����Ƿ�Ϊ�գ�������ǣ�ֱ��ɾ��
		if (upload != null) {
			// ɾ����customer�ж�Ӧ��cust_image
			String cust_image = customer.getCust_image();
			if (cust_image != null || !"".equals(cust_image)) {
				File file = new File(cust_image);
				if (file.exists()) {
					file.delete();
				}
			}
			// �����ϴ����ļ�����ļ�
			// �����ļ��ڷ������ϵı���·��
			String path = "F:/upload";
			String uuidFileName = UploadUtils.getUUIDName(uploadFileName);// ��������ļ���+��׺��
			String realDir = UploadUtils.getDir(uuidFileName);// ����uuid+��׺���õ����Ŀ¼

			// �������������ļ������Ŀ¼��λ��+������Ŀ¼
			String url = path + realDir;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			// �����ļ������ݣ�λ��+������Ŀ¼+�������ļ���+�ļ�����׺
			File dictFile = new File(url + "/" + uuidFileName);

			// ��upload�ļ��е����ݸ��Ƶ�dictFile�ļ��У��������ļ��е����ݸ��Ƶ����������ļ���
			FileUtils.copyFile(upload, dictFile);

			// �����ļ����ļ���
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.update(customer);
		return "updateSuccess";
	}

	public String findAllCustomer() throws Exception {
		List<Customer> list = customerService.findAll();
		// ��listת��Ϊjson���ݣ�����jspҳ������첽��ѯ����ӵ�option�ӱ�ǩ��
		JsonConfig jsonConfig = new JsonConfig();
		jsonConfig.setExcludes(new String[] { "linkMans", "baseDictSource", "baseDictLevel", "baseDictIndustry" });
		// ת��JSOn:
		JSONArray jsonArray = JSONArray.fromObject(list, jsonConfig);
		ServletActionContext.getResponse().setContentType("text/html;charset=UTF-8");
		ServletActionContext.getResponse().getWriter().println(jsonArray.toString());
		return NONE;
	}
}
