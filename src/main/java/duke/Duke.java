package duke;


import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;


public class Duke {
    private static final String FILE_PATH = "./data/duke.txt";
    private static final ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {

        try {
            loadPreviousTasks(FILE_PATH);
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }

        Scanner scanner = new Scanner(System.in);
        Ui ui = new Ui();

        // Greeting message
        ui.displayWelcomeMessage();

        while (true) {
            String echo = scanner.nextLine();

            try {

                if (echo.equals("bye")) {
                    // Farewell message
                    ui.displayByeMessage();
                    try {
                        appendTasksToFile(taskList, FILE_PATH);
                    } catch (IOException e) {
                        System.out.println("Something went wrong: " + e.getMessage());
                    }
                    break;
                } else if (echo.equals("list")) {
                    ui.displayListMessage(taskList);
                } else if (echo.startsWith("todo")) {
                    if (echo.length() <= 5) {
                        throw new DukeException("The description of a todo cannot be empty.");
                    }
                    String description = echo.substring(5).trim();
                    taskList.add(new ToDo(description, 'T'));

                    ui.displayToDoMessage(taskList);

                } else if (echo.startsWith("deadline")) {
                    int byIndex = echo.indexOf("/by");
                    String description = echo.substring(9, byIndex).trim();
                    String by = echo.substring(byIndex + 3).trim();
                    taskList.add(new Deadline(description, by, 'D'));
                    ui.displayDeadlineMessage(taskList);
                } else if (echo.startsWith("event")) {
                    int fromIndex = echo.indexOf("/from");
                    int toIndex = echo.indexOf("/to");
                    String description = echo.substring(6, fromIndex).trim();
                    String from = echo.substring(fromIndex + 5, toIndex).trim();
                    String to = echo.substring(toIndex + 3).trim();
                    taskList.add(new Event(description, from, to, 'E'));
                    ui.displayEventMessage(taskList);
                } else if (echo.startsWith("mark")) {
                    int taskIndex = Integer.parseInt(echo.substring(5).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markAsDone();
                        ui.displayMarkMessage(taskList, taskIndex);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (echo.startsWith("unmark")) {
                    int taskIndex = Integer.parseInt(echo.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        taskList.get(taskIndex).markAsNotDone();
                        ui.displayUnmarkMessage(taskList, taskIndex);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else if (echo.startsWith("delete")) {
                    int taskIndex = Integer.parseInt(echo.substring(7).trim()) - 1;
                    if (taskIndex >= 0 && taskIndex < taskList.size()) {
                        Task removedTask = taskList.remove(taskIndex);
                        ui.displayDeleteMessage(taskList, removedTask);
                    } else {
                        System.out.println("Invalid task index.");
                    }
                } else {
                    throw new DukeException("I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                ui.displayErrorMessage(e);
            }
        }

        scanner.close();

    }

    public static void appendTasksToFile(ArrayList<Task> taskList, String filePath)
            throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for (Task task: taskList) {
            fw.write(task.toFileString() + System.lineSeparator());
        }
        fw.close();
    }

    public static void loadPreviousTasks(String filePath) throws FileNotFoundException {
        File f = new File(filePath);
        Scanner s = new Scanner(f);
        while (s.hasNext()) {
            String line = s.nextLine();
            try {
                Task task = parseTaskFromLine(line);
                taskList.add(task);
            } catch (DukeException e) {
                System.err.println("Error parsing task from file: " + e.getMessage());
            }

        }
        s.close();
    }

    public static Task parseTaskFromLine(String line) throws DukeException {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            throw new DukeException("Invalid data format in the file.");
        }
        char taskType = parts[0].charAt(0);
        boolean isDone = parts[1].equals("1");
        String description = parts[2];

        switch (taskType) {
            case 'T':
                ToDo toDoTask = new ToDo(description, taskType);
                if (isDone) {
                    toDoTask.markAsDone();
                } else {
                    toDoTask.markAsNotDone();
                }
                return toDoTask;

            case 'D':
                if (parts.length < 4) {
                    throw new DukeException("Invalid data format for a deadline task");
                }
                String deadlineBy = parts[3];
                Deadline deadlineTask = new Deadline(description, deadlineBy, taskType);
                if (isDone) {
                    deadlineTask.markAsDone();
                } else {
                    deadlineTask.markAsNotDone();
                }
                return deadlineTask;

            case 'E':
                if (parts.length < 4) {
                    throw new DukeException("Invalid data format for an event task.");
                }
                String[] eventParts = parts[3].split("-");
                String eventFrom = eventParts[0];
                String eventTo = eventParts[1];
                Event eventTask = new Event(description, eventFrom, eventTo, taskType);
                if (isDone) {
                    eventTask.markAsDone();
                } else {
                    eventTask.markAsNotDone();
                }
                return eventTask;

            default:
                throw new DukeException("Invalid task type in data file.");
        }
    }
}
// A-Gradle
// A-JUnit
// A-Jar
// A-JavaDoc
// A-CodingStandard
// Level-9
// Level-10
// A-FullCommitMessage
// A-Assertions
// A-CodeQuality
// A-UserGuide



