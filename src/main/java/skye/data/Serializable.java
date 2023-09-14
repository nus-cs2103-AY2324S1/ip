package skye.data;

/**
 * Represents a  for encodable objects
 */
public interface Serializable {
    /**
     * Generates an encoded string containing metadata of the object to be saved in a text file.
     *
     * @return Encoded string representation of the object
     */
    public String toSaveDataFormat();
}
