import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.FileWriter;
import java.io.BufferedWriter;
import java.io.IOException;

public class Duke {


    public static void main(String[] args) {
        String name = "Johnnythesnake";
        System.out.println("Hello I'm " + name + "\n" + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        String filename = "tasks.txt";
        // Create a File object with the filename
        File file = new File(filename);

        ArrayList<Tasks> tasksList = new ArrayList<>();
        if (file.exists()) {
            tasksList = TaskReader.readTasksFromFile(filename);
            System.out.println(tasksList);
        }
        while (true) {
            System.out.print("Enter a command: ");
            String command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) { // bye exits the code
                Exit exit = new Exit();
                System.out.println(exit.exitMessage());
                break;
            } else if (command.equalsIgnoreCase("list")) { //list shows the task list
                System.out.println("Here are the tasks in your list: ");
                if (!tasksList.isEmpty()) {
                    for (int i = 1; i <= tasksList.size(); i++) {
                        System.out.println(i + "." + tasksList.get(i - 1));
                    }
                }
            } else if (command.startsWith("unmark")) { // unmark the task in question
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber < tasksList.size()) {
                    Tasks task = tasksList.get(taskNumber);
                    task.setMarked(false);
                    tasksList.set(taskNumber, task);
                    System.out.println("OK, I've marked this task as not done yet:\n" + "  " + tasksList.get(taskNumber));
                }
            } else if (command.startsWith("mark")) { // mark the task in question
                int taskNumber = Integer.parseInt(command.substring(5)) - 1;
                if (taskNumber < tasksList.size()) {
                    Tasks task = tasksList.get(taskNumber);
                    task.setMarked(true);
                    tasksList.set(taskNumber, task);
                    System.out.println("Nice! I've marked this task as done:\n" + "  " + tasksList.get(taskNumber));
                }


            } else if (command.startsWith("todo")) {
                String description = command.substring(4).trim(); // Trim any leading/trailing spaces

                try {
                    if (description.isEmpty()) {
                        throw new EmptyTodoException();
                    }

                    Todo todo = new Todo(description, false);
                    tasksList.add(todo);

                    System.out.println("Got it. I've added this task:");
                    System.out.println("  " + todo);
                    System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                } catch (EmptyTodoException e) {
                    System.out.println(e.getMessage());
                }
            } else if (command.startsWith("deadline")) {
                // Split the input
                String descriptionDeadline = command.substring(8).trim(); // Remove "deadline" and leading spaces
                if (descriptionDeadline.isEmpty()) {
                    try {
                        throw new EmptyDeadlineException();
                    } catch (EmptyDeadlineException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Find the index of the deadline separator "/"
                    int separatorIndex = descriptionDeadline.indexOf('/');

                    if (separatorIndex != -1) { // Ensure the separator exists in the input
                        // Extract the task description and deadline
                        String description = descriptionDeadline.substring(0, separatorIndex).trim();
                        String deadline = descriptionDeadline.substring(separatorIndex + 4).trim();

                        // Create a new Deadline object
                        Deadline deadlineTask = new Deadline(description, false, deadline);
                        tasksList.add(deadlineTask);

                        System.out.println("Got it. I've added this deadline:");
                        System.out.println("  " + deadlineTask);
                        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    } else {
                        System.out.println("Invalid input format for deadline command.");
                    }
                }
            } else if (command.startsWith("event")) {
                // split the input
                String descriptionStartEndTime = command.substring(5).trim(); // Remove "event" and leading spaces
                if (descriptionStartEndTime.isEmpty()) {
                    try {
                        throw new EmptyEventException();
                    } catch (EmptyEventException e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    // Find the indices of the time separators
                    int fromIndex = descriptionStartEndTime.indexOf("/from");
                    int toIndex = descriptionStartEndTime.indexOf("/to");

                    if (fromIndex != -1 && toIndex != -1) {
                        // Extract the task description, startTime, and endTime
                        String description = descriptionStartEndTime.substring(0, fromIndex).trim();
                        String startTime = descriptionStartEndTime.substring(fromIndex + 5, toIndex).trim();
                        String endTime = descriptionStartEndTime.substring(toIndex + 3).trim();

                        // Create a new Event object
                        Event eventTask = new Event(description, false, startTime, endTime);
                        tasksList.add(eventTask);

                        // Print confirmation message
                        System.out.println("Got it. I've added this task:");
                        System.out.println("  " + eventTask);
                        System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                    } else {
                        System.out.println("Invalid input format for event command.");
                    }
                }
            } else if (command.startsWith("delete")) {
                int taskNumber = Integer.parseInt(command.substring(7)) - 1;
                if (taskNumber < tasksList.size()) {
                    Tasks task = tasksList.get(taskNumber);
                    tasksList.remove(taskNumber);

                    System.out.println("Noted. I've removed this task: \n" + "  " + task);
                    System.out.println("Now you have " + tasksList.size() + " tasks in the list.");
                }


            } else {
                try {
                    throw new UnknownInputException();
                } catch (UnknownInputException e) {
                    System.out.println(e.getMessage());
                }
            }

        }
        TaskWriter.writeTasksToFile(tasksList, "tasks.txt");
    }
}

