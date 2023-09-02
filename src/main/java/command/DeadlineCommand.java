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

import task.Deadline;

public class DeadlineCommand extends Command {
    private TaskList taskList;
    private Ui ui;

    public DeadlineCommand(TaskList taskList, Ui ui) {
        super(taskList, ui);
    }
    @Override
    public void execute(TaskList taskList, Ui ui) throws EmptyInputException,
            InvalidFormatException, EmptyDateTimeException, InvalidDateTimeException {
        String input = ui.getInput();
        if (input.split(" ").length < 2) {
            throw new EmptyInputException("deadline");
        } else if (!input.contains("/by")) {
            throw new InvalidFormatException("deadline", "/by");
        } else if (input.length() <= input.indexOf("/by") + 4) {
            throw new EmptyDateTimeException("deadline");
        } else {
            String tempDes = input.split(" ", 2)[1];
            System.out.println(tempDes);
            String des = tempDes.split(" /by " )[0];
            String by = tempDes.split(" /by ")[1];
            try {
                String[] dateTimeArr = by.split(" ");
                LocalDate byDate = LocalDate.parse(dateTimeArr[0]);
                LocalTime byTime = LocalTime.parse(dateTimeArr[1]);
                Deadline d = new Deadline(des, byDate, byTime);
                taskList.addTask(d);
            } catch (DateTimeParseException e) {
                throw new InvalidDateTimeException("deadline");
            }
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
