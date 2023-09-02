package crusader.exception;

public class CrusaderMissingSaveFileException extends CrusaderException {
    public CrusaderMissingSaveFileException(String path) {
        super(String.format("No save file could be found at path %s !", path));
    }
}
