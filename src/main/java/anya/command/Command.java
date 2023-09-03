package anya.command;

/**
 * Enum representing the available commands in the Anya application.
 *
 * This enum defines the various command types that can be used by the user to interact with the Anya application.
 * Each enum value corresponds to a specific user command, such as "BYE" for exiting the application or "TODO" for
 * adding a to-do task.
 */
public enum Command {
    BYE, LIST, MARK, UNMARK, TODO, DEADLINE, EVENT, DELETE, FIND, UNKNOWN
}
