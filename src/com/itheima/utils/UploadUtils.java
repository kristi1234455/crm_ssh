package com.itheima.utils;

import java.util.UUID;

public class UploadUtils {
	
	/**
	 * 获取传入文件名的真实文件名称+后缀
	 * 如，传入的是：c:/upload/1.jpg，得到的是1.jpg
	 */
	public static String getRealName(String name){
		int index = name.lastIndexOf("\\");//获取最后一个"/"
		return name.substring(index+1);
	}
	
	/**
	 * 通过文件的真实名字+后缀，获取文件随机不重名名字+后缀
	 * 如，传入的是：1.jpg，得到的是2C65C3F01FE049AFA3503A22E4D1DBD6.jgp
	 */
	public static String getUUIDName(String realName){
		//realname  可能是  1.jpg   也可能是  1
		int index = realName.lastIndexOf(".");//获取后缀名
		if(index==-1){
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}else{
			return UUID.randomUUID().toString().replace("-", "").toUpperCase()+realName.substring(index);
		}
	}

	/**
	 * 通过传入不重名的文件名+后缀，得到不重名的文件目录
	 * 如，传入的是2C65C3F01FE049AFA3503A22E4D1DBD6.jgp，得到的是/0/2
	 */
	public static String getDir(String name){
		int i = name.hashCode();
		String hex = Integer.toHexString(i);
		int j=hex.length();
		for(int k=0;k<8-j;k++){
			hex="0"+hex;
		}
		return "/"+hex.charAt(0)+"/"+hex.charAt(1);
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String s="G:\\day17-基础加强\\resource\\1.jpg";
		//String s="1.jgp";
		String realName = getRealName(s);
		System.out.println(realName);//1.jgp
		
		String uuidName = getUUIDName(realName);
		System.out.println(uuidName);//2C65C3F01FE049AFA3503A22E4D1DBD6.jgp
		
		String dir = getDir(realName);
		System.out.println(dir);// /0/2
		
		
	}
}
