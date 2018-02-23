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
   List<Event> eventList = new ArrayList<>();

   public void addEventToList(Event e){
       eventList.add(e);
       data.File f = new data.File(eventList, 0);
       f.saveEventData();

   }

    public void registerUser(User e){
        usersList.add(e);
        data.File f = new data.File(usersList);
        f.saveData();

    }

   public List<Event> getEventList(){
       return eventList;
   }

   public Event getEventById(int id){
       for (int i = 0; i < eventList.size(); i++){
           if (eventList.get(i).getId() == id){
               return eventList.get(i);
           }
       }
       return null;
   }

    public void deleteEventById(int id){
        for (int i = 0; i < eventList.size(); i++){
            if (eventList.get(i).getId() == id){
                 eventList.remove(i);
            }
        }
    }


   public boolean userSignIn(String _username, String _password) {
       data.File f = new data.File();
       this.usersList = f.getData();

       boolean correct = false;
       int size = this.usersList.size();
       for (int i = 0; i < size; i++) {
           User user = this.usersList.get(i);
           if (_username.equals(user.getUsername()) && user.getPassword().equals(_password)) {
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
           if (_username.equals( user.getUsername())) {
               valid = false;
               break;
           }
       }
       return valid;
   }
   
   
}
