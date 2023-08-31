package cloud.main;

import java.util.List;

import cloud.main.todo.Todo;



/**
 * Represents a single word of user input.
 */
public class Token {
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

    public void set(String _token) {
        this.token = _token;
    }

    /**
     * Whether the token can be converted to a valid integer.
     */
    public boolean isInt() {
        Integer integer = this.toInt();
        return integer != null;
    }

    /**
     * Returns the token as an integer. Defaults to -1 if the token cannot be
     * converted to a valid integer.
     */
    public int asInt() {
        if (!isInt()) return -1;

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
}
