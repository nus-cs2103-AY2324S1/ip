import java.util.Scanner;

/**
 * Represents the main class for the Duke application.
 */
public class Duke {

    public static void main(String[] args) {
        System.out.println("Hello! I'm FRIDAY!\n" +
                "What can I do for you?");
        echo();
    }

    /**
     * Processes user inputs and interacts with the TaskList to execute user commands.
     * Continues to run until the user inputs the "bye" command.
     */
    public static void echo() {
        TaskList taskList = new TaskList();
        Scanner input = new Scanner(System.in);
        while (input.hasNextLine()) {
            try {
                String userInput = input.nextLine();
                if (userInput.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                } else if (userInput.equals("list")) {
                    System.out.println("Here are the tasks in your list:");
                    System.out.println(taskList);
                } else if (userInput.contains("unmark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println("Nice! I've marked this task as not done yet:");
                    taskList.unmark(taskNumber - 1);
                } else if (userInput.contains("mark")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    System.out.println("Nice! I've marked this task as done:");
                    taskList.mark(taskNumber - 1);
                } else if (userInput.contains("delete")) {
                    int taskNumber = Integer.parseInt(userInput.split(" ")[1]);
                    taskList.delete(taskNumber - 1);
                } else if (userInput.contains("todo")) {
                    String[] todoInput = userInput.split(" ", 2);
                    if (todoInput.length < 2 || todoInput[1].trim().isEmpty()) {
                        throw new DukeException("OOPS!!! The description of a todo cannot be empty.\n");
                    }
                    Todo todo = new Todo(todoInput[1]);
                    System.out.println("added: " + todo);
                    taskList.add(todo);
                    taskList.message();
                } else if (userInput.contains("deadline")) {
                    String[] commandAndDetails = userInput.split(" ", 2);
                    if (commandAndDetails.length < 2 || !userInput.contains("/by")) {
                        throw new DukeException("Incorrect format for 'deadline'. Here is a sample:\ndeadline return book /by Sunday\n");
                    }
                    String[] taskAndDate = commandAndDetails[1].split(" /by ", 2);
                    if (taskAndDate.length < 2) {
                        throw new DukeException("Please provide both a task description and a deadline date.");
                    }
                    String taskDescription = taskAndDate[0];
                    String deadlineDate = taskAndDate[1];
                    Deadline deadline = new Deadline(taskDescription, deadlineDate);
                    System.out.println("added: " + deadline);
                    taskList.add(deadline);
                    taskList.message();
                } else if (userInput.contains("event")) {
                    String[] commandAndDetails = userInput.split(" ", 2);
                    if (commandAndDetails.length < 2 || !userInput.contains("/from") || !userInput.contains("/to")) {
                        throw new DukeException("Incorrect format for 'event'. Expected format: event TASK_DESCRIPTION /from START_TIME /to END_TIME");
                    }
                    String[] taskAndTimes = commandAndDetails[1].split(" /from | /to ", 3);
                    if (taskAndTimes.length < 3) {
                        throw new DukeException("Please provide a task description, start time, and end time.");
                    }
                    String taskDescription = taskAndTimes[0];
                    String startTime = taskAndTimes[1];
                    String endTime = taskAndTimes[2];
                    Event event = new Event(taskDescription, startTime, endTime);
                    System.out.println("added: " + event);
                    taskList.add(event);
                    taskList.message();
                } else {
                    throw new DukeException("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
