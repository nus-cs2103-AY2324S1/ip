package Duke.Exceptions;

public class UnrecognisedFormatException extends Exception {

    public UnrecognisedFormatException() {
        super("Sorry!!! This data file is corrupted and we do not recognise the format of the file.");
    }

}
