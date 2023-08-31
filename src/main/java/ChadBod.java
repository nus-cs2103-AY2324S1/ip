package main.java;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.time.LocalDateTime;

public class ChadBod {
    private static final String FILE_PATH = "./data/tasks.txt";
    private static void saveTasks(ArrayList<Task> tasks) {
        try {
            File file = new File(FILE_PATH);
            if (!file.exists()) {
                // Create necessary directories and files
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            for (Task task : tasks) {
                writer.write(task.toFileString());
                writer.newLine();
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving tasks to file.");
        } catch (SecurityException e) {
            System.out.println("Error writing to file. Check permissions.");
        }
    }

    private static ArrayList<Task> loadTasks() {
        ArrayList<Task> tasks = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Task task = Task.fromString(line);
                if (task != null) {
                    tasks.add(task);
                } else {
                    System.out.println("File content invalid. Skipping this task.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("Task storage file not found. Starting with an empty task list.");
        } catch (IOException e) {
            System.out.println("Error parsing task storage file. Starting with an empty task list.");
        }

        return tasks;
    }

    public static void main(String[] args) {
        ArrayList<Task> tasks = loadTasks();
        System.out.println("Hello! I'm ChadBod.");
        System.out.println("What can I do for you?");
        Scanner sc = new Scanner(System.in);
        boolean shouldExit = false;

        while (!shouldExit) {
            String input = sc.nextLine();
            // may need try catch here
            String[] commandArray = input.split(" ", 2);
            Command command = null;
            try {
                if (commandArray.length > 0) {
                    String commandString = commandArray[0];
                    for (Command cmd : Command.values()) {
                        if (cmd.getValue().equals(commandString)) {
                            command = cmd;
                            break;
                        }
                    }
                }
                if (command == null) {
                    throw new InvalidInputException();
                }
                switch (command) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        shouldExit = true;
                        break;
                    case LIST:
                        if (tasks.isEmpty()) {
                            System.out.println("There are no tasks in your list!");
                        } else {
                            System.out.println("Here are the tasks in your list:");
                            for (int i = 0; i < tasks.size(); i ++) {
                                System.out.printf("%d.%s\n", i + 1, tasks.get(i));
                            }
                        }
                        break;
                    case MARK:
                        int markTaskNumber = Integer.parseInt(commandArray[1]);
                        if (markTaskNumber < 1 || markTaskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task markedTask = tasks.get(markTaskNumber - 1);
                        markedTask.markDone();
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.printf("%s\n", markedTask);
                        saveTasks(tasks);
                        break;
                    case UNMARK:
                        int unmarkTaskNumber = Integer.parseInt(commandArray[1]);
                        if (unmarkTaskNumber < 1 || unmarkTaskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task unmarkedTask = tasks.get(unmarkTaskNumber - 1);
                        unmarkedTask.markUndone();
                        System.out.println("OK, I've marked this task as not done yet:");
                        System.out.printf("%s\n", unmarkedTask);
                        saveTasks(tasks);
                        break;
                    case TODO:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of todo cannot be empty.");
                        }
                        Todo newTodo = new Todo(commandArray[1]);
                        tasks.add(newTodo);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newTodo);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        saveTasks(tasks);
                        break;
                    case DEADLINE:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of deadline cannot be empty.");
                        }
                        String[] deadlineDetails = commandArray[1].split(" /by ", 2);
                        if (deadlineDetails.length < 2 || deadlineDetails[1].isEmpty()) {
                            throw new InvalidTaskException("Deadline due date cannot be empty.");
                        }
                        LocalDateTime byDate;
                        try {
                            byDate = LocalDateTime.parse(deadlineDetails[1]);
                        } catch (DateTimeParseException e) {
                            throw new InvalidTaskException("Deadline due date/time not in ISO format. (e.g. 2007-12-03T10:15:30)");
                        }
                        Deadline newDeadline = new Deadline(deadlineDetails[0], byDate);
                        tasks.add(newDeadline);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newDeadline);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        saveTasks(tasks);
                        break;
                    case EVENT:
                        if (commandArray.length < 2 || commandArray[1].isEmpty()) {
                            throw new InvalidTaskException("Description of event cannot be empty.");
                        }
                        String[] eventDetails = commandArray[1].split(" /from ", 2);
                        if (eventDetails.length < 2 || eventDetails[1].isEmpty()) {
                            throw new InvalidTaskException("Event timings cannot be empty.");
                        }
                        String[] eventTimings = eventDetails[1].split(" /to ", 2);
                        if (eventTimings.length < 2 || eventTimings[1].isEmpty()) {
                            throw new InvalidTaskException("Event from and to timings cannot be empty.");
                        }
                        LocalDateTime fromDate, toDate;
                        try {
                            fromDate = LocalDateTime.parse(eventTimings[0]);
                            toDate = LocalDateTime.parse(eventTimings[1]);
                        } catch (DateTimeParseException e) {
                            throw new InvalidTaskException("Deadline due date/time not in ISO format. (e.g. 2007-12-03T10:15:30)");
                        }
                        Event newEvent = new Event(eventDetails[0], fromDate, toDate);
                        tasks.add(newEvent);
                        System.out.println("Got it. I've added this task:");
                        System.out.println(newEvent);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        saveTasks(tasks);
                        break;
                    case DELETE:
                        int taskNumber = Integer.parseInt(commandArray[1]);
                        if (taskNumber < 1 || taskNumber > tasks.size()) {
                            throw new TaskIndexOutOfBoundsException();
                        }
                        Task deletedTask = tasks.remove(taskNumber - 1);
                        System.out.println("Noted. I've removed this task:");
                        System.out.printf("%s\n", deletedTask);
                        System.out.printf("Now you have %d tasks in the list.\n", tasks.size());
                        saveTasks(tasks);
                        break;
                    default:
                        throw new InvalidInputException();
                }
            } catch (NumberFormatException e) {
                System.out.println("☹ OOPS!!! Invalid task index.");
            } catch (ChadBodException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
