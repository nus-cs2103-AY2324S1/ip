import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
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
        // Displays Evo logo and welcome message
        String logo = " _____\n"
                + "|  ___|\n"
                + "| |___ __    __  _____\n"
                + "|  ___|\\ \\  / / |     |\n"
                + "| |___  \\ \\/ /  |     |\n"
                + "|_____|  \\__/   |_____|\n";
        System.out.println("Hello from\n" + logo);

        // Initialises welcome and goodbye messages
        String welcomeMsg = "Hello! I'm Evo.\n" + "What can I do for you?\n";
        String byeMsg = "Bye. Hope to see you again soon!";

        // Prints out welcome message once the user using Evo
        System.out.println(welcomeMsg);

        // An ArrayList to store the Task objects
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            // Loads the data from the file when the chatbot starts up
            File folder = new File("./data");
            if (!folder.exists()) {
                throw new NoFolderFoundException();
            }
            File file = new File("./data/evo.txt");
            if (!file.exists()) {
                throw new NoDataFileFoundException();
            }
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] type = line.split(" \\| ");
                switch (type[0]) {
                case "T":
                    Task toDo = new ToDo(type[2]);
                    if (Integer.parseInt(type[1]) == 1) {
                        toDo.markAsDone();
                    }
                    taskList.add(toDo);
                    break;
                case "D":
                    Deadline deadline = new Deadline(type[2], type[3]);
                    if (Integer.parseInt(type[1]) == 1) {
                        deadline.markAsDone();
                    }
                    taskList.add(deadline);
                    break;
                case "E":
                    Event event = new Event(type[2], type[3]);
                    if (Integer.parseInt(type[1]) == 1) {
                        event.markAsDone();
                    }
                    taskList.add(event);
                    break;
                default:
                    throw new UnexpectedTaskTypeException();
                }
            }
        } catch (IOException ioException) {
            System.out.println("Something went wrong: " + ioException.getMessage() + "\n");
        } catch (NoFolderFoundException noFolderFoundException) {
            // Catches the exception when the required folder does not exist
            System.out.println("The folder does not exist.\n");
        } catch (NoDataFileFoundException noDataFileFoundException) {
            // Catches the exception when the data file does not exist while you run
            System.out.println("The required data file does not exist.\n");
        } catch (UnexpectedTaskTypeException unexpectedTaskTypeException) {
            // Catches the exception when an unexpected task type was encountered when loading the task from txt file
            System.out.println("Unexpected task type encountered when loading the task from txt file.\n");
        }

        // Initialise a scanner to receive text input from user
        Scanner scanner = new Scanner(System.in);

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

                if (!instruction.contains("/")) {
                    String[] actionType = instruction.split(" ");
                    if (Objects.equals(actionType[0], "mark")) {
                        if (actionType.length == 1) {
                            throw new MissingTaskToMarkException();
                        }
                        // Mark a task as done
                        markTaskDone(taskList, actionType);
                    } else if (Objects.equals(actionType[0], "unmark")) {
                        if (actionType.length == 1) {
                            throw new MissingTaskToUnmarkException();
                        }
                        // Mark a task as not done
                        unmarkTask(taskList, actionType);
                    } else if (Objects.equals(actionType[0], "delete")) {
                        if (taskList.isEmpty()) {
                            throw new NoTaskException();
                        }
                        // Delete a task from taskList
                        deleteTask(taskList, actionType);
                    } else if (Objects.equals(actionType[0], "todo")) {
                        // Add To Do object
                        if (actionType.length == 1) {
                            throw new MissingToDoDescriptionException();
                        }
                        addToDoTask(taskList, actionType);
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
                    int count = 0;
                    char target = '/';
                    for (int i = 0; i < instruction.length(); i++) {
                        if (instruction.charAt(i) == target) {
                            count++;
                        }
                    }
                    if (Objects.equals(actionType[0], "deadline")) {
                        if (count != 1) {
                            throw new InvalidDateInputException();
                        }
                        // Add Deadline object to the taskList
                        addDeadlineTask(taskList, actionType, typeAndDates);
                    } else if (Objects.equals(actionType[0], "event")) {
                        if (count != 2) {
                            throw new InvalidDateAndTimeInputException();
                        }
                        // Add Event object to the taskList
                        addEventTask(taskList, actionType, typeAndDates);
                    }
                }
                saveTaskListToFile(taskList);
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
            } catch (InvalidDateInputException invalidDateInputException) {
                // Catch the exception when the user types in an invalid date
                System.out.println("Please type in a valid date input. Eg: deadline return book /from " +
                        "2019-12-15 1800\n");
            } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
                // Catch the exception when the user types in an invalid date and time
                System.out.println("Please type in a valid date/time input. Eg: event project meeting /from " +
                        "2019-12-15 1800 /to 2000\n");
            } catch (IOException ioException) {
                System.out.println("Something went wrong: " + ioException.getMessage() + "\n");
            }
        }
    }

    /**
     * Adds a ToDo task to the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects to which the ToDo task will be added.
     * @param actionType An array containing the action type and description of the ToDo task.
     * @throws IOException If an I/O error occurs while attempting to modify the task list or display messages.
     * @throws MissingToDoDescriptionException If the ToDo task description is missing in the input.
     */
    private static void addToDoTask(ArrayList<Task> taskList, String[] actionType)
            throws IOException, MissingToDoDescriptionException {
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
    }

    /**
     * Deletes a task from the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects from which the task will be deleted.
     * @param actionType An array containing the action type and task number to be deleted.
     * @throws NoTaskException If there are no tasks to delete in the taskList.
     */
    private static void deleteTask(ArrayList<Task> taskList, String[] actionType) throws NoTaskException {
        // Deletes a task from taskList
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
    }

    /**
     * Marks a task as done in the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects in which the task will be marked as done.
     * @param actionType An array containing the action type and task number to be marked as done.
     * @throws MissingTaskToMarkException If the task number to mark as done is missing in the input.
     */
    private static void markTaskDone(ArrayList<Task> taskList, String[] actionType) throws MissingTaskToMarkException {
        // Marks a task as done
        int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
        taskList.get(taskNumberInList).markAsDone();

        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + taskList.get(taskNumberInList).toString() + "\n");
    }

    /**
     * Unmarks a task as not done in the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects in which the task will be unmarked.
     * @param actionType An array containing the action type and task number to be unmarked.
     * @throws MissingTaskToUnmarkException If the task number to unmark is missing in the input.
     */
    private static void unmarkTask(ArrayList<Task> taskList, String[] actionType) throws MissingTaskToUnmarkException {
        // Marks a task as not done
        int taskNumberInList = Integer.parseInt(actionType[1]) - 1;
        taskList.get(taskNumberInList).markAsNotDone();

        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println("  " + taskList.get(taskNumberInList).toString() + "\n");
    }

    /**
     * Adds a Deadline task to the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects to which the Deadline task will be added.
     * @param actionType An array containing the action type and description of the Deadline task.
     * @param typeAndDates An array containing the action type, description and due date/time of the Deadline task.
     * @throws IOException If an I/O error occurs while attempting to modify the task list or display messages.
     * @throws MissingDeadlineException If the due date/time of the Deadline task is missing in the input.
     * @throws MissingDescriptionAndDeadlineException If the Deadline task description and due date/time are missing in the input.
     */
    private static void addDeadlineTask(ArrayList<Task> taskList, String[] actionType, String[] typeAndDates)
            throws IOException, MissingDeadlineException, MissingDescriptionAndDeadlineException {
        // Constructs the description of the deadline task from the user input
        String taskDescription = "";
        for (int i = 1; i < actionType.length; i++) {
            if (i == actionType.length - 1) {
                taskDescription += actionType[i];
            } else {
                taskDescription += actionType[i] + " ";
            }
        }
        // Constructs the task due date/time
        String[] dates = typeAndDates[1].split(" ");
        String taskBy = "";
        for (int i = 1; i < dates.length; i++) {
            if (i == dates.length - 1) {
                taskBy += dates[i];
            } else {
                taskBy += dates[i] + " ";
            }
        }
        // Creates the deadline object with the taskDescription and taskBy
        Deadline deadline = new Deadline(taskDescription, taskBy);
        taskList.add(deadline);

        System.out.println("Got it. I've added this task:");
        System.out.println("  " + deadline.toString());
        if (taskList.size() <= 1) {
            System.out.println("Now you have " + taskList.size() + " task in the list.\n");
        } else {
            System.out.println("Now you have " + taskList.size() + " tasks in the list.\n");
        }
    }

    /**
     * Adds an Event task to the provided task list based on user input.
     *
     * @param taskList The ArrayList containing the list of Task objects to which the Event task will be added.
     * @param actionType An array containing the action type and description of the Event task.
     * @param typeAndDates An array containing the action type, description, start date/time, and end date/time of Event task.
     * @throws IOException If an I/O error occurs while attempting to modify the task list or display messages.
     * @throws MissingDescriptionAndDurationException If the Event task description and duration are missing in input.
     * @throws MissingDurationException If the duration of the Event task is missing in the input.
     */
    private static void addEventTask(ArrayList<Task> taskList, String[] actionType, String[] typeAndDates)
            throws IOException, MissingDescriptionAndDurationException, MissingDurationException {
        // Adds Event object to the taskList
        String[] datesFrom = typeAndDates[1].split(" ");
        String[] datesTo = typeAndDates[2].split(" ");
        String taskDescription = "";
        // Constructs the description of the event task from the user input
        for (int i = 1; i < actionType.length; i++) {
            if (i == actionType.length - 1) {
                taskDescription += actionType[i];
            } else {
                taskDescription += actionType[i] + " ";
            }
        }
        // Constructs the task due date/time duration
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
        // Creates the event object with the taskDescription and taskDuration
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

    /**
     * Saves a list of tasks to a text file.
     *
     * @param taskList The ArrayList containing the list of Task objects to be saved.
     * @throws IOException If an I/O error occurs while attempting to write to the file.
     * @throws InvalidDateAndTimeInputException If an invalid date/time input is encountered.
     */
    private static void saveTaskListToFile(ArrayList<Task> taskList) throws IOException,
            InvalidDateAndTimeInputException {
        // Creates a File object representing the target file path
        File file = new File("./data/evo.txt");
        // Creates a BufferedWriter to write to the file
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        try {
            // Iterates through the taskList and write each task's output message to the file
            for (int i = 0; i < taskList.size(); i++) {
                writer.append(taskList.get(i).outputMsg());
                writer.append("\n");
            }
        } catch (InvalidDateAndTimeInputException invalidDateAndTimeInputException) {
            System.out.println("Please type in a valid date/time input. Eg: event project meeting /from " +
                    "2019-12-15 1800 /to 2000.\n");
        } catch (IOException ioException) {
            System.out.println("Something went wrong: " + ioException.getMessage() + "\n");
        } finally {
            // Close the writer
            writer.close();
        }
    }
}
