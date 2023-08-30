package ducky.util;

import ducky.DuckyException;

public class DuckyFileParseException extends DuckyException {

    public DuckyFileParseException() {
        super("Oh no! There was a problem reading your file. It might be corrupted.");
    }
}
