package cloud.main.token;

import java.util.List;

import cloud.main.todo.Todo;



/**
 * Represents a single word of user input.
 */
public class Token {
    /** The prefix for flags */
    public static final String PREFIX_FLAG = "/";

    private String token;

    public Token(String _token) {
        this.token = _token;
    }

    private Integer toInt() {
        try {
            return Integer.parseInt(token);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public String get() {
        return this.token;
    }

    /**
     * Whether the token can be converted to a valid integer.
     */
    public boolean isInt() {
        Integer integer = this.toInt();
        return integer != null;
    }

    /**
     * Returns the token as an integer.
     *
     * @return Defaults to -1 if the token cannot be converted to a valid integer.
     */
    public int asInt() {
        if (!this.isInt()) return -1;

        return this.toInt();
    }

    /**
     * Whether the token is a valid TODO number.
     *
     * @param todos The list of Todos.
     */
    public boolean isValidNumber(List<Todo> todos) {
        if (!this.isInt()) return false;

        int number = this.asInt();

        if (number < 0 || number > todos.size()) {
            return false;
        }
        return true;
    }

    /**
     * Whether the token is a flag.
     */
    public boolean isFlag() {
        return this.token.startsWith(Token.PREFIX_FLAG);
    }

    /**
     * Returns the text portion of the flag.
     *
     * @return Defaults to "" if the token is not actually a flag.
     */
    public String getFlag() {
        if (!this.isFlag()) return "";

        return this.token.substring(1);
    }
}
