package entity;
import java.util.ArrayList;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Johanes
 */
public class PlaceOfInterest {
    private String name;
    private String location;
    private String information;
    private ArrayList<String> service;
    private int row;
    private int col;

    public PlaceOfInterest(int row, int col, String name, String location, String information) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.location = location;
        this.information = information;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ArrayList<String> getService() {
        return service;
    }

    public void setService(ArrayList<String> service) {
        this.service = service;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }
    
    @Override
    public String toString() {
        String output = "["+getRow()+","+getCol()+" -> "+getLocation()+"]  "+getName()+" - "+getInformation()+"\n";
        String servStr = "SERVICE: ";
        for(String serv:service){
            servStr = servStr + " " + serv + ",";
        }
        
        return output+servStr+"\n";
    }
    
    
}
