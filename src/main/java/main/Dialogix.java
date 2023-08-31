package main;

import exception.DialogixException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dialogix {
    private static final String FILE_PATH = "./data/dialogix.txt"; // Update the relative file path

    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("____________________________________________________________");
        System.out.println(" Hello! I'm Dialogix");
        System.out.println(" What can I do for you?");
        System.out.println("____________________________________________________________");

        List<Task> list = new ArrayList<>();

        boolean continueDialog = true;

        loadTasksFromFile(list);

        while (continueDialog) {
            try {
                System.out.print("User: ");
                String userInput = scanner.nextLine();

                if (userInput.equalsIgnoreCase("list")) {
                    System.out.println("Bot: Here are the tasks in your list:");
                    for (int i = 0; i < list.size(); i++) {
                        System.out.println((i + 1) + ". " + list.get(i).toString());
                    }
                } else if (userInput.startsWith("todo")) {
                    String description = userInput.replaceFirst("todo\\s+", "");
                    list.add(new Todo(description));
                    System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                } else if (userInput.startsWith("deadline")) {
                    String[] parts = userInput.replaceFirst("deadline\\s+", "").split(" /by ");
                    if (parts.length == 2) {
                        LocalDate byDate = LocalDate.parse(parts[1], DATE_TIME_FORMATTER);
                        list.add(new Deadline(parts[0], byDate, null, false));
                        System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                    } else {
                        System.out.println("Bot: Invalid input format for 'deadline'.");
                    }
                } else if (userInput.startsWith("event")) {
                    String[] parts = userInput.replaceFirst("event\\s+", "").split(" /from | /to ");
                    if (parts.length == 3) {
                        LocalDate fromDate = LocalDate.parse(parts[1], DATE_TIME_FORMATTER);
                        LocalDate toDate = LocalDate.parse(parts[2], DATE_TIME_FORMATTER);
                        list.add(new Event(parts[0], fromDate, toDate));
                        System.out.println("Bot: Got it. I've added this task:\n  " + list.get(list.size() - 1).toString());
                    } else {
                        System.out.println("Bot: Invalid input format for 'event'.");
                    }
                } else if (userInput.equalsIgnoreCase("bye")) {
                    saveTasksToFile(list);
                    System.out.println("Bot: Goodbye! Have a great day!");
                    continueDialog = false;
                } else if (userInput.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(userInput.replaceFirst("delete\\s+", "")) - 1;
                    if (taskIndex >= 0 && taskIndex < list.size()) {
                        Task deletedTask = list.remove(taskIndex);
                        System.out.println("Bot: Noted. I've removed this task:\n  " + deletedTask.toString());
                    } else {
                        throw new DialogixException(":( OOPS!!! Task index is out of range.");
                    }
                } else if (userInput.trim().isEmpty()) {
                    throw new DialogixException(":( OOPS!!! The description cannot be empty.");
                } else {
                    throw new DialogixException(":( OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DialogixException e) {
                System.out.println("Bot: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Bot: An error occurred: " + e.getMessage());
            }
        }

        System.out.println("____________________________________________________________");
        scanner.close();
    }

    private static void saveTasksToFile(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            File parentDirectory = file.getParentFile();

            if (!parentDirectory.exists()) {
                parentDirectory.mkdirs();
            }

            FileWriter fileWriter = new FileWriter(file);
            for (Task task : tasks) {
                fileWriter.write(task.toSaveString() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Bot: Error saving tasks to file.");
        }
    }

    private static void loadTasksFromFile(List<Task> tasks) {
        try {
            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
                return;
            }

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Task task = Task.parseSavedTask(line);
                if (task != null) {
                    tasks.add(task);
                }
            }
            scanner.close();
        } catch (IOException e) {
            System.out.println("Bot: Error loading tasks from file.");
        }
    }
}
