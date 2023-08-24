import java.util.*;

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
                + "|  ___|\n"
                + "| |___ __    __  _____\n"
                + "|  ___|\\ \\  / / |     |\n"
                + "| |___  \\ \\/ /  |     |\n"
                + "|_____|  \\__/   |_____|\n";
        System.out.println("Hello from\n" + logo);

        // Initialise welcome and goodbye messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // Print out welcome message once the user using Evo
        System.out.println(welcomeMsg);
        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);

        // An ArrayList to store the Task objects
        ArrayList<Task> taskList = new ArrayList<>();

        while (true) {
            try {
                // Assign the text to this string variable called instruction
                String instruction = scanner.nextLine();
                // If the text entered is bye, then print out the bye message and exit the loop
                if (Objects.equals(instruction, "bye")) {
                    System.out.println(byeMsg);
                    break;
                }

                /**
                 * If the text entered is list, then print out the status and description of tasks added before by the
                 * user. For Deadline and Event objects, the due date and duration will also be printed respectively.
                 * Then exit the current while loop and move to the next iteration.
                 */
                if (Objects.equals(instruction, "list")) {
                    if (taskList.isEmpty()) {
                        System.out.println("Here are the tasks in your list:\n");
                        continue;
                    } else {
                        System.out.println("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            if (i == taskList.size() - 1) {
                                System.out.println(i + 1 + "." + taskList.get(i).toString() + "\n");
                            } else {
                                System.out.println(i + 1 + "." + taskList.get(i).toString());
                            }
                        }
                        continue;
                    }
                }

                /**
                 * Split the text entered by user by "/" to differentiate the ToDo task with Deadline and Event object.
                 * If the text entered does not contain "/", then split the text entered by user by space and store it
                 * in a string array called actionType. Then, determine which action to be taken, whether is to mark a
                 * task done, mark a task not done, add a ToDo task to the taskList or delete a task from the taskList.
                 * If the text entered contain "/", then split the text entered by user by "/" and store it in in a
                 * string array called typeAndDates. Then, determine which action to be taken, whether is to add a
                 * deadline task to the taskList or add an event task to the taskList.
                 */
                if (!instruction.contains("/")) {
                    String[] actionType = instruction.split(" ");
                    if (Objects.equals(actionType[0], "mark")) {
                        if (actionType.length == 1) {
                            throw new MissingTaskToMarkException();
                        }
                        // Mark a task as done
                        int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                        taskList.get(taskNumberInList).markAsDone();

                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("  " + taskList.get(taskNumberInList).toString() + "\n");
                    } else if (Objects.equals(actionType[0], "unmark")) {
                        if (actionType.length == 1) {
                            throw new MissingTaskToUnmarkException();
                        }
                        // Mark a task as not done
                        int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
                        taskList.get(taskNumberInList).markAsNotDone();

                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.println("  " + taskList.get(taskNumberInList).toString() + "\n");
                    } else if (Objects.equals(actionType[0], "delete")) {
                        if (taskList.isEmpty()) {
                            throw new NoTaskException();
                        }
                        // Delete a task from taskList
                        int taskNumberToDelete = Integer.parseInt(actionType[1]) - 1;
                        Task deletedTask = taskList.get(taskNumberToDelete);
                        taskList.remove(taskNumberToDelete);

                        System.out.println("Noted. I've removed this task:");
                        System.out.println("  " + deletedTask.toString());
                        if (taskList.size() <= 1) {
                            System.out.println("Now you have " + taskList.size() + " task in the list.\n");
                        } else {
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
                        }
                    } else if (Objects.equals(actionType[0], "todo")) {
                        // Add ToDo object
                        if (actionType.length == 1) {
                            throw new MissingToDoDescriptionException();
                        }
                        String taskDescription = "";
                        for (int i = 1; i < actionType.length; i++) {
                            if (i == actionType.length - 1) {
                                taskDescription += actionType[i];
                            } else {
                                taskDescription += actionType[i] + " ";
                            }
                        }

                        ToDo toDo = new ToDo(taskDescription);
                        taskList.add(toDo);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + toDo.toString());
                        if (taskList.size() <= 1) {
                            System.out.println("Now you have " + taskList.size() + " task in the list.\n");
                        } else {
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
                        }
                    } else if (Objects.equals(actionType[0], "deadline")) {
                        if (actionType.length == 1) {
                            throw new MissingDescriptionAndDeadlineException();
                        } else {
                            throw new MissingDeadlineException();
                        }
                    } else if (Objects.equals(actionType[0], "event")) {
                        if (actionType.length == 1) {
                            throw new MissingDescriptionAndDurationException();
                        } else {
                            throw new MissingDurationException();
                        }
                    } else {
                        throw new InvalidOperationException();
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
                        taskList.add(deadline);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + deadline.toString());
                        if (taskList.size() <= 1) {
                            System.out.println("Now you have " + taskList.size() + " task in the list.\n");
                        } else {
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
                        }
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
                        taskList.add(event);

                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + event.toString());
                        if (taskList.size() <= 1) {
                            System.out.println("Now you have " + taskList.size() + " task in the list.\n");
                        } else {
                            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
                        }
                    }
                }
            } catch (InvalidOperationException invalidOpExp) {
                // Catch the exception when the operation is invalid
                System.out.println("OOPS!!! I'm sorry, but I don't know what that means :-(\n");
            } catch (MissingToDoDescriptionException missToDoExp) {
                // Catch the exception when the description of ToDo task is missing
                System.out.println("Description of this task is missing. " +
                        "Please specify the description of this task.\n");
            } catch (MissingTaskToMarkException missingTaskToMarkExp) {
                // Catch the exception when user never specifies which task to be marked
                System.out.println("Please specify the task to be marked.\n");
            } catch (MissingTaskToUnmarkException missingTaskToUnmarkExp) {
                // Catch the exception when user never specifies which task to be unmarked
                System.out.println("Please specify the task to be unmarked.\n");
            } catch (MissingDescriptionAndDeadlineException missingDescAndDeadlineExp) {
                // Catch the exception when the description and deadline of Deadline object are missing
                System.out.println("Description and deadline of this task are missing. " +
                        "Please specify the description and the deadline of this task.\n");
            } catch (MissingDescriptionAndDurationException missingDescAndDurationExp) {
                // Catch the exception when the description and duration of Event object are missing
                System.out.println("Description and duration of this event are missing. " +
                        "Please specify the description and the duration of this event.\n");
            } catch (MissingDeadlineException missingDeadlineException) {
                // Catch the exception when the deadline of Deadline object is missing
                System.out.println("Deadline of this task is missing. Please specify the deadline of this task.\n");
            } catch (MissingDurationException missingDurationException) {
                // Catch the exception when the duration of Event object is missing
                System.out.println("Duration of this event is missing. " +
                        "Please specify the start date/time and end date/time.\n");
            } catch (NoTaskException noTaskException) {
                // Catch the exception when user tries to delete task from an empty taskList
                System.out.println("This task cannot be deleted as there is no task in the list.\n");
            }
        }
    }
}
