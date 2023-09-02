package crusader.exception;

public class CrusaderDateFormatException extends CrusaderParseException {
    public CrusaderDateFormatException() {
        super("I only understand dates in the format dd/mm/yyyy hh(24 hours)!");
    }
}
