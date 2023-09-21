package com.cloud.chatbot.token;

import java.util.ArrayList;
import java.util.List;

import com.cloud.chatbot.exception.MissingFlagInputException;
import com.cloud.chatbot.exception.MissingInputException;



/**
 * Manages a flag set for a CommandManager.
 *
 * The flag is the head, while the sub input is the tail.
 */
public class FlagManager extends TokenManager {
    /**
     * @param flagSet The flag set to manage.
     */
    public FlagManager(List<Token> flagSet) {
        // There must be a first token
        assert flagSet.size() > 0;

        // The first token must be a flag;
        assert flagSet.get(0).isFlag();

        this.tokens = new ArrayList<>(flagSet);
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
