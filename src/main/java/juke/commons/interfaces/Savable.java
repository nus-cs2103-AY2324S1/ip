package juke.commons.interfaces;

/**
 * Denotes a class that can be saved to the datafile.
 */
public interface Savable {
    /**
     * Returns the string which represents this object when it is saved into the datafile.
     *
     * @return Datafile representation of this object
     */
    String save();
}
