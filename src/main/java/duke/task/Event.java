package duke.task;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exception.InvalidDateException;
import duke.util.Storage;
import duke.util.TaskList;

/**
 * Represents an Event task.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Event extends Task {
    //60 underscores.
    protected static final String HORIZONTAL_LINE = "    ____________________________________________________________";
    protected LocalDate startTime;
    protected LocalDate endTime;

    /**
     * Instantiates an event task with the given description, start date and end date.
     *
     * @param description The description of the Event task.
     * @param startTime   The starting date of the Event task.
     * @param endTime     The end date of the Event task.
     */
    public Event(String description, LocalDate startTime, LocalDate endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    protected static void printHorizontalLine() {
        System.out.println(HORIZONTAL_LINE);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(),
                getStartTime().format(DateTimeFormatter.ofPattern("MMM d yyy")),
                getEndTime().format(DateTimeFormatter.ofPattern("MMM d yyy")));
    }

    public LocalDate getStartTime() {
        return this.startTime;
    }

    public LocalDate getEndTime() {
        return this.endTime;
    }

    /**
     * Handles the creation of an event task based on user input.
     *
     * @param userInput A valid user input for an event task.
     * @throws IOException If there is an error with the storage.
     */
    public static String handleEventTask(String userInput) throws IOException {
        String[] details = userInput.split("/from | /to");
        // details[0] contains "event" and task description, need to erase "event".
        // details[1] contains String event timing
        StringBuilder message = new StringBuilder();

        if (details.length == 3) {
            String taskDescription = details[0].trim().replaceFirst("event", "").trim();
            String startTime = details[1].trim();
            String endTime = details[2].trim();

            //Check if input date is valid.
            try {
                if (TaskList.isValidDate(startTime)) {
                    Event eventTask = new Event(taskDescription,
                            LocalDate.parse(startTime),
                            LocalDate.parse(endTime));

                    Storage.saveTask(eventTask, true);
                    Storage.listOfTasks.add(eventTask);

                    message.append("Got it, I've added this Task:\n");
                    message.append(String.format(" %s\n", eventTask));
                    message.append(String.format("Now you have %d task(s) in the list.\n", Storage.listOfTasks.size()));
                } else {
                    throw new InvalidDateException();
                }
            } catch (InvalidDateException e) {
                message.append(e);
                System.out.println(e.toString());
                return message.toString();
            }

        } else {
            message.append("Invalid Event Task input.\n"
                    + "Please input in the following format:\n"
                    + "event <Task Description> /from <start time> /to <end time>");
        }
        return message.toString();
    }
}
