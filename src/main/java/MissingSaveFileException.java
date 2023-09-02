public class MissingSaveFileException extends CrusaderException {
    public MissingSaveFileException(String path) {
        super(String.format("No save file could be found at path %s !", path));
    }
}
