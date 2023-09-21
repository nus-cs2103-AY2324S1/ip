package duke.command;

import java.util.ArrayList;

import duke.main.DateFormatter;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

/** Class to represent the list of commands entered by users*/
public class CommandList {
    /**
     * A formatter for the date
     */
    private static final DateFormatter DF = new DateFormatter();
    /**
     * The list of previous commands
     */
    private final ArrayList<Command> commandList;

    /**
     * A constructor for the CommandList
     */
    public CommandList() {
        this.commandList = new ArrayList<Command>();
    }

    /**
     * Returns size of commandList
     */
    public int size() {
        return this.getCommandList().size();
    }

    /**
     * Returns commandList
     */
    public ArrayList<Command> getCommandList() {
        return this.commandList;
    }

    /**
     * Returns command from commandList
     * @param index The index of the command inside the commandList to be returned
     * @return command
     */
    public Command getCommand(int index) {
        return this.getCommandList().get(index);
    }

    /**
     * Adds command to commandList
     * @param command The command to be added
     */
    public void addCommand(Command command) {
        this.getCommandList().add(command);
    }

    /**
     * Removes last command from commandList
     */
    public void removeLastCommand() {
        this.getCommandList().remove(this.commandList.size() - 1);
    }

    /**
     * Returns commandList in String format to be written to file
     * @return content of commandList
     */
    public String convertToFileContent() {
        String result = "";
        String line = "";
        for (int i = 0; i < this.size(); i++) {
            Command currentCommand = this.getCommand(i);
            Task currentTask = currentCommand.getTask();
            if (currentCommand instanceof DeleteCommand) {
                DeleteCommand temp = (DeleteCommand) currentCommand;
                line = "delete | " + temp.getIndex() + " | " + taskFormatter(currentTask);
            } else if (currentCommand instanceof AddCommand) {
                line = "add | command";
            } else if (currentCommand instanceof MarkCommand) {
                MarkCommand temp = (MarkCommand) currentCommand;
                line = "mark | " + temp.getIndex();
            } else if (currentCommand instanceof UnmarkCommand) {
                UnmarkCommand temp = (UnmarkCommand) currentCommand;
                line = "unmark | " + temp.getIndex();
            }
            result += line + "\n";
        }
        return result;
    }

    /**
     * Returns task in String format to be written to file
     * @param currentTask the task to be formatted into String to store into file
     * @return String of task
     */
    public String taskFormatter(Task currentTask) {
        String line = "";
        if (currentTask instanceof Todo) {
            String mark = currentTask.isDoneGetter() ? "1" : "0";
            line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.getDescription();
        } else if (currentTask instanceof Deadline) {
            String mark = currentTask.isDoneGetter() ? "1" : "0";
            line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.getDescription() + " | "
                    + DF.saveDateToFile(((Deadline) currentTask).getEndDate());
        } else if (currentTask instanceof Event) {
            String mark = currentTask.isDoneGetter() ? "1" : "0";
            line = currentTask.getTypeIcon() + " | " + mark + " | " + currentTask.getDescription() + " | "
                    + DF.saveDateToFile(((Event) currentTask).getStartDate()) + " | "
                    + DF.saveDateToFile(((Event) currentTask).getEndDate());
        }
        return line;
    }
}
