package crusader.exception;

/**
 * Represents an error due to lack of an existing save file.
 */
public class CrusaderMissingSaveFileException extends CrusaderException {
    public CrusaderMissingSaveFileException(String path) {
        super(String.format("No save file could be found at path %s !", path));
    }
}
