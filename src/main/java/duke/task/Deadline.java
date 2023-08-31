package duke.task;

import duke.Duke;
import duke.exception.InvalidDateException;
import duke.util.Storage;
import duke.util.TaskList;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Represents a Deadline task.
 *
 * <p>CS2103T AY23/24 Semester 1
 * Individual Project
 * SeeWhyAre Bot
 * 31 Aug 2023
 *
 * @author Freddy Chen You Ren
 */
public class Deadline extends Task {

    private LocalDate by;

    /**
     * Constructs a Deadline task with the given description and deadline date.
     *
     * @param description The description of the deadline task.
     * @param by          The deadline date.
     */
    public Deadline(String description, LocalDate by) {
        super(description);
        this.by = by;
    }

    private LocalDate getDeadlineDate() {
        return this.by;
    }
    @Override
    public String toString() {
        //return "[D]" + super.toString() + " (by: " + by + ")";
        return String.format("[D]%s (by: %s)", super.toString(),
                getDeadlineDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")));
    }

    public LocalDate getDueDate() {
        return this.by;
    }

    /**
     * Handles the creation of a Deadline task based on user input.
     *
     * @param userInput A valid user input for a Deadline task.
     * @throws IOException If there is an error with the storage.
     */
    public static void handleDeadlineTask(String userInput) throws IOException {
        String[] details = userInput.split("/by");
        //details[0] contains "deadline" plus duke.task description, need to erase "deadline". details[1] contains deadline timing
        if (details.length == 2) {
            String taskDescription = details[0].trim().replaceFirst("deadline", "").trim();
            String deadline = details[1].trim();

            //Check if input date is valid.
            try {
                if (TaskList.isValidDate(deadline)) {
                    Deadline deadlineTask = new Deadline(taskDescription, LocalDate.parse(deadline));
                    Storage.saveTask(deadlineTask, true);
                    Storage.listOfTasks.add(deadlineTask); //duke.task.Deadline <: duke.task.Task

                    //Print details in the console
                    System.out.println(Duke.HORIZONTAL_LINE);
                    System.out.println("     Got it. I've added this Task:");
                    System.out.printf("       %s\n", deadlineTask.toString());
                    System.out.printf("     Now you have %d task(s) in the list.\n", Storage.listOfTasks.size());
                    System.out.println(Duke.HORIZONTAL_LINE);
                } else {
                    throw new InvalidDateException();
                }
            } catch (InvalidDateException e) {
                System.out.println(e.toString());
            }

        } else {
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Invalid Deadline Task input.\n"
                    + "     Please input in the following format:\n"
                    + "     deadline <Task Description> /by <deadline timing>");
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}
