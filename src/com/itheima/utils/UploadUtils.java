package com.itheima.utils;

import java.util.UUID;

public class UploadUtils {
	
	/**
	 * ��ȡ�����ļ�������ʵ�ļ�����+��׺
	 * �磬������ǣ�c:/upload/1.jpg���õ�����1.jpg
	 */
	public static String getRealName(String name){
		int index = name.lastIndexOf("\\");//��ȡ���һ��"/"
		return name.substring(index+1);
	}
	
	/**
	 * ͨ���ļ�����ʵ����+��׺����ȡ�ļ��������������+��׺
	 * �磬������ǣ�1.jpg���õ�����2C65C3F01FE049AFA3503A22E4D1DBD6.jgp
	 */
	public static String getUUIDName(String realName){
		//realname  ������  1.jpg   Ҳ������  1
		int index = realName.lastIndexOf(".");//��ȡ��׺��
		if(index==-1){
			return UUID.randomUUID().toString().replace("-", "").toUpperCase();
		}else{
			return UUID.randomUUID().toString().replace("-", "").toUpperCase()+realName.substring(index);
		}
	}

	/**
	 * ͨ�����벻�������ļ���+��׺���õ����������ļ�Ŀ¼
	 * �磬�������2C65C3F01FE049AFA3503A22E4D1DBD6.jgp���õ�����/0/2
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
		String s="G:\\day17-������ǿ\\resource\\1.jpg";
		//String s="1.jgp";
		String realName = getRealName(s);
		System.out.println(realName);//1.jgp
		
		String uuidName = getUUIDName(realName);
		System.out.println(uuidName);//2C65C3F01FE049AFA3503A22E4D1DBD6.jgp
		
		String dir = getDir(realName);
		System.out.println(dir);// /0/2
		
		
	}
}
