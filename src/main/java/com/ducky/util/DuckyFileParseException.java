package com.ducky.util;

import com.ducky.common.DuckyException;

/**
 * An exception thrown when a problematic save file is detected.
 */
public class DuckyFileParseException extends DuckyException {

    private static final String FILE_PARSE_ERROR_MSG =
            "Oh no! There was a problem reading your file. It might be corrupted.";
    public DuckyFileParseException() {
        super(FILE_PARSE_ERROR_MSG);
    }
}
