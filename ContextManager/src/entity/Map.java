package entity;


import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Hashtable;

// TODO: Auto-generated Javadoc
/**
 * Represents the Map entity. This class contains info of all POI
 * that GPS can query.
 * @author Johanes
 */
public class Map {
    
    /** The places. */
    private Hashtable<String, ArrayList<PlaceOfInterest>> places;
    
    /** The reader. */
    private BufferedReader reader;
    
    /** The map location. */
    private String mapLocation = "";
    
    /** The loc def. */
    private Hashtable<String, RowColLocation> locDef;
    
    /** The Constant ID_INDEX. */
    final static int ID_INDEX = 0;
    
    /** The Constant ROW_INDEX. */
    final static int ROW_INDEX = 1;
    
    /** The Constant COL_INDEX. */
    final static int COL_INDEX = 2;
    
    /** The Constant LOCATION_INDEX. */
    final static int LOCATION_INDEX = 3;
    
    /** The Constant NAME_INDEX. */
    final static int NAME_INDEX = 4;
    
    /** The Constant INFORMATION_INDEX. */
    final static int INFORMATION_INDEX = 5;
    
    /**
     * Instantiates a new map.
     *
     * @param mapLocation the map location
     */
    public Map(String mapLocation) {
        this.mapLocation = mapLocation;
        places = new Hashtable<String, ArrayList<PlaceOfInterest>>();
        
        //init every array in every coordinate;        
        for(char charIdx = 'A';charIdx<'J';charIdx++){
            places.put(""+charIdx, new ArrayList<PlaceOfInterest>());
        }
        
        try {
            String data = "";
            reader = new BufferedReader(new FileReader(mapLocation));
            String line = null;
            while ((line = reader.readLine()) != null) {
                parseMapLine(line);
            }
        } catch (FileNotFoundException ex) {
        	ex.printStackTrace();
        } catch (IOException ioex) {
        	ioex.printStackTrace();
        }
        
        locDef = new Hashtable<String, RowColLocation>(); 
        locDef.put("A", new RowColLocation(0,1));
        locDef.put("B", new RowColLocation(0,2));
        locDef.put("C", new RowColLocation(1,0));
        locDef.put("D", new RowColLocation(1,1));
        locDef.put("E", new RowColLocation(1,2));
        locDef.put("F", new RowColLocation(2,2));
        locDef.put("G", new RowColLocation(2,3));
        locDef.put("H", new RowColLocation(3,1));
        locDef.put("I", new RowColLocation(3,2));
        
    }

    /**
     * Parses the map line.
     *
     * @param line the line
     */
    private void parseMapLine(String line) {
        String[] str = line.split(",");
        if (str.length >= 5) {
        	String id = str[ID_INDEX];
            int row = Integer.parseInt(str[ROW_INDEX]);
            int col = Integer.parseInt(str[COL_INDEX]);
            String location = str[LOCATION_INDEX];

            PlaceOfInterest poi = new PlaceOfInterest(id, row, col, str[NAME_INDEX], location, str[INFORMATION_INDEX]);
            ArrayList<String> services = new ArrayList<String>();
            for (int i = 5; i < str.length; i++) {
                services.add(str[i]);
            }
            poi.setService(services);
            ArrayList<PlaceOfInterest> currPlaces = places.get(location);
            currPlaces.add(poi);
            places.put(location, currPlaces);
        }
    }
    
    /**
     * Gets the by location.
     *
     * @param location the location
     * @return the by location
     */
    public ArrayList<PlaceOfInterest> getByLocation(String location){
        return places.get(location);
    }
    
    /**
     * Gets the by id.
     *
     * @param id the id
     * @return the by id
     */
    public PlaceOfInterest getByID(String id){
    	for(ArrayList<PlaceOfInterest> pois : places.values()){
    		for(int i=0;i<pois.size();i++){
    			if(pois.get(i).getId().equals(id)){
    				return pois.get(i);
    			}
    		}
    	}
    	return null;
    }
    
    /**
     * Gets the by name.
     *
     * @param name the name
     * @return the by name
     */
    public PlaceOfInterest getByName(String name){
    	for(ArrayList<PlaceOfInterest> pois : places.values()){
    		for(int i=0;i<pois.size();i++){
    			if(pois.get(i).getName().equalsIgnoreCase(name)){
    				return pois.get(i);
    			}
    		}
    	}
    	return null;
    }
    
    /**
     * Gets the compass directive.
     *
     * @param from the from
     * @param to the to
     * @return the compass directive
     */
    public static String getCompassDirective(RowColLocation from, PlaceOfInterest to){
        if(from.getRow()==to.getRow()){
            if(from.getCol()>to.getCol()){
                return "UTARA";
            }else if(from.getCol()<to.getCol()){
                return "SELATAN";
            }else{
                return "POSISI SAMA";
            }
        }else if(from.getCol()==to.getCol()){
            if(from.getRow()>to.getRow()){
                return "TIMUR";
            }else if(from.getRow()<to.getRow()){
                return "BARAT";
            }else{
                return "POSISI SAMA";
            }
        }else if(from.getCol()>to.getCol()){
            if(from.getRow()>to.getRow()){
                return "BARAT LAUT";
            }else{
                return "BARAT DAYA";
            }
        }else if(from.getCol()<to.getCol()){
            if(from.getRow()>to.getRow()){
                return "TIMUR LAUT";
            }else{
                return "TENGGARA";
            }
        }
        return null;
    }
    
    /**
     * Gets the compass directive.
     *
     * @param from the from
     * @param to the to
     * @return the compass directive
     */
    public static String getCompassDirective(PlaceOfInterest from, PlaceOfInterest to){
        if(from.getRow()==to.getRow()){
            if(from.getCol()>to.getCol()){
                return "UTARA";
            }else if(from.getCol()<to.getCol()){
                return "SELATAN";
            }else{
                return "POSISI SAMA";
            }
        }else if(from.getCol()==to.getCol()){
            if(from.getRow()>to.getRow()){
                return "TIMUR";
            }else if(from.getRow()<to.getRow()){
                return "BARAT";
            }else{
                return "POSISI SAMA";
            }
        }else if(from.getCol()>to.getCol()){
            if(from.getRow()>to.getRow()){
                return "BARAT LAUT";
            }else{
                return "BARAT DAYA";
            }
        }else if(from.getCol()<to.getCol()){
            if(from.getRow()>to.getRow()){
                return "TIMUR LAUT";
            }else{
                return "TENGGARA";
            }
        }
        return null;
    }
    
}
