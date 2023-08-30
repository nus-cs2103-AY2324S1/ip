import Task.Task;
import Task.Deadline;
import Task.Event;
import Task.Todo;
import Exception.UnknownCommandException;
import Exception.EmptyDescriptionException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private static final String DATA_PATH = "./data";
    private static final String FILE_PATH = DATA_PATH + "/duke.txt";

    // Save tasks to file
    public static void saveToFile(ArrayList<Task> tasks) {
        try {
            if (!Files.exists(Paths.get(DATA_PATH))) {
                Files.createDirectories(Paths.get(DATA_PATH));
            }

            List<String> lines = new ArrayList<>();
            for (Task task : tasks) {
                lines.add(task.toFileFormat());
            }
            Files.write(Paths.get(FILE_PATH), lines);
        } catch (IOException e) {
            System.out.println("Error while saving tasks to file.");
        }
    }

    // Load tasks from file
    public static ArrayList<Task> loadFromFile() {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (Files.exists(Paths.get(FILE_PATH))) {
                List<String> lines = Files.readAllLines(Paths.get(FILE_PATH));
                for (String line : lines) {
                    tasks.add(Task.fromFileFormat(line));
                }
            }
        } catch (IOException e) {
            System.out.println("Error while loading tasks from file.");
        } catch (Exception e) {  // Handling corrupted data format
            System.out.println("Corrupted data file. Starting with an empty task list.");
            tasks = new ArrayList<>();
        }

        return tasks;
    }

    public static void filterInput(String[] words) throws UnknownCommandException, EmptyDescriptionException {
        // Only allows commands that are listed below
        String command = words[0];
        boolean validCommand2Words = command.equals("todo") || command.equals("deadline") || command.equals("event") ||
                command.equals("mark") || command.equals("unmark") || command.equals("delete");
        boolean validCommand1Word = command.equals("bye") || command.equals("list") || command.equals("help");

        if (!validCommand2Words && !validCommand1Word) {
            throw new UnknownCommandException();
        } else if (validCommand2Words && words.length < 2) {
            throw new EmptyDescriptionException();
        }
    }

    public static void main(String[] args) {
        String logo = ">. <\n";
        String name = "your father";
        String line = "_________________________\n";
        System.out.println(logo +
                line +
                "Hello! I'm " + name + "\n" +
                "What can I do for you?\n" +
                line);

        Scanner scanner = new Scanner(System.in);
        String input;

        ArrayList<Task> tasks = loadFromFile();
        int taskIndex = tasks.size();

        while (true) {
            input = scanner.nextLine().toLowerCase();
            String[] words = input.split(" ", 2); // splits into the command and the rest

            try {
                filterInput(words);
            } catch (UnknownCommandException | EmptyDescriptionException e) {
                System.out.println(line + e.getMessage() + "\n" + line);
                continue;
            }

            if (input.equals("bye")) {
                break;
            } else if (words[0].equals("delete")) {
                int index = Integer.parseInt(words[1]) - 1;
                Task removedTask = tasks.remove(index); // Removes and retrieves the task from the list
                taskIndex--;
                System.out.println(line +
                        "Noted. I've removed this task:\n  " +
                        removedTask +
                        "\nNow you have " +
                        tasks.size() +
                        " tasks in the list.\n" +
                        line);
            } else if (input.equals("help")) {
                System.out.println(line + "\nCommands:\n" +
                        "- To add a todo: 'todo [description]'\n" +
                        "- To add a deadline: 'deadline [description] /by [date in format yyyy-MM-dd]'\n" +
                        "- To add an event: 'event [description] /from [start date in format yyyy-MM-dd] /to " +
                        "[end date in format yyyy-MM-dd]'\n" +
                        line);
            } else if (words[0].equals("todo")) {
                Task newTask = new Todo(words[1]);
                tasks.add(newTask);
                taskIndex++;
                System.out.println(line + "Got it. I've added this task:\n  " +
                        newTask +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" +
                        line);
            } else if (words[0].equals("deadline")) {
                String[] parts = words[1].split(" /by ");
                if (parts.length < 2) {
                    System.out.println(line + "Error: Please use the format 'deadline <task description> " +
                            "/by yyyy-MM-dd'\n" + line);
                    continue;
                }
                try {
                    LocalDate.parse(parts[1]); // This will throw an exception if the date format is invalid
                } catch (DateTimeParseException e) {
                    System.out.println(line + "Error: Please enter the date in the format 'yyyy-MM-dd'.\n" + line);
                    continue;
                }
                Task newTask = new Deadline(parts[0], parts[1]);
                tasks.add(newTask);
                taskIndex++;
                System.out.println(line +
                        "Got it. I've added this task:\n  " +
                        newTask +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" + line);
            } else if (words[0].equals("event")) {
                String[] parts = words[1].split(" /from "); // second part will consist the timings
                if (parts.length < 2) {
                    System.out.println(line + "Error: Please use the format 'event <event description> /from yyyy-MM-dd " +
                            "/to yyyy-MM-dd'\n" + line);
                    continue;
                }
                String[] times = parts[1].split(" /to ");
                if (times.length < 2) {
                    System.out.println(line + "Error: Please use the format 'event <event description> /from yyyy-MM-dd " +
                            "/to yyyy-MM-dd'\n" + line);
                    continue;
                }
                try {
                    LocalDate.parse(times[0]);
                    LocalDate.parse(times[1]);
                } catch (DateTimeParseException e) {
                    System.out.println(line + "Error: Please enter the dates in the format 'yyyy-MM-dd'.\n" + line);
                    continue;
                }
                Task newTask = new Event(parts[0], times[0], times[1]);
                tasks.add(newTask);
                taskIndex++;
                System.out.println(line +
                        "Got it. I've added this task:\n  " +
                        newTask +
                        "\nNow you have " +
                        taskIndex +
                        " tasks in the list.\n" +
                        line);
            } else if (input.equals("list")) {
                System.out.println(line + "\nHere are the tasks in your list:");
                for (int i = 0; i < taskIndex; i++) {
                    System.out.println((i + 1) + "." + tasks.get(i));
                }
                System.out.println(line);
            } else if (words[0].equals("mark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1; // string -> int
                Task taskToMark = tasks.get(index); // Get the task from the list
                taskToMark.markDone(); // Mark the task as done
                tasks.set(index, taskToMark); // Update the task in the list
                System.out.println(line + "\nNice! I've marked this task as done:\n  " +
                        taskToMark +
                        "\n" +
                        line);
            } else if (words[0].equals("unmark") && words.length > 1) {
                int index = Integer.parseInt(words[1]) - 1; // string -> int
                Task taskToUnmark = tasks.get(index); // Get the task from the list
                taskToUnmark.unmarkDone(); // Mark the task as done
                tasks.set(index, taskToUnmark); // Update the task in the list
                System.out.println(line +
                        "\nOK, I've marked this task as not done yet:\n  " +
                        taskToUnmark +
                        "\n" +
                        line);
            }
            saveToFile(tasks);
        }

        System.out.println(line +
                "Bye. Hope to see you again soon!\n" +
                line);
        scanner.close();
    }
}
