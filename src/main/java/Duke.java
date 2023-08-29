import java.io.*;
import java.nio.file.Path;
import java.util.Scanner;
import java.util.ArrayList;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Duke
 *
 * CS2103T AY23/24 Semester 1
 * iP - Individual Project
 * Duke Project
 *
 * @author Freddy Chen You Ren
 */
public class Duke {
    static String HORIZONTAL_LINE = "    ____________________________________________________________"; //60 underscores.
    static String INDENT = "    "; //4 spaces.
    static ArrayList<Task> taskList = new ArrayList<>(100);
    enum Command {
        BYE, LIST, MARK, UNMARK, DELETE, DEADLINE, TODO, EVENT, UNKNOWN
    }

    /**
     * Function to greet the User.
     */
    public static void greet() {
        System.out.println("\nStarting SeeWhyAre Bot...");
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    Hello! I'm SeeWhyAre Bot!");
        System.out.println("    What can I do for you?");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to say goodbye to the User and end the program.
     */
    public static void farewell() {
        System.out.println(HORIZONTAL_LINE);
        System.out.println("    You are closing the SeeWhyAre chat bot.");
        System.out.println("    Bye bye. Please use me again soon!");
        System.out.println(HORIZONTAL_LINE);
    }

    /**
     * Function to add any given input into our taskList.
     */
    public static void addTask(String description) {
        System.out.println(HORIZONTAL_LINE);
        Task task = new Task(description);
        taskList.add(task);
        System.out.printf("    added: %s%n", description);
        System.out.println(HORIZONTAL_LINE);
    }

    public static void deleteTask(String deleteInput)
            throws EmptyDescriptionException, IOException {
        String[] words = deleteInput.split("\\s+"); // Split input by space, put into array
        //Check for valid length
        if (words.length <= 1) {
            throw new EmptyDescriptionException("Please provide the task index to be deleted.");
        }

        //Try parsing into integer to get deleteIndex
        try {
            int deleteIndex = Integer.parseInt(words[1]) - 1; // Potential Error cannot parse to integer
            System.out.println(HORIZONTAL_LINE);

            if (deleteIndex >= 0 && deleteIndex < taskList.size()) {
                Task removedTask = taskList.remove(deleteIndex); //Actual task can be todo, deadline, or event
                System.out.println("     Noted. I've removed this task:");
                System.out.printf("       %s\n", removedTask.toString());
                System.out.printf("     Now you have %d task(s) in the list.\n", taskList.size());
            } else {
                System.out.println("     OOPS!!! The task index is invalid.\n");
                System.out.printf("    You currently have %d task(s).\n", taskList.size());
            }
            System.out.println(HORIZONTAL_LINE);

        } catch (NumberFormatException e) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("     OOPS!!! Please enter the index after 'delete' command.");
            System.out.println("     For example: delete 5");
            System.out.println("     This will remove Task 5 from your Task List, assuming you have at least 5 tasks.");
            System.out.println(HORIZONTAL_LINE);
        }
        clearAllData();
        updateData();
    }

    /**
     * Function to mark a given task as done.
     * @param taskIndex the index of the task to be marked as done.
     */
    public static void markTask(int taskIndex) throws IOException {
        System.out.println(HORIZONTAL_LINE);
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            System.out.printf("     Invalid Index of Task. You currently have %d Task(s)\n", taskList.size());
        } else {
            Task task = taskList.get(taskIndex);
            task.markAsDone();
            System.out.println("     Nice! I've marked this task as done:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        System.out.println(HORIZONTAL_LINE);
        clearAllData();
        updateData();
    }

    /**
     * Function to mark a given task as done.
     * @param taskIndex the index of the task to be marked as not done yet.
     */
    public static void unmarkTask(int taskIndex) throws IOException {
        System.out.println(HORIZONTAL_LINE);
        if (taskIndex < 0 || taskIndex >= taskList.size()) {
            System.out.printf("    Invalid Index of Task. You currently have %d Task(s)\n", taskList.size());
        } else {
            Task task = taskList.get(taskIndex);
            task.markAsNotDone();
            System.out.println("     OK, I've marked this task as not done yet:");
            System.out.printf("       [%s] %s\n", task.getStatusIcon(), task.description);
        }
        System.out.println(HORIZONTAL_LINE);
        clearAllData();
        updateData();
    }

    /**
     * Function to list out all tasks.
     */
    public static void listAllTasks() {
        if (taskList.isEmpty()) {
            System.out.println(HORIZONTAL_LINE);
            System.out.println("    No tasks for now!");
            System.out.println(HORIZONTAL_LINE);
        } else {
            System.out.println(HORIZONTAL_LINE);
            for (int i = 0; i < taskList.size(); i++) {
                System.out.printf("     %d.%s\n", i + 1, taskList.get(i).toString());
            }
            System.out.println(HORIZONTAL_LINE);
        }
    }

    /**
     * Save a Task after it has been successfully inputted by user.
     * @param task the Task that is to be saved.
     * @param isAppend a Boolean to determine if we should add a new line in the saved text file.
     */
    public static void saveTask(Task task, boolean isAppend) throws IOException {
        // Format of saving tasks follow CS2103T Website:
        // T | 1 | read book
        // D | 0 | return book | June 6th
        // E | 0 | project meeting | Aug 6th 2pm | Aug 6th 4pm
        FileOutputStream outputStream = new FileOutputStream("src/main/data/duke.txt", isAppend);
        //Use a BufferedWriter
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        String[] saved = new String[5]; // Cannot be more than 5 separate parts. 5th part is only for Event

        //saved[0]
        if (task instanceof Deadline) {
            saved[0] = "D";
            saved[3] = ((Deadline) task).getDueDate();
        } else if (task instanceof Event) {
            saved[0] = "E";
            saved[3] = ((Event) task).getStartTime();
            saved[4] = ((Event) task).getEndTime();
        } else {
            //Todo task
            saved[0] = "T";
        }

        //saved[1] and saved[2]
        saved[1] = task.isDone ? "1" : "0";
        saved[2] = task.getDescription();

        if (isAppend) {
            bufferedWriter.newLine();
        }
        bufferedWriter.write(String.join(" | ", saved));
        bufferedWriter.close();
    }

    /**
     * Loads tasks saved previously from Hard Disk.
     * @throws IOException throws an IO Exception if the file is corrupted or invalid.
     */
    private static void loadTasks() throws IOException {
        //Use FileInputStream and BufferedReader, opposite of saveTask()
        // try-catch to check if file exists or if file is correct format
        try {
            Path directory = Path.of("./data");
            if (!Files.exists(directory)) {
                System.out.println("System Message: Directory 'data' does not exist. Creating one...");
                Files.createDirectories(directory); // Create the directory if it doesn't exist
            }
            System.out.println("System Message: Directory 'data' exists!");

            Path file = Path.of("./data/duke.txt");
            if (!Files.exists(file)) {
                System.out.println("System Message: File 'duke.txt' does not exist. Creating one...");
                Files.createFile(file); // Create the file if it doesn't exist
            }
            System.out.println("System Message: File 'duke.txt' exists! Loading past data...");

            FileInputStream inputStream = new FileInputStream("src/main/data/duke.txt");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader((inputStream)));
            String currentLine;

            try {
                //Recall delimiter "|" and get details of the tasks and add tasks
                while ((currentLine = bufferedReader.readLine()) != null) {
                    if (isValidTaskLine(currentLine)) {
                        // Parse the line and create tasks
                        String[] content = currentLine.split(" \\| ");
                        //System.out.printf("Content: %s", content);
                        String taskDescription = content[2];
                        //System.out.printf("Event details: %s\n", currentLine);
                        Task taskFromHardDisk;

                        // Now check which type of task it belongs to
                        // Create the task and add task to taskList
                        switch(content[0]) {
                        case "E":
                            taskFromHardDisk = new Event(taskDescription, content[3], content[4]);
                            //Potential error for content[3]
                            break;
                        case "D":
                            taskFromHardDisk = new Deadline(taskDescription, content[3]);
                            //Potential error for content[3]
                            break;
                        default:
                            taskFromHardDisk = new Todo(taskDescription);
                            break;
                        }

                        //Check if task is done
                        if (content[1].equals("1")) {
                            taskFromHardDisk.markAsDone();
                        } else {
                            taskFromHardDisk.markAsNotDone();
                        }
                        taskList.add(taskFromHardDisk);

                    } else {
                        System.out.printf("Skipping corrupted line: %s\n", currentLine);
                    }


                }
            } catch (IOException e) {
                // Handle exception while reading the file
                System.out.printf("Error while reading file: %s", e.getMessage());
            }
            bufferedReader.close();
        } catch (IOException e) {
            // Handle exception while creating directory or file
            System.out.printf("Error while creating directory: %s", e.getMessage());
        }
    }

    public static boolean isValidTaskLine(String line) {
        String[] tokens = line.split("\\|");

        if (tokens.length >= 3 && tokens.length <= 5) { // Valid number of segments: 3-5 (Todo-Event)
            String taskType = tokens[0].trim();
            String completionStatus = tokens[1].trim();
            String description = tokens[2].trim();

            if (taskType.matches("[TDE]") && completionStatus.matches("[01]") && !description.isEmpty()) {
                return true; // Line matches expected format
            }
        }

        return false; // Line is not valid
    }

    /**
     * Clears lines of task status in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or currupted.
     */
    private static void clearAllData() throws IOException {
        FileOutputStream outputStream = new FileOutputStream("src/main/data/duke.txt");
        BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter((outputStream)));
        bufferedWriter.close();
    }

    /**
     * Updates all lines of task status in Hard Disk.
     * @throws IOException throws IO Exception if file format is invalid or currupted.
     */
    private static void updateData() throws IOException {
        for (int i = 0; i < taskList.size(); i++) {
            if (i != 0) {
                saveTask(taskList.get(i), true);
            } else {
                saveTask(taskList.get(i), false);
            } //BUG FOR EVENT TASK, End Time get erased, Type kept getting changed to Deadline
        }
    }

    public static void main(String[] args) throws InvalidCommandException, IOException {
        Boolean repeatFlag = true;
        loadTasks();
        greet();
        Scanner scanner = new Scanner(System.in);

        while (repeatFlag) {
            try {
                System.out.println("Enter Command: ");
                String userInput = scanner.nextLine();
                //Level-4 Inrement: Use userInput.startWith() to check first word before splitting

                String[] words = userInput.split("\\s+"); // Split input by space, put into array
                String formattedInput = userInput.toLowerCase();
                String firstWord = words[0].toUpperCase();

                Command command; //Use enum
                try {
                    command = Command.valueOf(firstWord);
                } catch (IllegalArgumentException e) {
                    command = Command.UNKNOWN;
                }

                //A-Enum: Use switch-case instead of if-else for neater code
                switch (command) {
                case BYE:
                    farewell();
                    repeatFlag = false;
                    break;
                case LIST:
                    listAllTasks();
                    break;
                case MARK:
                    int taskIndex = Integer.parseInt(words[1]) - 1;
                    markTask(taskIndex);
                    break;
                case UNMARK:
                    taskIndex = Integer.parseInt(words[1]) - 1; //Same variable name taskIndex as above
                    unmarkTask(taskIndex);
                    break;
                case DELETE:
                    deleteTask(userInput);
                    break;
                case DEADLINE:
                    Deadline.handleDeadlineTask(userInput);
                    break;
                case TODO:
                    Todo.handleTodoTask(userInput);
                    break;
                case EVENT:
                    Event.handleEventTask(userInput);
                    break;
                case UNKNOWN:
                    throw new InvalidCommandException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (IOException e) {
                System.err.println(HORIZONTAL_LINE + "\n" + e.toString() + HORIZONTAL_LINE);
            } catch (EmptyDescriptionException e) {
                e.printExceptionMessage();
            } catch (InvalidCommandException e) {
                e.printExceptionMessage();
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("     Please enter valid Integer index!");
                System.out.printf("     You currently have %d tasks", taskList.size());
            } catch (Exception e) {
                System.out.println(HORIZONTAL_LINE);
                System.out.println("     Very Invalid command! Please enter valid commands");
                System.out.println(HORIZONTAL_LINE);
            }
        }
    }
}

/**
 * String logo = " ____        _        \n"
 *                 + "|  _ \\ _   _| | _____ \n"
 *                 + "| | | | | | | |/ / _ \\\n"
 *                 + "| |_| | |_| |   <  __/\n"
 *                 + "|____/ \\__,_|_|\\_\\___|\n";
 *         System.out.println("Hello from\n" + logo);
 */
