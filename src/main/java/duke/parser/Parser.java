package duke.parser;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.command.Command;
import duke.command.ListCommand;
import duke.command.MarkCommand;
import duke.command.UnmarkCommand;
import duke.command.ToDoCommand;
import duke.command.DeadlineCommand;
import duke.command.EventCommand;
import duke.command.DeleteCommand;
import duke.command.ByeCommand;
import duke.command.InvalidCommand;
import duke.ui.Ui;

/**
 * Handles parsing of user input and file input.
 */
public class Parser {
    Ui ui;

    /**
     * Initialises the parser object with a Ui object.
     * 
     * @param ui The Ui object to handle user interaction.
     */
    public Parser(Ui ui) {
        this.ui = ui;
    }

    /**
     * Parses the task from the file and returns a Task object.
     * 
     * @param fileTask The task in the file.
     * @return A Task object.
     */
    public Task parseFileTask(String fileTask) {
        String[] taskComponents = fileTask.split(" \\| ");
        String taskType = taskComponents[0];
        boolean taskStatus = taskComponents[1].equals("1");
        String taskDescription = taskComponents[2];
        Task task = null;
        DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

        switch (taskType) {
            case "T":
                ToDo todoTask = new ToDo(taskDescription);
                todoTask.changeStatus(taskStatus);
                task = todoTask;
                break;
            case "D":
                LocalDateTime deadlineDate = LocalDateTime.parse(taskComponents[3], fileFormatter);
                Deadline deadlineTask = new Deadline(taskDescription, deadlineDate);
                deadlineTask.changeStatus(taskStatus);
                task = deadlineTask;
                break;
            case "E":
                String[] taskDates = taskComponents[3].split(" - ");
                Event event = new Event(taskDescription, LocalDateTime.parse(taskDates[0], fileFormatter),
                        LocalDateTime.parse(taskDates[1], fileFormatter));
                event.changeStatus(taskStatus);
                task = event;
                break;
        }
        return task;
    }

    /**
     * Parses the user input and returns a Command object.
     * 
     * @param input The user input.
     * @return A Command object.
     */
    public Command parseCommand(String input) {
        String[] inputComponents = input.split(" ");
        String command = inputComponents[0];
        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");

        try {
            switch (command) {
                case "list":
                    return new ListCommand();
                case "mark":
                    return new MarkCommand(Integer.parseInt(inputComponents[1]));
                case "unmark":
                    return new UnmarkCommand(Integer.parseInt(inputComponents[1]));
                case "todo":
                    return new ToDoCommand(input.substring(5));
                case "deadline":
                    String[] deadlineComponents = input.substring(9).split(" /by ");
                    return new DeadlineCommand(deadlineComponents[0],
                            LocalDateTime.parse(deadlineComponents[1], inputFormatter));
                case "event":
                    String[] eventComponents = input.substring(6).split(" /from ");
                    String[] eventDates = eventComponents[1].split(" /to ");
                    LocalDateTime eventStart = LocalDateTime.parse(eventDates[0], inputFormatter);
                    LocalDateTime eventEnd = LocalDateTime.parse(eventDates[1], inputFormatter);
                    return new EventCommand(eventComponents[0], eventStart, eventEnd);
                case "delete":
                    return new DeleteCommand(Integer.parseInt(inputComponents[1]));
                case "bye":
                    return new ByeCommand();
            }
        } catch (Exception e) {
            ui.showError(e.getMessage());
        }

        return new InvalidCommand();
    }
}
