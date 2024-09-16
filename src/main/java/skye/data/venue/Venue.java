package skye.data.venue;

import skye.data.Serializable;

/**
 * Represents a venue that the user wishes to keep track of.
 */
public class Venue implements Serializable {
    private String name;
    private String address;
    private int capacity;
    private double rentalCost;

    /**
     * Initializes a venue object.
     *
     * @param name The name of the venue
     * @param address The address of the venue
     * @param capacity The capacity of the venue
     * @param rentalCost The cost of renting the venue
     */
    public Venue(String name, String address, int capacity, double rentalCost) {
        this.name = name;
        this.address = address;
        this.capacity = capacity;
        this.rentalCost = rentalCost;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public int getCapacity() {
        return capacity;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    /**
     * Encodes the venue object into a formatted string to be saved into a text file.
     *
     * @return Encoded string of Venue object
     */
    @Override
    public String toSaveDataFormat() {
        return String.format("V | %s | %s | %d | %f", getName(), getAddress(), getCapacity(), getRentalCost());
    }

    @Override
    public String toString() {
        return String.format("%s (Capacity: %d, Cost: %.2f)", getName(), getCapacity(), getRentalCost());
    }

    /**
     * Checks if another Venue is equivalent to the current venue.
     *
     * @param other The other Venue object we are checking for equivalence
     * @return if the other Venue object is equivalent to the current object
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else if (other instanceof Venue) {
            Venue otherVenue = (Venue) other;
            boolean isSameName = this.name.equals(otherVenue.name);
            boolean isSameAddress = this.address.equals(otherVenue.address);
            boolean isCapacityEqual = this.capacity == otherVenue.capacity;
            boolean isRentEqual = this.rentalCost == otherVenue.rentalCost;
            return (isSameName && isSameAddress && isCapacityEqual && isRentEqual);
        } else {
            return false;
        }
    }
}
