package com.cloud.chatbot.token;

import java.util.HashMap;
import java.util.List;

import com.cloud.chatbot.annotation.Nullable;
import com.cloud.chatbot.exception.MissingInputException;



/**
 * Manages all the Tokens representing an instance of user input.
 *
 * The command is the head, while the details are the tail.
 *
 * A flag set is a flag and its sub input that have been processed. Eg "/by
 * Tomorrow night" split into tokens is a flag set, where the flag is "/by"
 * whose flag text is "by", and the sub input is "Tomorrow night".
 */
public class CommandManager extends TokenManager {
    // Index by flag text instead of Token for faster retrieval
    private HashMap<String, FlagManager> flagSets = new HashMap<>();

    /**
     * @param input The full instance of user input.
     */
    public CommandManager(String input) {
        this.tokens = TokenManager.stringToTokens(input);

        // Go through tokens to extract flag sets
        int i = 0;
        while (i < this.tokens.size()) {
            Token token = this.tokens.get(i);

            if (token.isFlag()) {
                int endIndex = this.findFlagSetEnd(i);
                List<Token> flagSet = this.removeTokens(i, endIndex);

                FlagManager manager = new FlagManager(flagSet);
                this.flagSets.put(
                    manager.getFlagText(),
                    manager
                );

                continue;
            }

            i++;
        }
    }

    private int findFlagSetEnd(int startIndex) {
        int lastIndex = this.tokens.size() - 1;
        int movingIndex = startIndex + 1;

        while (movingIndex <= lastIndex) {
            Token token = this.tokens.get(movingIndex);
            if (token.isFlag()) {
                break;
            }

            movingIndex++;
        }

        // Start index inclusive, end index exclusive
        return movingIndex;
    }

    /**
     * Returns the head as the command (lowercase).
     *
     * @throws MissingInputException If there is no command.
     */
    public String getCommand() throws MissingInputException {
        return this.getHead().toLowerCase();
    }

    /**
     * Returns the tail as the details.
     *
     * @throws MissingInputException If there are no details.
     */
    public String getDetails() throws MissingInputException {
        return this.getTail();
    }

    /**
     * Returns the FlagManager for the specified flag, if it exists.
     *
     * @return null if no such flag exists.
     */
    public @Nullable FlagManager findFlag(String flagText) {
        return this.flagSets.get(flagText);
    }
}
