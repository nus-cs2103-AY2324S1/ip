package yong.command;

import yong.tasklist.TaskList;

import yong.tasks.Event;
import yong.tasks.Task;




import yong.exception.DukeException;

import java.security.InvalidParameterException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Represents a command for handling event tasks.
 */
public class EventCommand extends Command {

    private String inputString;

    /**
     * Constructs an EventCommand.
     *
     * @param taskList The TaskList object used to maintain the list of tasks in the chatbot.
     * @param inp The input line from the command-line interface.
     */
    public EventCommand(TaskList taskList, String inp) {
        super(taskList);
        this.inputString = inp;
    }

    /**
     * Initializes an Event object and adds it to the taskList.
     *
     * @return A message indicating the task has been added.
     * @throws DukeException If the input is invalid.
     */
    @Override
    public String execute() {
        try {
            String[] parts = inputString.split("/", 3);
            String[] typeDescription = parts[0].split(" ", 2);
            String type = typeDescription[0];
            String description = typeDescription[1];
            String from = parts[1].trim().split(" ", 2)[1];
            String to = parts[2].trim().split(" ", 2)[1];

            LocalDateTime fromDate = parseDateTime(from);
            LocalDateTime toDate = parseDateTime(to);

            if (fromDate.isBefore(toDate)) {
                Task newTask = new Event(description, from, to);

                taskList.add(newTask);

                outputString = "Okay! Task added \n" + newTask;
            } else {
                outputString = "From Date cannot be after To Date!";
            }

            return outputString;
        } catch (Exception e) {
            System.out.println(e);
            throw new DukeException("Please give a valid description for an Event task!"
                    + " An example time format will be \"Event Read Book /from 2022-03-01 1800 /to 2022-04-02 1900\"");
        }
    }

    public LocalDateTime parseDateTime(String datetimeString) throws InvalidParameterException {
        try {
            String pattern = "yyyy-MM-dd HHmm";
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
            LocalDateTime datetime = LocalDateTime.parse(datetimeString, formatter);
            return datetime;
        } catch (Exception e) {
            System.out.println("Wrong date format provided");
            throw new InvalidParameterException();
        }
    }
}
