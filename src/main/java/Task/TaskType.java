package task;

/**
 * The `TaskType` enum represents the different types of commands or actions that can be performed
 * in the BloopBot application.
 * Each enum value corresponds to a specific command, and it is used to identify the user's intended action.
 *
 * The possible `TaskType` values include:
 * - ADD: Represents the "add" command.
 * - TODO: Represents the "todo" command.
 * - DEADLINE: Represents the "deadline" command.
 * - EVENT: Represents the "event" command.
 * - LIST: Represents the "list" command.
 * - MARK: Represents the "mark" command.
 * - UNMARK: Represents the "unmark" command.
 * - DELETE: Represents the "delete" command.
 * - BYE: Represents the "bye" command.
 * - ECHO: Represents the "echo" command.
 * - UNKNOWN: Represents an unknown or unsupported command.
 * - HELP: Represents the "help" command.
 *
 * @author raydenlim
 * @version 0.0.0
 */
public enum TaskType {
    ADD, TODO, DEADLINE, EVENT, LIST, MARK, UNMARK, DELETE, BYE, ECHO, UNKNOWN, HELP
}
