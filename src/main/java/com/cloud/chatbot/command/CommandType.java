package com.cloud.chatbot.command;



/**
 * Represents all possible command types, including unknown commands.
 */
public enum CommandType {
    ADD,
    LIST,
    FIND,
    MARK,
    UNMARK,
    DELETE,
    EXIT,
    UNKNOWN;

    /**
     * Returns the matching CommandType for the specified command.
     *
     * @param command The command.
     */
    public static CommandType fromString(String command) {
        switch (command) {
        case "todo":
        case "t":
        case "deadline":
        case "d":
        case "event":
        case "e":
        case "add":
            return CommandType.ADD;
        case "list":
        case "l":
            return CommandType.LIST;
        case "find":
        case "f":
        case "search":
        case "s":
            return CommandType.FIND;
        case "mark":
        case "m":
            return CommandType.MARK;
        case "unmark":
        case "un":
            return CommandType.UNMARK;
        case "delete":
        case "del":
        case "yeet":
            return CommandType.DELETE;
        case "bye":
        case "exit":
        case "quit":
        case "q":
        case "done":
            return CommandType.EXIT;
        default:
            return CommandType.UNKNOWN;
        }
    }
}
