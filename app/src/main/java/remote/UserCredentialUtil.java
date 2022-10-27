package remote;

import model.User;

public class UserCredentialUtil {

     private  User user;

     public void setCurrentUser(User user){
         this.user = user;
     }

     public User getUser(){

         return user;
     }
}
