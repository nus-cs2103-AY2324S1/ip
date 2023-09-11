package com.cloud.chatbot.exception;

import com.cloud.chatbot.token.FlagManager;



/**
 * Thrown when the user does not specify all required sub inputs for a flag.
 */
public class MissingFlagInputException extends MissingInputException {
    private FlagManager manager;

    /**
     * @param _manager The FlagManager that threw this exception.
     */
    public MissingFlagInputException(FlagManager _manager) {
        this.manager = _manager;
    }

    /**
     * Returns the text portion of the associated flag.
     */
    public String getFlagText() {
        return manager.getFlagText();
    }
}
