import java.io.IOException;

public class Event extends Task {

    protected String startTime;
    protected String endTime;

    public Event(String description, String startTime, String endTime) {
        super(description);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + startTime + " to: " + endTime + ")";
    }

    public String getStartTime() {
        return this.startTime;
    }

    public String getEndTime() {
        return this.endTime;
    }
    public String getSchedule() {
        return String.format("From %s to %s", getStartTime(), getEndTime());
    }
    /**
     * Function to handle an Event Task. If it's inputs are valid, create an Event Task.
     * Otherwise, print an error message in the console.
     * @param userInput a valid user input for an Event Task.
     */
    public static void handleEventTask(String userInput) throws IOException {
        String[] details = userInput.split("/from | /to");
        //details[0] contains "deadline" plus task description, need to erase "deadline". details[1] contains String deadline timing
        if (details.length == 3) {
            String taskDescription = details[0].trim().replaceFirst("event", "").trim();
            String startTime = details[1].trim();
            String endTime = details[2].trim();
            Event eventTask = new Event(taskDescription, startTime, endTime);
            Duke.saveTask(eventTask, true);
            Duke.taskList.add(eventTask); //Deadline <: Task

            //Print details in the console
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Got it. I've added this task:");
            System.out.printf("       %s\n", eventTask.toString());
            System.out.printf("     Now you have %d tasks in the list.\n", Duke.taskList.size());
            System.out.println(Duke.HORIZONTAL_LINE);

        } else {
            System.out.println(Duke.HORIZONTAL_LINE);
            System.out.println("     Invalid Event Task input.\n"
                    + "     Please input in the following format:\n"
                    + "     event <Task Description> /from <start time> /to <end time>");
            System.out.println(Duke.HORIZONTAL_LINE);
        }
    }
}
