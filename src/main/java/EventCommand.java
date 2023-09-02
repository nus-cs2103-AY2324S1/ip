import exception.EmptyDateTimeException;
import exception.EmptyInputException;
import exception.InvalidDateTimeException;
import exception.InvalidFormatException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class EventCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    public EventCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException,
            InvalidFormatException, EmptyDateTimeException, InvalidDateTimeException {
        String input = ui.getInput();
        if (input.split(" ").length < 2) {
            throw new EmptyInputException("event");
        } else if (!input.contains("/from")) {
            throw new InvalidFormatException("event", "/from");
        } else if (!input.contains("/to")) {
            throw new InvalidFormatException("event", "/to");
        } else if (input.length() <= input.indexOf("/from") + 6) {
            throw new EmptyDateTimeException("event");
        } else if (input.length() <= input.indexOf("/to") + 4) {
            throw new EmptyDateTimeException("event");
        } else {
            String tempDes = input.split(" ", 2)[1];
            String des = tempDes.split(" /from ")[0];
            String start = tempDes.split(" /from ")[1].split(" /to ")[0];
            String end = tempDes.split(" /to ")[1];
            try {
                String[] startArr = start.split(" ");
                LocalDate startDate = LocalDate.parse(startArr[0]);
                LocalTime startTime = LocalTime.parse(startArr[1]);
                String[] endArr = end.split(" ");
                LocalDate endDate = LocalDate.parse(endArr[0]);
                LocalTime endTime = LocalTime.parse(endArr[1]);
                Event e = new Event(des, startDate, startTime, endDate, endTime);
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
