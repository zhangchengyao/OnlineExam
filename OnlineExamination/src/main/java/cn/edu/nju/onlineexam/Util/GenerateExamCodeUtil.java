package cn.edu.nju.onlineexam.Util;

public class GenerateExamCodeUtil {

    public static String getRandomString(int length){
        String pattern = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        StringBuilder stringBuffer = new StringBuilder();

        int len = pattern.length();

        for(int i=0;i<length;i++){
            stringBuffer.append(pattern.charAt(getRandomIndex(len-1)));
        }
        return stringBuffer.toString();
    }

    private static int getRandomIndex(int count){
        return (int)Math.round(Math.random()*count);
    }
}
