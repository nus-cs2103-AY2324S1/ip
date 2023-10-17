package duke.util;

import duke.exception.DukeException;
import duke.task.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * This class stores and loads previously recorded tasks into a .txt file.
 */
public class Storage {
    private File file;
    private static final String DIR = "./data";
    private static final String PATH = "./data/tasks.txt";
    private Parser parser = new Parser();

    private void loadTodo(ArrayList<Task> tasks, Todo newTodo, String taskCompletionStatus) {
        tasks.add(newTodo);

        if (taskCompletionStatus.equals("X")) {
            newTodo.doTask();
        }
    }

    private void loadDeadline(ArrayList<Task> tasks, Deadline newDeadline, String taskCompletionStatus) {
        tasks.add(newDeadline);

        if (taskCompletionStatus.equals("X")) {
            newDeadline.doTask();
        }
    }

    private void loadEvent(ArrayList<Task> tasks, Event newEvent, String taskCompletionStatus) {
        tasks.add(newEvent);

        if (taskCompletionStatus.equals("X")) {
            newEvent.doTask();
        }
    }

    private void loadRecur(ArrayList<Task> tasks, Recurring newRecur, String taskCompletionStatus) {
        tasks.add(newRecur);

        if (taskCompletionStatus.equals("X")) {
            newRecur.doTask();
        }
    }

    /**
     * Checks for the presence of ./data/tasks.txt.
     */
    private void checkFile() {
        File dataDir = new File(DIR);
        if (!dataDir.exists()) {
            dataDir.mkdir();
        }

        file = new File(PATH);
        if (!file.exists()) {
            try {
                file.createNewFile();
                file.setReadable(true);
                file.setWritable(true);
            } catch (IOException e) {
                System.out.println("Something went wrong when creating a new tasks.txt");
            }
        }
    }

    /**
     * Imports tasks from ./data/tasks.txt.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        this.checkFile();
        ArrayList<Task> tasks = new ArrayList<Task>();

        try {
            Scanner fileScanner = new Scanner(file);

            while (fileScanner.hasNextLine()) {
                String nextTask = fileScanner.nextLine();
                String[] processedTask = this.parser.parseStoredTask(nextTask);
                String typeOfTask = processedTask[0];
                String taskCompletionStatus = processedTask[1];
                String taskDescription = processedTask[2];

                switch (typeOfTask) {
                case "T":
                    Todo newTodo = new Todo(taskDescription);
                    loadTodo(tasks, newTodo, taskCompletionStatus);
                    break;
                case "D":
                    LocalDate deadline;

                    try {
                        deadline = LocalDate.parse(processedTask[3]);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Please ensure that the deadline provided is in YYYY-MM-DD format.");
                    }

                    Deadline newDeadline = new Deadline(taskDescription, deadline);
                    loadDeadline(tasks, newDeadline, taskCompletionStatus);
                    break;
                case "E":
                    Event newEvent = new Event(taskDescription, processedTask[3], processedTask[4]);
                    loadEvent(tasks, newEvent, taskCompletionStatus);
                    break;
                case "R":
                    Recurring newRecur = new Recurring(taskDescription, processedTask[3]);
                    loadRecur(tasks, newRecur, taskCompletionStatus);
                    break;
                default:
                    throw new DukeException("tasks.txt may have been corrupted.");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No file found :(");
        }

        return tasks;
    }

    private StringBuilder createEntryTodo(StringBuilder currEntry, Todo todo) {
        currEntry.append("T | ");
        currEntry.append(todo.getMarkedIcon());
        currEntry.append(" | ");
        currEntry.append(todo.getTaskDescription());

        return currEntry;
    }

    private StringBuilder createEntryDeadline(StringBuilder currEntry, Deadline deadline) {
        currEntry.append("D | ");
        currEntry.append(deadline.getMarkedIcon());
        currEntry.append(" | ");
        currEntry.append(deadline.getTaskDescription());
        currEntry.append(" | ");
        currEntry.append(deadline.getDeadline());

        return currEntry;
    }

    private StringBuilder createEntryEvent(StringBuilder currEntry, Event event) {
        currEntry.append("E | ");
        currEntry.append(event.getMarkedIcon());
        currEntry.append(" | ");
        currEntry.append(event.getTaskDescription());
        currEntry.append(" | ");
        currEntry.append(event.getStart());
        currEntry.append(" | ");
        currEntry.append(event.getEnd());

        return currEntry;
    }

    private StringBuilder createEntryRecur(StringBuilder currEntry, Recurring recur) {
        currEntry.append("R | ");
        currEntry.append(recur.getMarkedIcon());
        currEntry.append(" | ");
        currEntry.append(recur.getTaskDescription());
        currEntry.append(" | ");
        currEntry.append(recur.getRecurrence());

        return currEntry;
    }

    /**
     * Saves tasks to ./data/tasks.txt.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(PATH);

        for (Task currTask : tasks) {
            StringBuilder currEntry = new StringBuilder();

            if (currTask instanceof Todo) {
                Todo todo = (Todo) currTask;
                createEntryTodo(currEntry, todo);
            } else if (currTask instanceof Deadline) {
                Deadline deadline = (Deadline) currTask;
                createEntryDeadline(currEntry, deadline);
            } else if (currTask instanceof Event) {
                Event event = (Event) currTask;
                createEntryEvent(currEntry, event);
            } else if (currTask instanceof Recurring) {
                Recurring recur = (Recurring) currTask;
                createEntryRecur(currEntry, recur);
            }

            currEntry.append(System.lineSeparator());
            fw.write(currEntry.toString());
        }

        fw.close();
    }
}
