package com.ducky.command;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.ducky.common.DuckyException;
import com.ducky.common.Storage;
import com.ducky.common.TaskList;
import com.ducky.util.Parser;

/**
 * Represents a command that retrieves the list of tasks scheduled for the specified day.
 */
public class ViewScheduleCommand extends Command {

    private static final String INVALID_DATE_FORMAT_ERROR_MSG =
            "Your date query should be in yyyy-mm-dd format.";

    private final String dateString;
    /**
     * Constructs a command that retrieves list of tasks on the specified day.
     *
     * @param dateString String of date to retrieve tasks for.
     */
    public ViewScheduleCommand(String dateString) {
        this.dateString = dateString;
    }

    /**
     * Parses the specified date string and retrieves list of tasks on that date.
     *
     * @param taskList TaskList of Ducky chatbot instance.
     * @param storage  Storage module of Ducky chatbot instance.
     * @return String with list of tasks that fulfil the date query.
     * @throws DuckyException If exceptions specific to Ducky are raised.
     */
    @Override
    public String execute(TaskList taskList, Storage storage) throws DuckyException {
        LocalDate queryDate;
        try {
            queryDate = Parser.parseDate(this.dateString);
            return taskList.queryTaskByDate(queryDate);
        } catch (DateTimeParseException e) {
            throw new DuckyInvalidCommandFormatException(
                    INVALID_DATE_FORMAT_ERROR_MSG
            );
        }
    }
}
