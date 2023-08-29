import javafx.css.Match;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileNotFoundException;

public class Task {
    private static Pattern markUnmarkCommand = Pattern.compile("^(mark|unmark) (?<taskNumber>\\d*)$");
    private static Pattern deleteCommand = Pattern.compile("^delete (?<taskNumber>\\d*)$");
    private static ArrayList<Task> allTasks = new ArrayList<>();
    private boolean isDone;
    private String name;

    Task(String name) {
        this.name = name.trim();
        this.isDone = false;

        allTasks.add(this);
    }

    Task(String name, boolean isDone) {
        this.name = name.trim();
        this.isDone = isDone;
    }

    public static void saveTasksToFile(String filepath) throws LukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            String inputTxt = "";

            for (Task task : allTasks) {
                inputTxt += task.toSaveStr() + "\n";
            }

            fw.write(inputTxt.trim());
            fw.close();
            Util.displayMessage("Tasks successfully saved into '" + filepath + "'");
        } catch (IOException e) {
            throw new LukeException("Error writing saved tasks into '" + filepath
                    + "'\n\n Save aborted");
        }
    }

    public static void readSavedTasks(String filepath) throws LukeException {
        try {
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while(s.hasNext()) {
                String savedData = s.nextLine();
                String[] taskDetails = Arrays.stream(savedData.split("\\|")).map(String::trim).toArray(String[]::new);

                if (taskDetails.length < 2) {
                    throw new LukeException("Unknown data '" + savedData + "'");
                }

                boolean isDone;
                switch (taskDetails[1]) {
                    case "Done":
                        isDone = true;
                        break;
                    case "Not Done":
                        isDone = false;
                        break;
                    default:
                        throw new LukeException("Task neither 'Done' nor 'Not Done'");
                }

                Task createdTask;
                switch (taskDetails[0]) {
                    case "T":
                        createdTask = Todo.createTodo(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                        break;
                    case "D":
                        createdTask = Deadline.createDeadline(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                        break;
                    case "E":
                        createdTask = Event.createEvent(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                        break;
                    default:
                        throw new LukeException("Unknown Task Type '" + taskDetails[0] +"'");
                }
                allTasks.add(createdTask);
            }
            Util.displayMessage("Saved file successfully loaded from '" + filepath + "'");
        } catch (LukeException e) {
            // Drop all tasks if exception encountered
            allTasks = new ArrayList<>();
            throw new LukeException(
                    "Error reading saved tasks from '" + filepath + "': \n"
                            + e.getMessage() + "\n\n No tasks loaded"
            );
        } catch (FileNotFoundException e) {
            throw new LukeException(
                    "Could not find file '" + filepath + "'\n No tasks loaded"
            );
        }
    }

    public static Task deleteTask(String command) throws LukeException {
        Matcher matcher = deleteCommand.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: delete {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        Task task = allTasks.get(taskIndex);
        allTasks.remove(taskIndex);
        return task;
    }

    public static Task markUnmarkTask(String command) throws LukeException {
        Matcher matcher = markUnmarkCommand.matcher(command);
        if (!matcher.find()) {
            throw new LukeException("Invalid format. Usage: mark {task_number}");
        }

        String taskNumber = matcher.group("taskNumber");
        if (taskNumber == null || taskNumber.isBlank()) {
            throw new LukeException("Task number cannot be empty.");
        }

        int taskIndex = Integer.parseInt(taskNumber) - 1;
        if (taskIndex < 0 || taskIndex >= allTasks.size()) {
            throw new LukeException("Invalid task number: no such task");
        }

        return setTaskIsDone(taskIndex, command.split(" ")[0].equals("mark"));
    }

    public static void listTasks(String command) {
        String list = "";
        for (int i = 1; i <= allTasks.size(); i++) {
            list += i + ". " + allTasks.get(i - 1).toString() + "\n";
        }

        if (list.isBlank()) {
            Util.displayMessage("Congrats! You have no tasks!");
            return;
        }

        Util.displayMessage(list.trim());
    }

    public static Task addTask(String input) throws LukeException {
        Task task;
        switch (input.split(" ")[0]) {
            case "todo":
                task = Todo.createTodo(input);
                break;
            case "deadline":
                task = Deadline.createDeadline(input);
                break;
            case "event":
                task = Event.createEvent(input);
                break;
            default:
                throw new LukeException("Error processing command in addTask: '" + input + "'");
        }

        return task;
    }

    public static Task setTaskIsDone(int taskNumber, boolean isDone) throws LukeException {
        Task task = allTasks.get(taskNumber);
        if (task.isDone == isDone) {
            String status = isDone ? "marked as done" : "marked as unfinished";
            throw new LukeException("Task: '" + task + "' is already " + status);
        }
        task.isDone = isDone;
        return task;
    }

    public String toSaveStr() {
        return (isDone ? "Done" : "Not Done")
                + " | " + name;
    }

    @Override
    public String toString() {
        String doneIndicator = isDone ? "x" : " ";
        return "[" + doneIndicator + "] " + name;
    }
}
