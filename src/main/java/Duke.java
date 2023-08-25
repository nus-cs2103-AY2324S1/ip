import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.BufferedWriter;

public class Duke {
    private static String divider = "____________________________________________________________";
    private static String indent = "     ";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static String filePath = "../../../data/duke.txt";

    /*
     * Adds indentation to the string given and prints it out.
     */
    public static void formatString(String s) {
        System.out.println(indent + s);
    }

    public static void printDivider() {
        formatString(divider);
    }

    public static void greet() {
        String chatbotName = "Miles";

        printDivider();
        formatString("Hey! I'm " + chatbotName + "!");
        formatString("What can I do for you, my friend?");
        printDivider();
    }

    public static void exit() {
        printDivider();
        formatString("Stay safe my friend. Hope to see you again soon man.");
        printDivider();
    }

    public static void markTaskAsDone(int taskNum) {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            return;
        }

        Task task = taskList.get(taskNum - 1);
        task.markAsDone();
        printDivider();
        formatString(" Nice! I've marked this task as done:");
        formatString("  " + task.toString());
        printDivider();
    }

    public static void markTaskAsUndone(int taskNum) {
        if (taskNum <= 0 || taskNum > taskList.size()) {
            DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
            return;
        }

        Task task = taskList.get(taskNum - 1);
        task.markAsUndone();
        printDivider();
        formatString(" OK, I've marked this task as not done yet:");
        formatString("  " + task.toString());
        printDivider();
    }

    public static void updateTaskStatusFromFile(Task task, String status) {
        if (status.equals("[X]")) {
            task.markAsDone();
        } else {
            task.markAsUndone();
        }
    }

    /**
     * Loads the file from the data folder if it exists, else it creates a new file for users to 
     * store their tasks.
     * 
     * @throws IOException if there is an error creating the file  
     */
    public static void loadFile() {
        try {
            File directory = new File("../../../data");
            File newFile = new File(filePath);

            // check if directory exists
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // check if file exists
            if (!newFile.exists()) {
                newFile.createNewFile();
                return ; 
            }

            Scanner scanner = new Scanner(newFile);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");
                String taskType = parts[0].trim();

                if (taskType.equals("T")) {
                    String status = parts[1].trim();
                    String task = parts[2];
                    Task newTask = new ToDo("todo " + task);

                    updateTaskStatusFromFile(newTask, status);
                    taskList.add(newTask);
                } else if (taskType.equals("D")) {
                    String status = parts[1].trim();
                    String task = parts[2].trim();
                    String deadline = parts[3].trim();
                    Task newTask = new Deadline(task, deadline);

                    updateTaskStatusFromFile(newTask, status);
                    taskList.add(newTask);                    
                } else if (taskType.equals("E")) {
                    String status = parts[1].trim();
                    String task = parts[2].trim();
                    String from = parts[3].trim();
                    String to = parts[4].trim();
                    Task newTask = new Event(task, from, to);

                    updateTaskStatusFromFile(newTask, status);
                    taskList.add(newTask);
                }
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile() {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            for (int i = 0; i < taskList.size(); i += 1) {
                Task currentTask = taskList.get(i);
                String s = currentTask.stringToSave();
                bufferedWriter.write(s);
                bufferedWriter.newLine();
            }

            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * Method that handles input with the command "mark" or "unmark".
     * 
     * @param command the command that the user inputs: either "mark" or "unmark"
     * @param input   what the user inputs 
     * @return        the task number that the user wants to mark or unmark
     */
    public static int getTaskNumber(String command, String input) {
        String taskNum = input.replace(command + " ", "");
        // remove leading and trailing whitespaces
        String trimmedTaskNum = taskNum.trim();
        return Integer.valueOf(trimmedTaskNum);
    }

    /*
     * Method that prints every task in the task list.
     */
    public static void printList() {
        int n = taskList.size();

        printDivider();
        formatString(" Here are the tasks in your list:");
        for (int i = 0; i < n; i += 1) {
            Task currentTask = taskList.get(i);
            String output = " " + (i + 1) + ". " + currentTask.toString();
            formatString(output);
        }
        printDivider();
    }

    public static void printAddedTask(Task task, int n) {
        printDivider();
        formatString(" Got it. I've added this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        printDivider();
    }

    public static void printDeletedTask(Task task, int n) {
        printDivider();
        formatString(" Noted. I've removed this task:");
        formatString("  " + task.toString());
        formatString(" Now you have " + n + " tasks in the list.");
        printDivider();
    }

    public static void handleCommands() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            String input = scanner.nextLine();
            
            if (input.equals("bye")) {
                break;
            } else if (input.equals("")) {
                DukeExceptionHandler.handleEmptyInput();
            } else if (input.equals("list")) {
                printList();
            } else if (input.contains("unmark")) {
                // mark is in the word unmark so this case has to go first
                int taskNum = getTaskNumber("unmark", input);
                markTaskAsUndone(taskNum);
                saveFile();
            } else if (input.contains("mark")) {
                int taskNum = getTaskNumber("mark", input);
                markTaskAsDone(taskNum);
                saveFile();
            } else if (input.contains("todo")) {
                try {
                    ToDo newToDo = new ToDo(input);
                    taskList.add(newToDo);
                    saveFile();
                    printAddedTask(newToDo, taskList.size());
                } catch (IllegalArgumentException e) {
                    DukeExceptionHandler.printErrorMsg(e.getMessage());
                }
            } else if (input.contains("deadline")) {
                try {
                    Deadline newDeadline = new Deadline(input);
                    taskList.add(newDeadline);
                    saveFile();
                    printAddedTask(newDeadline, taskList.size());
                } catch (IllegalArgumentException e) {
                    DukeExceptionHandler.printErrorMsg(e.getMessage());
                } 
            } else if (input.contains("event")) {
                try {
                    Event newEvent = new Event(input);
                    taskList.add(newEvent);
                    saveFile();
                    printAddedTask(newEvent, taskList.size());
                } catch (IllegalArgumentException e) {
                    DukeExceptionHandler.printErrorMsg(e.getMessage());
                } 
            } else if (input.contains("delete")) {
                try {
                    int taskNum = getTaskNumber("delete", input);
                    Task task = taskList.remove(taskNum - 1);
                    saveFile();
                    printDeletedTask(task, taskList.size());
                } catch (IndexOutOfBoundsException e) {
                    int taskNum = getTaskNumber("delete", input);
                    DukeExceptionHandler.handleTaskNumOutOfBounds(taskNum);
                }
                
            } else {
                DukeExceptionHandler.handleUnseenInput();
            }
        }

        scanner.close();
    }

    public static void main(String[] args) {
        Duke.greet();
        Duke.loadFile();
        Duke.handleCommands();
        Duke.exit();
    }
}
