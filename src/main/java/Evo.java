import java.util.Objects;
import java.util.Scanner;

/**
 * Evo is a Personal Assistant Chatbot that helps a person to keep track of various things.
 * Tasks are represented by the nested static class Task.
 */
public class Evo {

    /**
     * The main method of Evo program.
     *
     * @param args The command-line arguments passed to the program.
     */
    public static void main(String[] args) {
        // Display Evo logo and welcome message
        String logo = " _____\n"
                + "|  ___| \n"
                + "| |___ __    __  _____ \n"
                + "|  ___|\\ \\  / / |     | \n"
                + "| |___  \\ \\/ /  |     |  \n"
                + "|_____|  \\__/   |_____|  \n";
        System.out.println("Hello from\n" + logo);

        // Initialise welcome and goodbye messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // Print out welcome message once the user using Evo
        System.out.println(welcomeMsg);
        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);

        // taskIndex use to keep track the index number of the first empty slot in taskList
        int taskIndex = 0;
        // An array to store the Task objects
        Task[] taskList = new Task[100];

        while (true) {
            // Assign the text to this string variable called instruction
            String instruction = scanner.nextLine();

            // If the text entered is bye, then print out the bye message and exit the loop
            if (Objects.equals(instruction, "bye")) {
                System.out.println(byeMsg);
                break;
            }

            /**
             * If the text entered is list, then print out the status and description of tasks added before by the user.
             * For Deadline and Event objects, the due date and duration will also be printed respectively.
             * Then exit the current while loop and move to the next iteration.
             */
            if (Objects.equals(instruction, "list")) {
                System.out.println("Here are the tasks in your list: ");
                for (int i = 0; i < taskIndex; i++) {
                    if (i == taskIndex - 1) {
                        System.out.println(i + 1 + "." + taskList[i].toString() + "\n");
                    } else {
                        System.out.println(i + 1 + "." + taskList[i].toString());
                    }
                }
                continue;
            }

            /**
             * Split the text entered by user by "/" to differentiate the ToDo task with Deadline and Event object.
             * If the text entered does not contain "/", then split the text entered by user by space and store it in
             * a string array called actionType. Then, determine which action to be taken, whether is to mark a task
             * done, mark a task not done or add a ToDo task to the taskList.
             * If the text entered contain "/", then split the text entered by user by "/" and store it in in a string
             * array called typeAndDates. Then, determine which action to be taken, whether is to add a deadline task
             * to the taskList or add an event task to the taskList.
             */
            if (!instruction.contains("/")) {

                String[] actionType = instruction.split(" ");
                if (Objects.equals(actionType[0], "mark")) {
                    // Mark a task as done
                    int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                    taskList[taskNumberInList].markAsDone();

                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println("  " + taskList[taskNumberInList].toString() + "\n");
                } else if (Objects.equals(actionType[0], "unmark")) {
                    // Mark a task as not done
                    int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                    taskList[taskNumberInList].markAsNotDone();

                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println("  " + taskList[taskNumberInList].toString() + "\n");
                } else if (Objects.equals(actionType[0], "todo")) {
                    // Add ToDo object
                    String taskDescription = "";
                    for (int i = 1; i < actionType.length; i++) {
                        taskDescription += actionType[i] + " ";
                    }

                    ToDo toDo = new ToDo(taskDescription);
                    taskList[taskIndex] = toDo;
                    taskIndex++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + toDo.toString());
                    System.out.println("Now you have " + taskIndex + " tasks in the list.\n");
                }
            } else {
                String[] typeAndDates = instruction.split("/");
                String[] actionType = typeAndDates[0].split(" ");

                // Add Deadline object to the taskList
                if (Objects.equals(actionType[0], "deadline")) {
                    // Construct the description of the deadline task from the user input
                    String taskDescription = "";

                    for (int i = 1; i < actionType.length; i++) {
                        taskDescription += actionType[i] + " ";
                    }

                    // Construct the task due date/time
                    String[] dates = typeAndDates[1].split(" ");
                    String taskBy = "";

                    for (int i = 1; i < dates.length; i++) {
                        if (i == dates.length - 1) {
                            taskBy += dates[i];
                        } else {
                            taskBy += dates[i] + " ";
                        }
                    }
                    // Create the deadline object with the taskDescription and taskBy
                    Deadline deadline = new Deadline(taskDescription, taskBy);
                    taskList[taskIndex] = deadline;
                    taskIndex++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + deadline.toString());
                    System.out.println("Now you have " + taskIndex + " tasks in the list.\n");
                } else if (Objects.equals(actionType[0], "event")) {
                    // Add Event object to the taskList
                    String[] datesFrom = typeAndDates[1].split(" ");
                    String[] datesTo = typeAndDates[2].split(" ");
                    String taskDescription = "";
                    // Construct the description of the event task from the user input
                    for (int i = 1; i < actionType.length; i++) {
                        taskDescription += actionType[i] + " ";
                    }
                    // Construct the task due date/time duration
                    String taskDuration = "";
                    for (int i = 0; i < datesFrom.length; i++) {
                        if (i == 0) {
                            taskDuration += datesFrom[i] + ": ";
                        } else {
                            taskDuration += datesFrom[i] + " ";
                        }
                    }
                    for (int i = 0; i < datesTo.length; i++) {
                        if (i == 0) {
                            taskDuration += datesTo[i] + ": ";
                        } else {
                            taskDuration += datesTo[i];
                        }
                    }
                    // Create the event object with the taskDescription and taskDuration
                    Event event = new Event(taskDescription, taskDuration);
                    taskList[taskIndex] = event;
                    taskIndex++;

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + event.toString());
                    System.out.println("Now you have " + taskIndex + " tasks in the list.\n");
                }
            }
        }
    }
}
