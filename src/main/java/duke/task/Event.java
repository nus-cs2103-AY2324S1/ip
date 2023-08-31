package duke.task;

import duke.exception.InvalidDateException;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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

    protected LocalDate startTime;
    protected LocalDate endTime;
    protected static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.

    /**
     * Constructs a Event task with the given description, start date and end date.
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
//        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
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
    public String getSchedule() {
        return String.format("From %s to %s", getStartTime(), getEndTime());
    }

    /**
     * Handles the creation of a Deadline task based on user input.
     *
     * @param userInput A valid user input for a Deadline task.
     * @throws IOException If there is an error with the storage.
     */
    public static void handleEventTask(String userInput) throws IOException {
        String[] details = userInput.split("/from | /to");
        //details[0] contains "deadline" plus duke.task description, need to erase "deadline". details[1] contains String deadline timing
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
                    Storage.listOfTasks.add(eventTask); //duke.task.Deadline <: duke.task.Task

                    //Print details in the console
                    printHorizontalLine();
                    System.out.println("     Got it. I've added this task:");
                    System.out.printf("       %s\n", eventTask.toString());
                    System.out.printf("     Now you have %d task(s) in the list.\n", Storage.listOfTasks.size());
                    printHorizontalLine();
                } else {
                    throw new InvalidDateException();
                }
            } catch (InvalidDateException e) {
                System.out.println(e.toString());
            }

        } else {
            printHorizontalLine();
            System.out.println("     Invalid Event Task input.\n"
                    + "     Please input in the following format:\n"
                    + "     event <Task Description> /from <start time> /to <end time>");
            printHorizontalLine();
        }
    }
}
