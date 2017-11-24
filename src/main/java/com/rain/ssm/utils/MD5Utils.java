package com.rain.ssm.utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    /**
     * md5加密工具
     * @param pwd
     * @return
     */
    public static String getMD5(String pwd){
        StringBuilder sb = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(pwd.getBytes());
            byte[] hash = md.digest();
            
            int i =0;
            for(int offset = 0;offset <hash.length;offset++){
                i = hash[offset];
                if(i<0){
                    i+=256;
                }
                if(i<16){
                    sb.append("0");
                }
                sb.append(Integer.toHexString(i));
            }
            
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return sb.toString();
    }
}
