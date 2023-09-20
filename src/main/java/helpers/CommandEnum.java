package helpers;

/**
 * The {@code CommandEnum} enum represents a set of commands that can be used in a task management application.
 * Each command corresponds to a specific action that the application can perform.
 * The available commands are:
 * - {@code LIST}: List all tasks.
 * - {@code TODO}: Add a new to-do task.
 * - {@code SORT}: Sorts task aphabetically
 * - {@code MARK}: Mark a task as done.
 * - {@code UNMARK}: Mark a task as undone.
 * - {@code DEADLINE}: Add a new deadline task.
 * - {@code EVENT}: Add a new event task.
 * - {@code DELETE}: Delete a task.
 * - {@code FIND}: Find tasks based on a search query.
 * - {@code BYE}: Exit the application.
 */
public enum CommandEnum {
    LIST,
    SORT,
    TODO,
    MARK,
    UNMARK,
    DEADLINE,
    EVENT,
    DELETE,
    FIND,
    BYE
}
