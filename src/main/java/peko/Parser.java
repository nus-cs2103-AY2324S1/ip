package peko;

import java.util.Scanner;

public class Parser {

    private String string;
    private static final String[] commands = new String[]
            {"echo","otsupeko", "list", "write", "mark", "unmark",
                    "todo", "deadline", "event", "find", "delete","tell me a joke"};
    public Parser(String s) {
        this.string = s;
    }
    public Commands getResponseValue() {

        if (string.toLowerCase().equals("tell me a joke")) {
            return Commands.COPYPASTA;
        }
        int output = 0;
        string = string.toLowerCase();
        for (int i = 0; i < commands.length; i++) {
            if (string.startsWith(commands[i])) {
                output = i;
                break;
            }
        }
        String temp = commands[output].toUpperCase().trim();
        return Commands.valueOf(temp);
    }
    public String getDescription() {
        return string.split(" ",2).length < 2 ? " " : string.split(" ", 2)[1];
    }
}
