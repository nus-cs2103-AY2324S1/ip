package com.cloud.chatbot.token;

import java.util.List;

import com.cloud.chatbot.exceptions.MissingFlagInputException;
import com.cloud.chatbot.exceptions.MissingInputException;



/**
 * Manages a flag set for a CommandManager.
 *
 * The flag is the head, while the sub input is the tail.
 */
public class FlagManager extends TokenManager {
    public FlagManager(List<Token> flagSet) {
        // Assumption: There is a first token and it is a flag.
        // Assumption: The passed list will not be mutated externally
        this.tokens = flagSet;
    }

    /**
     * Returns the text portion of the flag.
     */
    public String getFlagText() {
        return this.tokens.get(0).getFlagText();
    }

    /**
     * Returns the tail as the sub input.
     *
     * @throws MissingFlagInputException If there is no sub input.
     */
    public String getSubInput() throws MissingFlagInputException {
        try {
            return this.getTail();
        } catch (MissingInputException e) {
            throw new MissingFlagInputException(this);
        }
    }
}
