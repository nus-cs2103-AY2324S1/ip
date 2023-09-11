package duke;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.task.DeadlineTask;
import duke.task.EventTask;
import duke.task.Task;
import duke.task.ToDoTask;

/**
 * Represents a storage class that deals with loading tasks from the file and saving tasks in the file.
 */
public class Storage {
    private final String filePath;

    /**
     * Constructor for Storage.
     *
     * @param filePath path to duke.txt.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves list to disk in a formatted version.
     *
     * @param taskList list of tasks.
     */
    public void saveListToDisk(ArrayList<Task> taskList) {
        assert taskList != null : "taskList should not be null";
        createFile(this.filePath);

        try {
            FileWriter fw = new FileWriter(filePath);
            System.out.println("Saving list to disk...");
            for (Task task : taskList) {
                fw.write(task.getFileDescriptor() + "\n");
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }


    /**
     * Creates a file if it does not exist.
     *
     * @param path of the file to be created.
     */
    private void createFile(String path) {
        File file = new File(path);
        // checks if data folder and duke.txt exists, else create file
        if (!file.exists()) {
            try {
                file.getParentFile().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Something went wrong: " + e.getMessage());
            }
        }
    }

    /**
     * Loads list from disk and then converts it to tasks with their details.
     *
     * @return list of tasks.
     * @throws DukeException if error loading a file.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<>();

        File f = new File(filePath);
        Scanner s = new Scanner(filePath);

        try {
            s = new Scanner(f);
        } catch (FileNotFoundException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

        while (s.hasNext()) {
            String line = s.nextLine();
            String[] details = line.split("\\|");
            String taskType = details[details.length - 1].trim();
            if (details.length < 2) {
                throw new DukeException("☹ OOPS!!! I'm sorry, but I can't load the data :-(");
            }
            boolean isDone = details[0].trim().equals("true");
            String taskDescription = details[1].trim();
            Task task;

            switch (taskType) {
            case "TODO":
                task = new ToDoTask(taskDescription);
                break;
            case "DEADLINE":
                assert details.length >= 3 : "Deadline should have 3 details";
                String by = details[2].trim();
                task = createDeadlineTask(taskDescription, by);
                break;
            case "EVENT":
                assert details.length >= 4 : "Event should have 4 details";
                String from = details[2].trim();
                String to = details[3].trim();

                task = createEventTask(taskDescription, from, to);
                break;
            default:
                throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what the loaded command is :-(");
            }
            if (isDone) {
                task.mark();
            }
            taskList.add(task);
        }

        return taskList;
    }

    /**
     * Creates a DeadlineTask object based on the description and by string.
     *
     * @param taskDescription of the task.
     * @param by              deadline of the task.
     * @return DeadlineTask object.
     * @throws DukeException if the date is not in the format of yyyy-mm-dd.
     */
    private DeadlineTask createDeadlineTask(String taskDescription, String by) throws DukeException {
        LocalDate byDate;

        try {
            byDate = LocalDate.parse(by);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date in the format: yyyy-mm-dd");
        }

        return new DeadlineTask(taskDescription, byDate);
    }

    /**
     * Creates an EventTask object based on the description, from and to string.
     *
     * @param taskDescription of the task.
     * @param from            start date of the task.
     * @param to              end date of the task.
     * @return EventTask object.
     * @throws DukeException if the date is not in the format of yyyy-mm-dd.
     */
    private EventTask createEventTask(String taskDescription, String from, String to) throws DukeException {
        LocalDate fromDate;
        LocalDate toDate;

        try {
            // convert date string in the format of yyyy-mm-dd to LocalDate object
            fromDate = LocalDate.parse(from);
            // convert LocalDate object to MMM dd yyyy format
            toDate = LocalDate.parse(to);
        } catch (Exception e) {
            throw new DukeException("Please enter a valid date in the format: yyyy-mm-dd");
        }

        return new EventTask(taskDescription, fromDate, toDate);
    }
}
