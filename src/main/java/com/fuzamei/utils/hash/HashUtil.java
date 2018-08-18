package com.fuzamei.utils.hash;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * 
 * @author ylx
 * @Descri 文件hash计算工具类
 */
public class HashUtil {
	private HashUtil(){
		throw new AssertionError("instantiation is not permitted");
	}
	/** 
     * 根据文件计算出文件的MD5
     * 文件hash的值和文件名没有关系
     * @param file 
     * @return 
     */  
    public static final String getFileMD5(File file) {  
    	if(!file.exists()){
    		throw new RuntimeException("该文件不存在");
    	}
        if (!file.isFile()) {  
        	throw new RuntimeException("无法对文件夹进行hash计算");
        }  
        MessageDigest digest = null;  
        FileInputStream in = null;  
        byte[] buffer = new byte[1024];  
        int len;  
        try {  
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);  
            while ((len = in.read(buffer)) != -1) {  
                digest.update(buffer, 0, len);  
            }  
        } catch (Exception e) {  
        	throw new RuntimeException("计算文件hash出错");
        }finally {
			if(in!=null){
				try {
					in.close();
				} catch (IOException e) {
					in = null;
				}
			}
		}
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);//转换成16进制
    }
    
    public static final String getFileMD5(String filePath){
    	return getFileMD5(new File(filePath));
    }
	
    /** 
     * 计算字符串的hash值
     * @param str
     * @return 
     */
    public static final String getStringMD5(String str) {
    	MessageDigest digest = null;  
        try {  
            digest = MessageDigest.getInstance("MD5");
            digest.update(str.getBytes());
        } catch (Exception e) {  
        	throw new RuntimeException("计算文件hash出错");
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());  
        return bigInt.toString(16);//转换成16进制
    }

}
