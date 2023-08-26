import com.sun.source.util.TaskListener;

import java.lang.reflect.Array;
import java.util.Scanner;
import java.util.ArrayList;
public class Duke {
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

    public static void addTask(TaskType taskType, String userInput) throws DukeException{
        if (taskType == TaskType.TODO) {
            String description = userInput.substring(5).trim();
            if (description.isEmpty()) {
                throw new DukeException("The description of a todo cannot be empty.");
            }
            Duke.taskList.add(new Todo(description));
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
                    Duke.taskList.add(new Event(description, from, end));
                    Duke.number++;
                } else {
                    wrongInput = true;
                }
            } else {
                wrongInput = true;
            }
            if (wrongInput == true) {
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
                Duke.taskList.add(new Deadline(description, by));
                Duke.number++;

            } else {
                wrongInput = true;
            }
            if (wrongInput == true) {
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

    public static void main(String[] args) throws DukeException {
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
                addTask(TaskType.TODO, userInput);
            } else if (userInput.startsWith("event")) {
                addTask(TaskType.EVENT, userInput);
            } else if (userInput.startsWith("deadline")) {
                addTask(TaskType.DEADLINE, userInput);
            } else if (userInput.startsWith("mark")) {
                toggleMark(MarkStatus.MARK, Duke.taskList, userInput);
            } else if (userInput.startsWith("unmark")) {
                toggleMark(MarkStatus.UNMARK, Duke.taskList, userInput);
            } else if (userInput.startsWith("delete")) {
                String[] parts = userInput.split(" ");
                if (parts.length == 2) {
                    int taskIndex = Integer.parseInt(parts[1]) - 1;
                    deleteTask(taskIndex);
                } else {
                    System.out.println("Please specify the task number to delete.");
                }
            } else {
                throw new DukeException("Please enter a valid command!");
            }
        }
        printGoodbye();
    }
}

