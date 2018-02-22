/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author danielalvarado
 */
public class Main {
   //Descripcion de metodos utilies.  
   List<User> usersList = new ArrayList<>();
   
   public boolean userSignIn(String _username, String _password) {
       boolean correct = false;
       int size = this.usersList.size();
       for (int i = 0; i < size; i++) {
           User user = this.usersList.get(i);
           if (_username == user.getUsername() && user.getPassword() == _password) {
               correct = true;
               break;
           }
       }
       return correct;
   }
   
   public boolean validUsername(String _username) {
       boolean valid =  true;
       int size = this.usersList.size();
       for (int i = 0; i < size; i++) {
           User user = this.usersList.get(i);
           if (_username == user.getUsername()) {
               valid = false;
               break;
           }
       }
       return valid;
   }
   
   
}
