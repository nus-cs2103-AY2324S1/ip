import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.MissingResourceException;
import java.util.Scanner;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class Duke {
    private static final String HORIZONTAL_LINE = "____________________________________________________________";
    private static final String TASK_FILENAME = "task-list.txt";
    private final String myName = "Quack-NKN";
    private final Scanner scanner = new Scanner(System.in);
    private TaskList taskList = new TaskList();

    public Duke() {
        interact();
    }

    /**
     * Invoked at the start of the interaction, to greet the user.
     */
    private void start() {
        System.out.print("Hello from ");
        System.out.println(this.myName);
        System.out.println("What can I do for you?");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Invoked at the start before interaction.
     * Read data from file and save to the list of task.
     * Report whether the program should continue or exit by the returned value.
     * If there is an error in writing file, the program should not continue.
     * @return whether the program can continue, true if it can, false otherwise.
     */
    private void readFromDisk() throws FileCorruptedException {
        System.out.println(Duke.HORIZONTAL_LINE);
        System.out.println("Loading data from hard disk ...");
        File f = new File(Duke.TASK_FILENAME);
        try {
            ArrayList<Task> taskList = new ArrayList<>();
            Scanner fileScanner = new Scanner(f);
            while (fileScanner.hasNext()) {
                String[] line = fileScanner.nextLine().split(" ", 3);
                if (line.length == 0 && !fileScanner.hasNext()) {
                    break;
                }
                Task task;
                switch (line[0]) {
                    case "T":
                        task = new ToDo(line[2]);
                        break;
                    case "D":
                        String[] split = line[2].split(" /by ", 2);
                        if (split.length != 2) {
                            throw new FileCorruptedException();
                        }
                        try {
                            LocalDateTime dateTime = DateTimeManager.inputToDate(split[1]);
                            task = new Deadline(split[0], dateTime);
                        } catch (DateTimeManager.DateParseException | DateTimeException e) {
                            throw new FileCorruptedException();
                        }
                        break;
                    case "E":
                        String[] separateByFrom = line[2].split(" /from ", 2);
                        if (separateByFrom.length != 2) {
                            throw new FileCorruptedException();
                        }
                        String[] separateByTo = separateByFrom[1].split(" /to ", 2);
                        if (separateByTo.length != 2) {
                            throw new FileCorruptedException();
                        }
                        try {
                            LocalDateTime startTime = DateTimeManager.inputToDate(separateByTo[0]);
                            LocalDateTime endTime = DateTimeManager.inputToDate(separateByTo[1]);
                            task = new Event(separateByFrom[0], startTime, endTime);
                        } catch (DateTimeManager.DateParseException | DateTimeException e) {
                            throw new FileCorruptedException();
                        }
                        break;
                    default:
                        throw new FileCorruptedException();
                }
                if (line[1].equals("1")) {
                    task.markAsDone();
                } else if (line[1].equals("0")) {
                    task.markAsNotDone();
                } else {
                    throw new FileCorruptedException();
                }
                taskList.add(task);
            }
            this.taskList = new TaskList(taskList);
            fileScanner.close();
        } catch (FileNotFoundException fileError) {
            try {
                f.createNewFile();
            } catch (IOException ioError) {
                System.out.println("Quack, an error has occurred while trying to save data to hard disk.");
                System.out.println("Starting with an empty task list.");
                return;
            }
        }

        System.out.println("Done loading.");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Invoked at the end of the programme, to leave an exit message to user.
     */
    private void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        System.out.println(Duke.HORIZONTAL_LINE);
    }

    /**
     * Main programme to allow user to input and respond accordingly.
     * Available commands:
     * - bye: to exit the programme
     * - list: to list out the current task list
     * - list {date}: to list out all events happening on that date or deadlines before/on that date
     * - list {todo/deadline/event}: list out all todo items / deadline items / event items
     * - mark {number}: to mark the task with the corresponding index in the list as done
     * - unmark {number}: to mark the task with the corresponding index in the list as not done
     * - todo {taskname}: to add a new task as a to-do item (no deadline or time)
     * - event {taskname} /from {starttime} /to {endtime}: to add a new task as an event (with start time and end time)
     * - deadline {taskname} /by {time}: to add a new task as a deadline (with deadline time)
     * Error handling is done in the individual functions, not here.
     * Datetime format: "{date} {time}"
     * Date format: either "today", "tmr", "tomorrow", or DD/MM/YYYY
     * Time format: either "{HH}am", "{HH}pm", "{HH:MM}am" or "{HH:MM}pm"
     */
    private void interact() {
        try {
            readFromDisk();
        } catch (FileCorruptedException e) {
            System.out.println("Quack, memory was found to be corrupted!");
            boolean isContinuing = this.handleFileCorrupted();
            if (!isContinuing) {
                return;
            }
        }

        this.start();
        label:
        while (true) {
            // receive input
            System.out.print("In: ");
            String command = this.scanner.nextLine();
            System.out.println(Duke.HORIZONTAL_LINE);
            String[] commandArgs = command.split(" ", 2);

            // exit
            switch (commandArgs[0]) {
                case "":
                    continue;

                case "exit":
                case "bye":
                    break label;


                // show list
                case "list":
                    this.showList(commandArgs);
                    break;

                // mark as done
                case "mark":
                    this.markTaskAsDone(commandArgs);
                    break;

                // mark as not done
                case "unmark":
                    this.markTaskAsNotDone(commandArgs);
                    break;

                // add to-do
                case "todo":
                    this.addToDoToList(commandArgs);
                    break;

                // add event
                case "event":
                    this.addEventToList(commandArgs);
                    break;

                // add deadline
                case "deadline":
                    this.addDeadlineToList(commandArgs);
                    break;

                // delete task
                case "delete":
                    this.deleteTask(commandArgs);
                    break;

                // save data to hard disk
                case "save":
                    this.saveData();
                    break;

                // anything else
                default:
                    this.echo(command);
                    break;
            }

            System.out.println(Duke.HORIZONTAL_LINE);
        }
        this.exit();
    }

    /**
     * Perform input checking and add the to-do task to task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addToDoToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the to-do task!");
            return;
        }
        // no empty to-do task
        if (commandArgs[1].equals("")) {
            System.out.println("Quack, no empty to-do please!");
            return;
        }

        Task newTask = new ToDo(commandArgs[1]);
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        showTaskCount();
    }

    /**
     * Perform input checking and add the event to the task list.
     * @param commandArgs the arguments provided in the command
     */
    private void addEventToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the event!");
            return;
        }

        // /from keyword
        String[] separateByFrom = commandArgs[1].split(" /from ", 2);
        // no empty event
        if (separateByFrom[0].equals("")) {
            System.out.println("Quack, no empty event please!");
            return;
        }
        // /from keyword must exist
        if (separateByFrom.length != 2) {
            System.out.println("Quack, keyword '/from' not found. " +
                    "It must be present for me to mark the start time!");
            return;
        }

        // /to keyword
        String[] separateByTo = separateByFrom[1].split(" /to ", 2);
        // no empty start time
        if (separateByTo[0].equals("")) {
            System.out.println("Quack, no empty start time please!");
            return;
        }
        // /to keyword must exist
        if (separateByTo.length != 2) {
            System.out.println("Quack, keyword '/to' not found. " +
                    "It must be present after the '/from' keyword for me to mark the end time!");
            return;
        }
        // no empty end time
        if (separateByTo[1].equals("")) {
            System.out.println("Quack, no empty end time please!");
            return;
        }

        Task newTask;
        try {
            LocalDateTime startTime = DateTimeManager.inputToDate(separateByTo[0]);
            LocalDateTime endTime = DateTimeManager.inputToDate(separateByTo[1]);
            newTask = new Event(separateByFrom[0], startTime, endTime);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            System.out.println("Quack, I do not understand your datetime.");
            return;
        }
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    /**
     * Perform input checking and add a new task as deadline to the current list of tasks.
     * @param commandArgs the list of arguments in the command
     */
    private void addDeadlineToList(String[] commandArgs) {
        // number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you did not provide me with the deadline!");
            return;
        }

        String[] separateByBy = commandArgs[1].split(" /by ", 2);
        // /by keyword must exist
        if (separateByBy.length != 2) {
            System.out.println("Quack, keyword '/by' not found." +
                    "It must be present for me to mark the deadline time!");
            return;
        }
        // no empty deadline
        if (separateByBy[0].equals("")) {
            System.out.println("Quack, no empty deadline task please!");
            return;
        }
        // no empty end time
        if (separateByBy[1].equals("")) {
            System.out.println("Quack, no empty deadline time please!");
            return;
        }

        Task newTask;
        try {
            LocalDateTime dateTime = DateTimeManager.inputToDate(separateByBy[1]);
            newTask = new Deadline(separateByBy[0], dateTime);
        } catch (DateTimeManager.DateParseException | DateTimeException e) {
            System.out.println("Quack, I do not understand your datetime.");
            return;
        }
        this.taskList.add(newTask);
        System.out.println("Got it. I've added this task:");
        System.out.println(newTask);
        this.showTaskCount();
    }

    /**
     * Notify user of how many tasks are currently in the task list.
     */
    private void showTaskCount() {
        System.out.println("Now you have " + this.taskList.size() + " in the list.");
    }

    /**
     * Show the current list of tasks to user.
     * @param commandArgs the list of arguments in the command
     */
    private void showList(String[] commandArgs) {
        if (commandArgs.length != 1) {
            String[] args = commandArgs[1].split(" ");
            boolean isExcludingDone = false;
            LocalDate date = null;
            for (String arg: args) {
                switch (arg) {
                    case "todo":
                    case "deadline":
                    case "event":
                        break;
                    case "-d":
                        isExcludingDone = true;
                        break;
                    default:
                        try {
                            date = DateTimeManager.parseDate(arg);
                        } catch (DateTimeManager.DateParseException e) {
                            System.out.println("Quack, unrecognised \"" + arg + "\"");
                            return;
                        }
                }
            }
            switch (args[0]) {
                case "todo":
                    System.out.println("Alright, here are your to-do tasks"
                            + (isExcludingDone ? " not done" : "")
                            + ":"
                    );
                    this.taskList.displayTodos(isExcludingDone);
                    break;
                case "deadline":
                    System.out.println("Alright, here are your deadlines"
                            + (isExcludingDone ? " not done" : "")
                            + (date == null ? "" : " before " + DateTimeManager.dateToDisplay(date))
                    );
                    this.taskList.displayDeadlines(isExcludingDone, date);
                    break;
                case "event":
                    System.out.println("Alright, here are your events"
                            + (isExcludingDone ? " not done" : "")
                            + (date == null ? "" : " happening on " + DateTimeManager.dateToDisplay(date))
                    );
                    this.taskList.displayEvents(isExcludingDone, date);
                    break;
                default:
                    String displayString = DateTimeManager.dateToDisplay(date);
                    System.out.println("Alright, here are the deadlines before "
                        + displayString + " and events happening " + displayString
                    );
                    this.taskList.displayTasks(isExcludingDone, date);
                    break;
            }
        } else {
            System.out.println("Here is your list of tasks:");
            this.taskList.displayTasks(false, null);
        }
    }

    /**
     * Perform input checking and mark a task with corresponding index as done.
     * @param commandArgs the list of arguments in the command
     */
    private void markTaskAsDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        try {
            Task task = this.taskList.markTaskAsDone(index);
            System.out.println("Nice! I've marked this task as done:");
            System.out.println(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            System.out.println("Quack, the task number you provide is too big!");
        }
    }

    /**
     * Perform input checking and mark a task with corresponding index as not done.
     * @param commandArgs the list of arguments in the command
     */
    private void markTaskAsNotDone(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        try {
            Task task = this.taskList.markTaskAsNotDone(index);
            System.out.println("OK, I've marked this task as not done yet:");
            System.out.println(task);
        } catch (TaskList.TaskIndexOutOfRange e) {
            System.out.println("Quack, the task number you provide is too big!");
        }
    }

    /**
     * Perform input checking and delete the task if input is valid, given a command.
     * @param commandArgs the list of arguments in the command.
     */
    private void deleteTask(String[] commandArgs) {
        int index = this.getTaskIndexFromCommand(commandArgs);
        if (index == -1) {
            return;
        }
        try {
            Task taskDeleted = this.taskList.deleteTask(index);
            System.out.println("Noted, I've removed this task:");
            System.out.println(taskDeleted);
            this.showTaskCount();
        } catch (TaskList.TaskIndexOutOfRange e) {
            System.out.println("Quack, the task number you provided is too big!");
        }
    }

    /**
     * Get task index from command (second element) and validate input.
     * If command is invalid, return the number. Else, return -1.
     * Print out the relevant error message, if there is error.
     * Else, return
     * @param commandArgs the list of command arguments separated by space
     * @return the task index, or -1 if input is invalid
     */
    private int getTaskIndexFromCommand(String[] commandArgs) {
        // check for number of arguments
        if (commandArgs.length != 2) {
            System.out.println("Quack, you have provided wrong number of arguments!");
            return -1;
        }

        // check if second argument is positive integer
        String indexString = commandArgs[1];
        if (indexString.matches("0+") || !indexString.matches("\\d+")) {
            System.out.println("Quack, you need to provide a positive integer!");
            return -1;
        }

        return Integer.parseInt(indexString) - 1;
    }

    /**
     * Save data to hard disk, with the current task list.
     */
    private void saveData() {
        System.out.println("Saving data ...");
        try {
            this.taskList.saveData(Duke.TASK_FILENAME);
        } catch (IOException e) {
            System.out.println("An error has occurred while writing to hard disk!");
        }
    }

    /**
     * Ask the user on the course of action to take, when file is corrupted.
     */
    private boolean handleFileCorrupted() {
        System.out.println("What do you wish to do?");
        System.out.println("1. Quit, let me restore the data manually");
        System.out.println("2. Continue with an empty task list");
        System.out.print("Please indicate your option (1/2): ");

        while (true) {
            String response = this.scanner.nextLine();
            System.out.println(Duke.HORIZONTAL_LINE);
            switch (response) {
                case "1":
                    this.exit();
                    return false;
                case "2":
                    return true;
                default:
                    System.out.print("Quack, I do not understand your option, please indicate again (1/2): ");
            }
        }
    }

    /**
     * Echo command back to the user.
     * @param command the command from the user
     */
    private void echo(String command) {
        if (command.equals("quack")) {
            System.out.println("Quack quack quack");
        } else {
            System.out.println("Quack, what do you mean when you say " + command);
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
