package duke;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Represents a parser for user input in the Duke program.
 */
public class Parser {
    private String str;
    private String arr[];
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<Task> list;

    /**
     * Constructs a Parser object with the specified list of tasks and reads user input.
     *
     * @param list The list of tasks.
     * @throws Exception If an error occurs while reading user input.
     */
    public Parser(List<Task> list) throws Exception {
        this.list = list;
        this.str = reader.readLine();
        this.arr = str.split(" ", 2);
    }

    /**
     * Gets the user input string.
     *
     * @return The user input string.
     */
    public String getStr() {
        return this.str;
    }

    /**
     * Gets an array containing the user input split into two parts.
     *
     * @return An array containing user input parts.
     */
    public String[] getArr() {
        return this.arr;
    }
}
