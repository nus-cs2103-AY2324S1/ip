import java.io.IOException;

public class Deadline extends Task {

    protected String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    public String getDueDate() {
        return this.by;
    }

    /**
     * Function to handle a Deadline Task. If it's inputs are valid, create a Deadline Task.
     * Otherwise, print an error message in the console.
     * @param userInput a valid user input for a Deadline Task.
     */
    public static void handleDeadlineTask(String userInput) throws IOException {
        String[] details = userInput.split("/by");
        //details[0] contains "deadline" plus task description, need to erase "deadline". details[1] contains String deadline timing
        if (details.length == 2) {
            String taskDescription = details[0].trim().replaceFirst("deadline", "").trim();
            String deadline = details[1].trim();
            Deadline deadlineTask = new Deadline(taskDescription, deadline);
            Duke.saveTask(deadlineTask, true);
            Duke.taskList.add(deadlineTask); //Deadline <: Task

            //Print details in the console
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Got it. I've added this task:");
            System.out.printf("       %s\n", deadlineTask.toString());
            System.out.printf("     Now you have %d tasks in the list.\n", Duke.taskList.size());
            System.out.println(Duke.HORIZONTAL_LINE);

        } else {
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Invalid Deadline Task input.\n"
                    + "     Please input in the following format:\n"
                    + "     deadline <Task Description> /by <deadline timing>");
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}
