import java.util.EnumMap;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Duke {
    private static int count = 0;
    private static ArrayList<Task> taskList = new ArrayList<>(); //universal task list in memory
    public static void saveToFile() {
        String home = "C:/Users/user/CS2103Tip/src/main";

        //small issue maybe w my device: data/tasks.txt not being created when i run this
        try {
            java.nio.file.Path folderPath = java.nio.file.Paths.get(home, "data");
            //System.out.println("Folder Path: " + folderPath);
            boolean folderExists = java.nio.file.Files.exists(folderPath);
            if (!folderExists) {
                System.out.println("Hol up, folder doesn't exist. Creating a folder rnrn");
                try {
                    Files.createDirectories(folderPath);
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the 'data' folder: " + e.getMessage());
                }
            }
            java.nio.file.Path filePath = java.nio.file.Paths.get(home, "data", "tasks.txt");
            //System.out.println("File Path: " + filePath);
            boolean fileExists = java.nio.file.Files.exists(filePath);
            if (!fileExists) {
                System.out.println("Hol up, file doesn't exist. Creating a file rnrn");
                try {
                    Files.createFile(filePath);
                } catch (IOException e) {
                    System.out.println("An error occurred while creating the 'data' folder: " + e.getMessage());
                }
            }
            File file = filePath.toFile();
            FileWriter writer = new FileWriter(file, true);
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error! " +e.getMessage());
        }
    }



    public static void main(String[] args) throws DukeException {
        String logo = " __          _        \n"
                + "| |     _   _| | _____ \n"
                + "| |    | | | | / / _ \\\n"
                + "| |___ | |_| |   <  __/\n"
                + "|____/ \\__,__|_|\\_\\___|\n";
        System.out.println("Hello I'm\n" + logo);
        System.out.println("What can I do for you?\n");
        Scanner scanner = new Scanner(System.in);
        String input;



        while (true) {
            input = scanner.nextLine();

            if (input.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            if (input.equals("list")) {
                int id = 1;
                System.out.println("Here are the tasks in your list:");
                for (Task task : taskList) {
                    System.out.println(String.valueOf(id) + ". " + task);
                    id++;
                }
                continue;
            }
            try {

                //for identifying first word keyword: mark/ unmark, todo, deadline, event
                String[] parts = input.split(" ");
                String keyword = parts[0]; //first word is command
                String restOfSentence = input.substring(keyword.length()).trim();
                String[] descr = restOfSentence.split("/"); //you get 0: taskName, 1: deadline/start, 2: end
                String taskName = descr[0];

                if (keyword.equals("delete")) {
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                    }
                    int taskID = Integer.parseInt(restOfSentence) - 1;
                    Task taskChanged = taskList.get(taskID);
                    taskList.remove(taskID);
                    count--;
                    saveToFile();
                    System.out.println("Noted. I've removed this task:");
                    System.out.println(taskChanged);
                    System.out.println("Now you have " + count + " tasks in the list.");
                } else if (keyword.equals("todo")) {
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! The description of a task cannot be empty.");
                    }
                    //new todo
                    Task newTask = new ToDo(taskName);
                    taskList.add(newTask);
                    count++;
                    saveToFile();
                    System.out.println("Got it. I've added this todo:");
                    System.out.println("  " + newTask);
                    System.out.println("Now you have " + count + " tasks in the list.");
                } else if (keyword.equals("deadline")) {
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("Hey I'm gonna need the deadline description!");
                    }
                    //new deadline
                    if (descr.length > 1) {
                        Task newTask = new Deadline(restOfSentence); //breakdown of description abstracted into deadline.java
                        taskList.add(newTask);
                        count++;
                        saveToFile();
                        System.out.println("Got it. I've added this deadline:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + count + " tasks in the list.");
                    }
                } else if (keyword.equals("event")) {
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("Hey I'm gonna need the event description!");
                    }
                    if (descr.length < 3) {
                        throw new ArrayIndexOutOfBoundsException();
                    }
                    //new event
                    //if (descr.length > 1) {
                        Task newTask = new Event(restOfSentence);
                        taskList.add(newTask);
                        count++;
                        saveToFile();
                        System.out.println("Got it. I've added this event:");
                        System.out.println("  " + newTask);
                        System.out.println("Now you have " + count + " tasks in the list.");
                    //}
                } else if (keyword.equals("mark")) {
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! Which do you want to mark?");
                    }
                    int taskID = Integer.parseInt(restOfSentence) - 1;
                    Task taskChanged = taskList.get(taskID);
                    taskChanged.markDone();
                    saveToFile();
                    System.out.println("Nice! I've marked this task as done:");
                    System.out.println(taskChanged);
                } else if (keyword.equals("unmark")){
                    if (restOfSentence.isEmpty()) {
                        throw new DukeException("☹ OOPS!!! Which do you want to unmark?");
                    }
                    int taskID = Integer.parseInt(restOfSentence) - 1;
                    Task taskChanged = taskList.get(taskID);
                    taskChanged.markUndone();
                    saveToFile();
                    System.out.println("OK, I've marked this task as not done yet:");
                    System.out.println(taskChanged);
                } else { //covers unknown words and empty input
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                System.out.println("☹ OOPS!!! We need more deets!");
            }
        }
        scanner.close();
    }
}
