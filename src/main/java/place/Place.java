package place;

import java.util.ArrayList;

public abstract class Place {
    private static ArrayList<Place> placeArrayList = new ArrayList<>();

    public abstract String getName();

    public abstract String editDesc(String newDesc);
    public boolean checkIfPlaceExists(Place place) {
        return placeArrayList.contains(place);
    }

    public int getPlace(String name) {
        int index = -1;
        for (Place place : placeArrayList) {
            if (place.getName().equals(name)) {
                index = placeArrayList.indexOf(place);
            }
        }
        return index;
    }

    public static String list() {
        String returnString = "";
        for (Place place : placeArrayList) {
            returnString += place.getName() + ": " + place.toString();
        }
        return returnString;
    }

    public boolean checkIfNameExists(Place place) {
        boolean isFound = false;
        for (Place existingPlace : placeArrayList) {
            if (place.getName().equals(existingPlace.getName())) {
                isFound = true;
            }
        }
        return isFound;
    }

    public String addPlace() {
        if (checkIfPlaceExists(this)) {
            return "You have visited this place before! It is already in the list.";
        } else if (checkIfNameExists(this)) {
            return placeArrayList.get(getPlace(this.getName())).editDesc(this.toString());
        } else {
            placeArrayList.add(this);
            return "Nice! This is a new location. Adding it now.";
        }
    }

}
