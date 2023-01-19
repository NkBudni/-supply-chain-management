package com.example.supplychainsystem;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.sql.ResultSet;

public class Login {

    DataBaseConnection dbConn= new DataBaseConnection();

    public static byte[] getSHA(String input){
        try {
            MessageDigest md= MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
            }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    static String getEncryptedPassword(String password){
        String encryptedPassword="";
        BigInteger number =new BigInteger(1,getSHA(password));
        StringBuilder  hexString = new StringBuilder(number.toString(16));
        encryptedPassword= hexString.toString();
        return  encryptedPassword;
    }

    public boolean customerLogin(String email, String password){
      try{
          String query = String.format("SELECT * FROM customer Where email = '%s' and password = '%s'", email, getEncryptedPassword(password));
          ResultSet rs= dbConn.getQueryTable(query);

//          System.out.println(getEncryptedPassword(password));
          if(rs==null){
//              System.out.println(1);
              return false;
          }
          if(rs.next()){
//              System.out.println(2);
              return true;
          }
          else {
//              System.out.println(3);
              return false;
          }
      }catch (Exception e){
          e.printStackTrace();
          return false;
      }
    }

//    public static void main(String[] args) {
////        Login lg=new Login();
//
//
//        System.out.println(getEncryptedPassword("abc123"));
//    }
}
