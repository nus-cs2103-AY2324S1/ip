package com.cloud.chatbot.token;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;



/**
 * Manages all the Tokens representing an instance of user input.
 *
 * Flag sets are stored separate from the list of Tokens. The first token is understood to be the
 * flag, whose flag text is used as the key. The remaining tokens are the sub input, stored in a
 * nested TokenManager.
 */
public class TokenManager {
    private List<Token> tokens = new ArrayList<>();
    private HashMap<String, TokenManager> flagSets = new HashMap<>();

    public TokenManager(String input) {
        String[] words = input.split(" ");

        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            Token token = new Token(word);
            this.tokens.add(token);
        }

        // Go through tokens to extract flag sets
        int i = 0;
        while (i < tokens.size()) {
            Token token = this.tokens.get(i);

            if (token.isFlag()) {
                int endIndex = this.findFlagSetEnd(i);
                List<Token> flagSet = this.removeTokens(i, endIndex);

                // This removal degrades the flag set into a sub input
                Token flag = flagSet.remove(0);
                this.flagSets.put(
                    flag.getFlag(),
                    new TokenManager(flagSet)
                );
                continue;
            }

            i++;
        }
    }

    public TokenManager(List<Token> _tokens) {
        this.tokens = _tokens;
    }

    private int findFlagSetEnd(int startIndex) {
        int lastIndex = this.tokens.size() - 1;
        int movingIndex = startIndex + 1;

        while (movingIndex <= lastIndex) {
            Token token = this.tokens.get(movingIndex);
            if (token.isFlag()) break;

            movingIndex++;
        }

        // Start index inclusive, end index exclusive
        return movingIndex;
    }

    /**
     * Removes and returns all tokens between the specified indices.
     *
     * @param startIndex The index to start from (inclusive).
     * @param endIndex The index to end before (exclusive).
     */
    private List<Token> removeTokens(int startIndex, int endIndex) {
        int removeCount = endIndex - startIndex;
        List<Token> removed = new ArrayList<>();
        while (removeCount > 0) {
            Token token = this.tokens.remove(startIndex);
            removed.add(token);

            removeCount--;
        }
        return removed;
    }

    @Override
    public String toString() {
        return this.tokens
                .stream()
                .map(Token::get)
                .collect(Collectors.joining(" "));
    }

    public List<Token> getTokens() {
        return this.tokens;
    }

    /**
     * Returns the first token, understood to be the command.
     *
     * @return Defaults to "" if the user input is too short.
     */
    public String getCommand() {
        if (this.tokens.size() <= 0) {
            return "";
        }

        return this.tokens.get(0).get();
    }

    /**
     * Returns the TokenManager for the specified flag, if it exists.
     *
     * @return Defaults to null if no such flag exists.
     */
    public TokenManager findFlag(String flag) {
        return this.flagSets.get(flag);
    }
}
