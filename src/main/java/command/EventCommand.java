package command;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;
import task.Event;

/**
 * The Command which indicates that the user wishes to add an event to the task list.
 */
public class EventCommand extends Command {
    private TaskList taskList;
    private Ui ui;
    private Storage storage;

    /**
     * The constructor of EventCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public EventCommand(TaskList taskList, Ui ui, Storage storage) {
        super(taskList, ui, storage);
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws EmptyInputException,
            InvalidFormatException, EmptyDateTimeException, InvalidDateTimeException {
        String input = ui.getInput();
        try {
            if (input.split(" ").length < 2) {
                throw new EmptyInputException("a event");
            } else if (!input.contains("/from")) {
                throw new InvalidFormatException("event", "/from");
            } else if (!input.contains("/to")) {
                throw new InvalidFormatException("event", "/to");
            } else if (input.length() <= input.indexOf("/from") + 6) {
                throw new EmptyDateTimeException("event");
            } else if (input.length() <= input.indexOf("/to") + 4) {
                throw new EmptyDateTimeException("event");
            } else {
                String tempDescription = input.split(" ", 2)[1];
                String description = tempDescription.split(" /from ")[0];
                String start = tempDescription.split(" /from ")[1].split(" /to ")[0];
                String end = tempDescription.split(" /to ")[1];

                String[] startArr = start.split(" ");
                LocalDate startDate = LocalDate.parse(startArr[0]);
                LocalTime startTime = LocalTime.parse(startArr[1]);
                String[] endArr = end.split(" ");
                LocalDate endDate = LocalDate.parse(endArr[0]);
                LocalTime endTime = LocalTime.parse(endArr[1]);
                Event event = new Event(description, startDate, startTime, endDate, endTime);
                String str = ui.printAddTask(taskList, event);
                storage.writeTasks(taskList);
                return str;

            }
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
