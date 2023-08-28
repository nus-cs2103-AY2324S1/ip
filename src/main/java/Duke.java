import com.sun.source.util.TaskListener;

import java.lang.reflect.Array;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Duke {
    private static final String FILE_PATH = "./Data.txt";
    private static ArrayList<Task> taskList = new ArrayList<>();
    private static int number = 0;
    private static boolean activated = true;
    private static final String line = "____________________________________________________________";
    private enum MarkStatus {
        MARK, UNMARK
    }
    private enum TaskType {
        TODO, EVENT, DEADLINE
    }

    public static void loadTasksFromFile() {
        try {
            File file = new File(FILE_PATH);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                char type = line.charAt(1);
                char mark = line.charAt(4);

                if (type == 'T') {
                    String description = line.substring(6).trim();
                    if (mark == 'X') {
                        Duke.taskList.add(new Todo(description, "marked" ));
                    } else {
                        Duke.taskList.add(new Todo(description, "unmarked"));
                    }
                    Duke.number++;
                } else if ( type == 'D') {
                    int byIndex = line.indexOf("(by: ");
                    String description = line.substring(7, byIndex).trim(); // 7 is the length of "[D][ ] "
                    String dueDate = line.substring(byIndex + 5, line.length() - 1).trim();

                    if (mark == 'X') {
                        Duke.taskList.add(new Deadline(description, dueDate, "marked"));
                    } else {
                        Duke.taskList.add(new Deadline(description, dueDate, "unmarked"));
                    }
                    Duke.number++;
                } else if ( type == 'E') {
                    String[] parts = line.split("\\(from: | to ");
                    String description = parts[0].trim().substring(7
                    );
                    String startTime = parts[1].trim();
                    String endTime = parts[2].replace(")", "").trim();

                    if (mark == 'X') {
                        Duke.taskList.add(new Event(description, startTime, endTime, "marked"));
                    } else {
                        Duke.taskList.add(new Event(description, startTime, endTime, "unmarked"));
                    }
                    Duke.number++;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data file not found: " + e.getMessage());
        }
    }

    public static void saveTasksToFile() {
        try {
            FileWriter writer = new FileWriter(FILE_PATH);

            for (Task task : taskList) {
                // Convert Task objects to strings and write them to the file
                // For example, if your Task class has a toString method that returns
                // a string representation of the task, you can use task.toString().
                writer.write(task.toString() + System.lineSeparator());
            }

            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file: " + e.getMessage());
        }
    }

    public static void printHello() {
        System.out.println(line);
        System.out.println("Hello! I am TaskMaster!");
        System.out.println("What can I do for you today?");
        System.out.println(line);
    }
    public static void printGoodbye() {
        System.out.println(line);
        System.out.println("Bye! Hope to see you again!");
        System.out.println(line);
    }

    public static void printList(ArrayList<Task> list) {
        System.out.println(line);
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < Duke.number; i ++ ) {

            System.out.println("Task " + (i + 1)+ ": " + list.get(i));
        }
        System.out.println(line);
    }

    public static void toggleMark(MarkStatus mark, ArrayList<Task> taskList, String userInput) {
        String[] parts = userInput.split(" ");
        if (parts.length == 2) {
            int taskIndex = Integer.parseInt(parts[1]) - 1;
            if (taskIndex >= 0 && taskIndex < Duke.number) {
                if (mark == MarkStatus.UNMARK) {
                    taskList.get(taskIndex).markAsNotDone();
                    System.out.println(line);
                    System.out.println("OK, I have marked this as undone:");
                    System.out.println("  " + taskList.get(taskIndex));
                    System.out.println(line);
                } else if (mark == MarkStatus.MARK){
                    taskList.get(taskIndex).markAsDone();
                    System.out.println(line);
                    System.out.println("Good job! I have marked this task as completed:");
                    System.out.println("  " + taskList.get(taskIndex));
                    System.out.println(line);
                }
            } else {
                System.out.println("Sorry! you have input an invalid task!");
            }
        }
    }

    public static void deleteTask(int taskIndex) {
        if (taskIndex >= 0 && taskIndex < Duke.taskList.size()) {
            Task removedTask = Duke.taskList.remove(taskIndex);
            Duke.number--;
            System.out.println(line);
            System.out.println("Noted. I've removed this task:");
            System.out.println("  " + removedTask);
            System.out.println("Now you have " + Duke.taskList.size() + " tasks in the list.");
            System.out.println(line);
        } else {
            System.out.println("Sorry! You have entered an invalid task number.");
        }
    }

    public static void addTask(TaskType taskType, String userInput, String marked) throws DukeException{
        if (taskType == TaskType.TODO) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Duke.taskList.add(new Todo(description, marked));
            Duke.number++;
            System.out.println(line);
            System.out.println("Got it. I've added this to-do task:");
            System.out.println("  " + Duke.taskList.get(Duke.taskList.size() - 1));
            System.out.println("Now you have " + taskList.size() + " tasks in the list.");
            System.out.println(line);
        } else if (taskType == TaskType.EVENT) {
            boolean wrongInput = false;
            String input = userInput.substring(5);
            String[] parts = input.split("/from");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String[] timeParts = parts[1].split("/to");
                if (timeParts.length == 2) {
                    String from = timeParts[0].trim();
                    String end = timeParts[1].trim();
                    Duke.taskList.add(new Event(description, from, end, marked));
                    Duke.number++;
                } else {
                    wrongInput = true;
                }
            } else {
                wrongInput = true;
            }
            if (wrongInput) {
                System.out.println("Please input a valid task");
            } else {
                System.out.println(line);
                System.out.println("Got it. I've added this event:");
                System.out.println("  " + Duke.taskList.get(Duke.taskList.size() - 1));
                System.out.println("Now you have " + Duke.taskList.size() + " tasks in the list.");
                System.out.println(line);
            }
        } else if (taskType == TaskType.DEADLINE) {
            boolean wrongInput = false;
            String input = userInput.substring(8);
            String[] parts = input.split("/by");
            if (parts.length == 2) {
                String description = parts[0].trim();
                String by = parts[1].trim();
                Duke.taskList.add(new Deadline(description, by, marked));
                Duke.number++;

            } else {
                wrongInput = true;
            }
            if (wrongInput) {
                System.out.println("Please input a valid task");
            } else {
                System.out.println(line);
                System.out.println("Got it. I've added this deadline:");
                System.out.println("  " + Duke.taskList.get(Duke.taskList.size() - 1));
                System.out.println("Now you have " + taskList.size() + " tasks in the list.");
                System.out.println(line);
            }
        }
    }

    public static void printTasksByDate(String date) {
        LocalDate dueDate = null;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate parsedStartDate = LocalDate.parse(date, formatter);
            dueDate = parsedStartDate;
        } catch (java.time.format.DateTimeParseException e) {
            System.out.println("Please input a valid date format: yyyy-mm-dd!");
        }
        System.out.println("Tasks occurring on " + dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ":");

        int count = 1;

        for (Task task : taskList) {
            String dueDateString = dueDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                String deadlineString = deadline.getStringDate();
                LocalDate deadlineDate = deadline.getLocalDate();
                if ((deadlineDate != null && deadlineDate.equals(dueDate)) || (deadlineString != null && deadlineString.equals(dueDateString))) {
                    System.out.println(count + ": " + task.toString());
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                String startString = event.getStartString();
                LocalDate startDate = event.getStartDate();
                if  ((startDate != null && startDate.equals(dueDate)) || (startString != null && startString.equals(dueDateString))) {
                    System.out.println(count + ": " + task.toString());
                }
            }
            count++;
        }
    }

    public static void main(String[] args) throws DukeException {
        loadTasksFromFile();
        Scanner scanner = new Scanner(System.in);
        printHello();

        while (Duke.activated) {
            String userInput = scanner.nextLine();

            if (userInput.equalsIgnoreCase("bye")) {
                Duke.activated = false;
                break;
            } else if (userInput.equalsIgnoreCase("list")) {
                printList(Duke.taskList);
            } else if (userInput.startsWith("todo")) {
                addTask(TaskType.TODO, userInput, "unmarked");
                saveTasksToFile();
            } else if (userInput.startsWith("event")) {
                addTask(TaskType.EVENT, userInput, "unmarked");
                saveTasksToFile();
            } else if (userInput.startsWith("deadline")) {
                addTask(TaskType.DEADLINE, userInput, "unmarked");
                saveTasksToFile();
            } else if (userInput.startsWith("mark")) {
                toggleMark(MarkStatus.MARK, Duke.taskList, userInput);
                saveTasksToFile();
            } else if (userInput.startsWith("unmark")) {
                toggleMark(MarkStatus.UNMARK, Duke.taskList, userInput);
                saveTasksToFile();
            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    deleteTask(taskIndex);
                } else {
                    System.out.println("Please specify the task number to delete.");
                }
                saveTasksToFile();
            } else if (userInput.startsWith("due")) {
                String date = userInput.substring(4).trim();
                printTasksByDate(date);
            } else {
                throw new DukeException("Please enter a valid command!");
            }
        }
        printGoodbye();
    }
}

