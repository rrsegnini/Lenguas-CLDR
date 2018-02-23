/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.util.Date;

/**
 *
 * @author danielalvarado
 */
public class Event implements java.io.Serializable{
    private static int idGlobal; //Autoincremental identification number
    private int id;
    private Date date;
    private String name;
    private String user;
    private String place;

    public Event(Date date, String name, String user, String place) {
        idGlobal++;
        this.id = idGlobal;

        this.date = date;
        this.name = name;
        this.user = user;
        this.place = place;
    }
    public int getId(){
        return id;
    }
    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser() {
        return user;
    }

    public void setDescrp(String descrp) {
        this.user = descrp;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }
    
    
    
}
