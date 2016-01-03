package pinkteampdm.farmerhelper;

import java.util.Vector;

public class Culture {
    private String culture_name;
    private String activity_name;
    private String moon;
    private String country_zone;
    private Vector<String> place;

    public Culture(String activity_name, String moon, String country_zone, String culture_name){
        this.activity_name = activity_name;
        this.moon = moon;
        this.country_zone = country_zone;
        this.culture_name = culture_name;
        place = new Vector<>();
    }

    public String getAct_name(){
        return activity_name;
    }

    public String getMoon(){
        return moon;
    }

    public String getCulture_name(){
        return culture_name;
    }

    public String getCountry_zone(){
        return country_zone;
    }

    public String getPlaceString(){
        String allPlaces = "";
        for(int i=0; i<place.size(); i++){
            if (i == place.size()-1){
                allPlaces += place.elementAt(i);
                break;
            }
            allPlaces += place.elementAt(i) + ",";
        }
        return allPlaces;
    }

    public void addPlace(String newPlace){
        place.addElement(newPlace);
    }
}
