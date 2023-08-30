import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    private LocalDate by;

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
     * Function to handle a Deadline Task. If it's inputs are valid, create a Deadline Task.
     * Otherwise, print an error message in the console.
     * @param userInput a valid user input for a Deadline Task.
     */
    public static void handleDeadlineTask(String userInput) throws IOException {
        String[] details = userInput.split("/by");
        //details[0] contains "deadline" plus task description, need to erase "deadline". details[1] contains deadline timing
        if (details.length == 2) {
            String taskDescription = details[0].trim().replaceFirst("deadline", "").trim();
            String deadline = details[1].trim();

            //Check if input date is valid.
            try {
                if (TaskList.isValidDate(deadline)) {
                    Deadline deadlineTask = new Deadline(taskDescription, LocalDate.parse(deadline));
                    Storage.saveTask(deadlineTask, true);
                    Storage.taskList.add(deadlineTask); //Deadline <: Task

                    //Print details in the console
                    System.out.println(Duke.HORIZONTAL_LINE);
                    System.out.println("     Got it. I've added this task:");
                    System.out.printf("       %s\n", deadlineTask.toString());
                    System.out.printf("     Now you have %d tasks in the list.\n", Storage.taskList.size());
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
