package com.example.captonesecondstage.Validation;

import java.util.regex.Pattern;

public class ValidationData {
    //we consider the name is correct when the name contains at least 3 letters..
    public static boolean isCorrectName(String name){
        return name !=null&&name.length()>=3&&Character.isLetter(name.charAt(0))
                &&Character.isLetter(name.charAt(1))&&Character.isLetter(name.charAt(2));
    }
    //we conseider the phone is correct if it's contains number only and the length is more than 6 and less thean 10
    public static boolean isCorrectPhone(String phone){
        if(phone!=null) {
            for (int i = 0; i < phone.length(); i++) {
                if (Character.isLetter(phone.charAt(i))) {
                    return false;
                }
            }
        }
        return phone.length()>=6&&phone.length()<=10;
    }
    //we consider the email is valid if it's the same  as the pattern
    public static boolean isCorrectEmail(String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";

        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    //we consider the password is correct if it's have at least 2 Capital letters and the length more than or equal 6..
    public static boolean isCorrectPassword(String password){
        int count=0;
        if(password==null){return false;}
            for(int i=0;i<password.length();i++){
               if(Character.isUpperCase(password.charAt(i)))count++;
            }

        return count>=2&&password.length()>=6;
    }
    //we consider the address it's correct if it's also contains at least 3 character
    public static boolean isCorrectAddress(String address){
        int count=0;
        if(address==null){return false;}
            for(int i=0;i<address.length();i++){
                if(Character.isLetter(address.charAt(i))){
                    count++;
                }
            }

        return count>=3;
    }
    public static  boolean isCorrectDesciption(String description){
        return description!=null&&description.length()>=8;
    }
    public static boolean isCorrectCources(String courses){
        return isCorrectName(courses);
    }

}
