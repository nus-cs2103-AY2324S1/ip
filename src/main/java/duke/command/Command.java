package duke.command;

import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.element.CommandElement;
import duke.parser.element.argument.Argument;
import duke.storage.Storage;
import duke.ui.Ui;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Actionable representing a command input by the user.
 */
public abstract class Command {

    protected final String commandName;
    protected Map<String, Object> args;

    /**
     * Constructor for command.
     * 
     * @param commandName The name of the command.
     * @param args The arguments entered by the user.
     */
    public Command(String commandName, Map<String, Object> args) {
        this.commandName = commandName;
        this.args = args;
    }

    protected abstract List<CommandElement> getCommandElements();

    /**
     * Generates regex pattern expected for command.
     * 
     * @return Regex pattern expected for command.
     */
    public Pattern getPattern() {
        return Pattern.compile(String.join("", this.getCommandElements().stream().map(
                e -> e.getRegexForm()).collect(Collectors.toList())));
    }

    /**
     * Generates readable expected structure for command.
     * 
     * @return Readable expected structure for command.
     */
    public String getStructure() {
        List<String> struct = new ArrayList<>();
        struct.add(commandName);
        for (CommandElement e : this.getCommandElements()) {
            struct.add(e.getName());
        }
        return String.join(" ", struct);
    }

    /**
     * @inheritdoc
     */
    @Override
    public String toString() {
        List<String> commandList = new ArrayList<>();
        commandList.add(this.commandName);
        int i = 0;
        for (CommandElement e : this.getCommandElements()) {
            if (e instanceof Argument) {
                commandList.add(((Argument) e).formatOutput(args.get(e.toString())).toString());
            } else {
                commandList.add(e.toString());
            }
        }
        return String.join(" ", commandList);
    }

    /**
     * Generates list of arguments expected from command.
     * 
     * @return List of arguments expected from command.
     */
    public List<Argument> getArguments() {
        List<Argument> argsList = new ArrayList<>();
        argsList.add(null);
        for (CommandElement e : this.getCommandElements()) {
            if (e instanceof Argument) {
                argsList.add((Argument) e);
            } else {
                argsList.add(null);
            }
        }
        return argsList;
    }

    /**
     * Get command to act.
     * 
     * @param tasks The list of tasks to act on.
     * @param ui The Ui to interact with the user.
     * @param storage The Storage to update data.
     * @throws DukeException When the execution fails.
     */
    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether the command is to terminate the program.
     * 
     * @return Whether the command is to terminate the program.
     */
    public abstract boolean isExit();

}
