package com.ducky.util;

import com.ducky.logic.DuckyException;

/**
 * An exception thrown when a problematic save file is detected.
 */
public class DuckyFileParseException extends DuckyException {

    public DuckyFileParseException() {
        super("Oh no! There was a problem reading your file. It might be corrupted.");
    }
}
