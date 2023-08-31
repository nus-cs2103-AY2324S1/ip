import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * The main class that initiates the chatbot application
 */
public class Duke {

    private final TaskList taskList ;
    private final String FILE_PATH = "./src/main/data/duke.txt";
    private final Storage storage = new Storage(FILE_PATH);



    /**
     * Initializes the Chatbot with an empty task list
     */
    public Duke() {
        this.taskList = new TaskList();
    }

    /**
     * Check if input str is a number
     *
     * @param str input string
     * @return true if str is a number, false otherwise
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Marks a task as done and provides user feedback
     *
     * @param taskIndex Index of the task to be marked as done, starts from '1'
     */
    public void markTaskByBot(int taskIndex) {
        taskList.markTaskAsDone(taskIndex - 1);
        saveTasksToFile();
        System.out.println("____________________________________________________________\n" +
                " Nice! I've marked this task as done:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");
    }

    /**
     * Marks a task as not done and provides user feedback
     *
     * @param taskIndex Index of the task to be marked as not done, starts from '1'
     */
    public void unmarkTaskByBot(int taskIndex) {
        taskList.markTaskAsNotDone(taskIndex - 1);
        saveTasksToFile();
        System.out.println("____________________________________________________________\n" +
                " OK, I've marked this task as not done yet:\n" +
                taskList.getTaskDetails(taskIndex - 1) +
                "\n____________________________________________________________");
    }



    /**
     * Deletes a task from the task list based on the provided input.
     *
     * @param inputStr The input string containing the index of the task to be deleted.
     * @throws DeleteException If the input string is not numeric or if the task index is out of valid range.
     */
    public void deleteTaskByBot(String inputStr) throws DeleteException {
        if (!isNumeric(inputStr)) {
            throw new DeleteException();
        }

        int taskIndex = Integer.parseInt(inputStr);
        if (taskIndex < 1 || taskIndex > taskList.getTaskCount()) {
            throw new DeleteException();
        } else {
            System.out.println("____________________________________________________________\n" +
                    " Noted. I've removed this task:\n" +
                    this.taskList.getTaskDetails(taskIndex - 1).toString() +
                    "\n Now you have " + (taskList.getTaskCount() - 1 )+ " tasks in the list.\n" +
                    "____________________________________________________________");
            taskList.deleteTask(taskIndex - 1);
        }
    }

    /**
     * Adds a task to the task list based on task type and description
     *
     * @param taskType The type of task (todo, deadline, event)
     * @param description The description of the task
     * @throws DukeException If there is an error adding the task
     */
    public void addTaskByBot(String taskType, String description) throws DukeException {
        DateTimeFormatter dataFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        Task newTask = null;
        String taskDescription;
        String deadlineTiming;
        String eventFrom;
        String eventTo;

        // invoke diff task's constructor
        switch (taskType) {
        case "todo":
            newTask = new Todo(description);
            break;
        case "deadline":
            // make sure /by exist to avoid exception in "split() array"
            if (!description.contains("/by")) {
                throw new DeadlineException();
            } else {
                // split the description to task description and timing, with /by in between
                String[] desArray = description.split("/by", 2);
                taskDescription = desArray[0];
                deadlineTiming = desArray[1];

                if (taskDescription.isEmpty() || deadlineTiming.isEmpty()) {
                    throw new DeadlineException();
                }

                try {
                    LocalDate.parse(deadlineTiming.trim(), dataFormatter);  // Validate date format
                    newTask = new Deadline(taskDescription, deadlineTiming.trim());
                } catch (DateTimeParseException e) {
                    throw new DeadlineException();
                }
                break;
            }
        case "event":
            // make sure /from and /to exist to avoid exception in "split() array"
            if (!description.contains("/from") || !description.contains("/to")) {
                throw new EventException();
            } else {
                // split the description to task description and timing, with /from in between
                String[] desArray = description.split("/from", 2);
                taskDescription = desArray[0];
                // when from /to comes before /from
                if (taskDescription.contains("/to")) {
                    throw new EventException();
                }

                // split the timing description further, with /to in between
                String[] timingArr = desArray[1].split("/to", 2);
                eventFrom = timingArr[0];
                eventTo = timingArr[1];

                if (eventFrom.isEmpty() || eventTo.isEmpty()) {
                    throw new EventException();
                }

                try {
                    LocalDate.parse(eventFrom.trim(), dataFormatter);  // Validate date format
                    LocalDate.parse(eventTo.trim(), dataFormatter);  // Validate date format
                    newTask = new Event(taskDescription, eventFrom.trim(), eventTo.trim());
                    break;
                } catch (DateTimeParseException e) {
                    throw new EventException();
                }

            }
        }

        // provides feedback for the task created
        if (newTask != null) {
            taskList.addTask(newTask);
            System.out.println("____________________________________________________________\n" +
                    " Got it. I've added this task:\n" +
                    newTask.toString() +
                    "\n Now you have " + taskList.getTaskCount() + " tasks in the list.\n" +
                    "____________________________________________________________");
        }
    }

    private void loadTasksFromFile() {
        for (Task taskData : storage.loadTasks()) {
            this.taskList.addTask(taskData);
        }

        if (!this.taskList.isEmpty()) {
            System.out.println(this.taskList.toString());
        }
    }

    /**
     * Saves the tasks to the storage file
     */
    private void saveTasksToFile(TaskList taskList) {
        this.storage.saveTasks(taskList);
    }

//    public boolean handleCommand(String userInput) throws DukeException {
//        String[] parts = userInput.split(" ", 2);
//        String command = parts[0];
//        int taskIndex;
//
//        if (command.equals("mark") && parts.length > 1) {
//            taskIndex = Integer.parseInt(parts[1]);
//            this.markTaskByBot(taskIndex);
//
//        } else if (command.equals("unmark") && parts.length > 1) {
//            taskIndex = Integer.parseInt(parts[1]);
//            this.unmarkTaskByBot(taskIndex);
//
//        } else if (userInput.equals("bye")) {
//            return false;  // Exit the loop when user types "bye"
//
//        } else if (userInput.equals("list")) {
//            taskList.displayTasks();
//
//        } else if (command.equals("todo") && parts.length > 1) {
//            addTaskByBot("todo", parts[1]);
//
//        } else if (command.equals("deadline") && parts.length > 1) {
//            addTaskByBot("deadline", parts[1]);
//
//        } else if (command.equals("event") && parts.length > 1) {
//            addTaskByBot("event", parts[1]);
//
//        } else if (command.equals("delete") && parts.length > 1) {
//            deleteTaskByBot(parts[1]);
//        } else {
//            throw new DukeException("I'm sorry, but I don't know what that means :-(");
//        }
//        return true;
//    }

    public boolean handleCommand(Command command) throws DukeException {
        String commandType = command.getCommandType();
        String description = command.getDescription();
        int taskIndex = command.getTaskIndex();
        LocalDate deadlineDate = command.getDeadlineDate();
        LocalDate eventFromDate = command.getEventFromDate();
        LocalDate eventToDate = command.getEventToDate();

        switch (commandType) {
        case "mark":
            this.markTaskByBot(description);
            break;
        case "unmark":
            this.unmarkTaskByBot(description);
            break;
        case "bye":
            return false;
        case "list":
            Ui.showMessage(this.taskList.toString());
            break;
        case "todo":
            this.addTaskByBot("todo", description);
            break;
        case "deadline":
            this.addTaskByBot("deadline", description, deadlineDate);
            break;
        case "event":
            this.addTaskByBot("event", description, eventFromDate, eventToDate);
            break;
        case "delete":
            this.deleteTaskByBot(taskIndex);
            break;
        default:
            throw new DukeException("I'm sorry, but I don't know what that means :-(");
        }
        return true;
    }

//    /**
//     * Start the chatbot interaction loop, end when "bye" is given
//     */
//    public void start() {
//        Scanner scanner = new Scanner(System.in);
//
//        String helloMessage = "____________________________________________________________\n" +
//                " Hello! I'm Najib\n" +
//                " What can I do for you?\n" +
//                "____________________________________________________________\n";
//
//        String byeMessage = "____________________________________________________________\n" +
//                " Bye. Hope to see you again soon!\n" +
//                "____________________________________________________________";
//
//        System.out.println(helloMessage);
//        String userInput;
//
//        loadTasksFromFile();
//        boolean isContinuing = true;
//        while (isContinuing) {
//            try {
//                userInput = scanner.nextLine();
//                isContinuing = handleCommand(userInput);
//                saveTasksToFile();
//            } catch (DukeException e) {
//                System.out.println("____________________________________________________________\n" +
//                        " ☹ OOPS!!! " + e.getMessage() + "\n" +
//                        "____________________________________________________________");
//            }
//        }
//        System.out.println(byeMessage);
//        scanner.close();
//    }

    public void start() {
        Ui.showWelcomeMessage();
        String userInput = null;
        Command parsedCommand = null;

        this.loadTasksFromFile();

        boolean isContinuing = true;
        while (isContinuing) {
            try {
                userInput = Ui.getUserInput();
                parsedCommand = Parser.parse(userInput);
                isContinuing = handleCommand(parsedCommand);
                this.saveTasksToFile(this.taskList.getTasks());
            } catch (DukeException e) {
               Ui.showErrorMessage(e.getMessage());
            }
        }

        Ui.showGoodByeMessage();
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.start();
    }
}
