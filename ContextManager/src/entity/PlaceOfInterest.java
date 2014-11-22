package entity;
import java.util.ArrayList;

// TODO: Auto-generated Javadoc
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 * The Class PlaceOfInterest.
 * Represents an entity for a Place Of Interest that located in a location of a map.
 * @author Johanes
 */
public class PlaceOfInterest {
	
	/** The id. */
	private String id;
    
    /** The name. */
    private String name;
    
    /** The location. */
    private String location;
    
    /** The information. */
    private String information;
    
    /** The service. */
    private ArrayList<String> service;
    
    /** The row. */
    private int row;
    
    /** The col. */
    private int col;

    /**
     * Instantiates a new place of interest.
     *
     * @param id the id
     * @param row the row
     * @param col the col
     * @param name the name
     * @param location the location
     * @param information the information
     */
    public PlaceOfInterest(String id, int row, int col, String name, String location, String information) {
        this.row = row;
        this.col = col;
        this.name = name;
        this.location = location;
        this.information = information;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public String getId() {
		return id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
        return name;
    }

    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets the location.
     *
     * @param location the new location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets the information.
     *
     * @return the information
     */
    public String getInformation() {
        return information;
    }

    /**
     * Sets the information.
     *
     * @param information the new information
     */
    public void setInformation(String information) {
        this.information = information;
    }

    /**
     * Gets the service.
     *
     * @return the service
     */
    public ArrayList<String> getService() {
        return service;
    }

    /**
     * Sets the service.
     *
     * @param service the new service
     */
    public void setService(ArrayList<String> service) {
        this.service = service;
    }

    /**
     * Gets the row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets the row.
     *
     * @param row the new row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets the col.
     *
     * @return the col
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the col.
     *
     * @param col the new col
     */
    public void setCol(int col) {
        this.col = col;
    }
    
    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
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
