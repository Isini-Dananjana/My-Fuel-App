package remote;

import model.User;

public class UserCredentialUtil {

     private  static User user;

     public static void setCurrentUser(User userObj){

         user = userObj;
     }

     public static User getUser(){

         return user;
     }
}
