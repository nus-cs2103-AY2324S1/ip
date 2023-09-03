package com.cloud.chatbot.token;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cloud.chatbot.exceptions.MissingInputException;



/**
 * A general parent Token manager providing children useful shared methods.
 */
public abstract class TokenManager {
    protected List<Token> tokens = new ArrayList<>();

    public TokenManager() {}

    protected static String tokensToString(List<Token> tokens) {
        return tokens
            .stream()
            .map(Token::toString)
            .collect(Collectors.joining(" "));
    }

    /**
     * Returns the first token as a String.
     *
     * @throws MissingInputException If there is no first token.
     */
    protected String getHead() throws MissingInputException {
        if (this.getTokenCount() <= 0) {
            throw new MissingInputException();
        }

        return this.tokens.get(0).toString();
    }

    /**
     * Returns all tokens except the first as a rejoined String.
     *
     * @throws MissingInputException If there are no relevant tokens to join.
     */
    protected String getTail() throws MissingInputException {
        if (this.getTokenCount() <= 1) {
            throw new MissingInputException();
        }

        List<Token> tailTokens = new ArrayList<>(this.tokens);
        tailTokens.remove(0);
        return TokenManager.tokensToString(tailTokens);
    }

    /**
     * Removes and returns all tokens between the specified indices.
     *
     * @param startIndex The index to start from (inclusive).
     * @param endIndex The index to end before (exclusive).
     */
    protected List<Token> removeTokens(int startIndex, int endIndex) {
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
        return TokenManager.tokensToString(this.tokens);
    }

    /**
     * Returns the Token at the specified index.
     *
     * @param index The index.
     */
    public Token getToken(int index) {
        return this.tokens.get(index);
    }

    /**
     * Returns the total number of Tokens.
     */
    public int getTokenCount() {
        return this.tokens.size();
    }
}
