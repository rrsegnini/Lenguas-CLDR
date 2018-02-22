/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author danielalvarado
 */
public class User {
    private String username;
    private String password;
    private List<Event> userEvents = new ArrayList<>();

    
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    protected String getPassword() {
        return password;
    }

    protected void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" + "username=" + username + '}';
    }
    
    public List<Event> getUserEvents() {
        return userEvents;
    }

    public boolean eliminateEvent(Date _date, String _name) {
        boolean eliminated = false;
        for (int i =0;i < this.userEvents.size(); i++) {
            Event e = this.userEvents.get(i);
            if (e.getDate() == _date && e.getName() == _name) {
                eliminated = true;
                this.userEvents.remove(i);
                break;
            
            }
        }
        return eliminated;
    }
    
    private boolean eventExistis(Date _date, String _name) {
    boolean exists = false;
        for (int i =0;i < this.userEvents.size(); i++) {
            Event e = this.userEvents.get(i);
            if (e.getDate() == _date && e.getName().equals(_name)) {
                exists = true;
                break;
            
            }
        }
        return exists;
    }
    
    public boolean addEvent(Date _date, String _name, String _descrp, String _place) {
        boolean added = false;
        if (!this.eventExistis(_date, _name)) {
            Event newEvent = new Event(_date,_name,_descrp,_place);
            this.userEvents.add(newEvent);
            added = true;
        }
        
        return added;
        
    }
}
