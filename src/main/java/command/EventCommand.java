package command;

import duke.Ui;
import duke.TaskList;

import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

import task.Event;

/**
 * The Command which indicates that the user wishes to add an event to the task list.
 */
public class EventCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    /**
     * The constructor of EventCommand.
     *
     * @param taskList The task list which the command would modify when tasked.
     * @param ui The ui of the chatbot to get the input of the user.
     */
    public EventCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException,
            InvalidFormatException, EmptyDateTimeException, InvalidDateTimeException {
        String input = ui.getInput();
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
            try {
                String[] startArr = start.split(" ");
                LocalDate startDate = LocalDate.parse(startArr[0]);
                LocalTime startTime = LocalTime.parse(startArr[1]);
                String[] endArr = end.split(" ");
                LocalDate endDate = LocalDate.parse(endArr[0]);
                LocalTime endTime = LocalTime.parse(endArr[1]);
                Event e = new Event(description, startDate, startTime, endDate, endTime);
                taskList.addTask(e);
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("event");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
