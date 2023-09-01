package duke.util;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

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
    private static final String PATH = "./data/tasks.txt";
    private Parser parser = new Parser();

    /**
     * Imports tasks from ./data/tasks.txt.
     */
    public ArrayList<Task> loadTasks() throws DukeException {
        this.file = new File(PATH);

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
                    tasks.add(newTodo);
                    if (taskCompletionStatus.equals("X")) {
                        newTodo.doTask();
                    }
                    break;
                case "D":
                    LocalDate deadline;

                    try {
                        deadline = LocalDate.parse(processedTask[3]);
                    } catch (DateTimeParseException e) {
                        throw new DukeException("Please ensure that the deadline provided is in YYYY-MM-DD format.");
                    }

                    Deadline newDeadline = new Deadline(taskDescription, deadline);
                    tasks.add(newDeadline);
                    if (taskCompletionStatus.equals("X")) {
                        newDeadline.doTask();
                    }
                    break;
                case "E":
                    Event newEvent = new Event(taskDescription, processedTask[3], processedTask[4]);
                    tasks.add(newEvent);
                    if (taskCompletionStatus.equals("X")) {
                        newEvent.doTask();
                    }
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

    /**
     * Saves tasks to ./data/tasks.txt.
     */
    public void saveTasks(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(PATH);

        for (Task currTask : tasks) {
            StringBuilder currEntry = new StringBuilder();

            if (currTask instanceof Todo) {
                Todo todo = (Todo) currTask;
                currEntry.append("T | ");
                currEntry.append(todo.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(todo.getTaskDescription());
            } else if (currTask instanceof Deadline) {
                Deadline deadline = (Deadline) currTask;
                currEntry.append("D | ");
                currEntry.append(deadline.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(deadline.getTaskDescription());
                currEntry.append(" | ");
                currEntry.append(deadline.getDeadline());
            } else if (currTask instanceof Event) {
                Event event = (Event) currTask;
                currEntry.append("E | ");
                currEntry.append(event.getMarkedIcon());
                currEntry.append(" | ");
                currEntry.append(event.getTaskDescription());
                currEntry.append(" | ");
                currEntry.append(event.getStart());
                currEntry.append(" | ");
                currEntry.append(event.getEnd());
            }

            currEntry.append(System.lineSeparator());
            fw.write(currEntry.toString());
        }

        fw.close();
    }
}
