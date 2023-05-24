package com.dan.api_example.util;

import java.security.MessageDigest;

public class SHA256 {
    public static String getEncrypt(String plainText){
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256"); //"SHA256" 인스턴스 생성
            md.update(plainText.getBytes()); //SaltedPlainText는 비밀번호 + salt이고 이것을 해싱하는것
            byte[] byteData = md.digest(); //바이트 배열로 해쉬를 반환.

            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < byteData.length; ++i) {
                String hex = Integer.toHexString(255 & byteData[i]); // 각 바이트마다 Hex값으로 변환
                if (hex.length() == 1) {
                    hexString.append('0');
                }
                hexString.append(hex);
            }
            return hexString.toString();

        } catch (Exception var7) {
            var7.printStackTrace();
            throw new RuntimeException();
        }
    }
}
