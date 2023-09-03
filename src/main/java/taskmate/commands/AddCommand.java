package taskmate.commands;

/**
 * The AddCommand class is an abstract parent class to three other commands that deal with adding new tasks specified by
 * the user. The three other commands are:
 * 1. "todo <name>"
 * 2. "deadline <name> /by <date>"
 * 3. "event <name> /from <date> /to <date>
 *
 */
public abstract class AddCommand extends Command {
    String name;
}
