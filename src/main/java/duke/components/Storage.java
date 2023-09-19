package duke.components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exceptions.DataCorruptedException;
import duke.exceptions.DukeException;
import duke.exceptions.InvalidStartEndException;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.tasks.ToDo;

/**
 * Handles interactions between task list and hard drive.
 */
public class Storage {
    private String path;

    /**
     * Constructor for Storage class.
     *
     * @param path path of file where data is stored.
     */
    public Storage(String path) {
        this.path = path;
    }

    /**
     * Creates a file with the path .data/duke.txt (hardcoded value).
     *
     * @return a File object to store tasks.
     */
    public File createDataFile() {
        File dataFile = new File(this.path);
        try {
            Path dirPath = Paths.get("./data/");
            if (!Files.exists(dirPath)) {
                Files.createDirectory(dirPath);
            }
            if (!dataFile.exists()) {
                dataFile.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("Unable to create file!!");
        }
        assert dataFile.exists() : "Data file does not exist";
        return dataFile;
    }

    /**
     * Converts the task text in the data file to a Task.
     *
     * @param taskDetails details of the task.
     * @return the Task equivalent.
     * @throws DataCorruptedException   when text is not of the task format.
     * @throws InvalidStartEndException when start greater than end time in event.
     */
    public Task convertToTask(String[] taskDetails) throws DataCorruptedException,
            InvalidStartEndException {
        String type = taskDetails[0];
        int intStatus = Integer.parseInt(taskDetails[1]);
        Status status = Status.convertToStatus(intStatus);
        String desc = taskDetails[2];

        switch (type) {
        case "T":
            return new ToDo(status, desc);

        case "D":
            LocalDateTime date = Parser.convertToDateTime(taskDetails[3]);
            return new Deadline(status, desc, date);

        case "E":
            LocalDateTime start = Parser.convertToDateTime(taskDetails[3]);
            LocalDateTime end = Parser.convertToDateTime(taskDetails[4]);
            return new Event(status, desc, start, end);

        default:
            throw new DataCorruptedException();
        }
    }

    /**
     * Retrieves the tasks stored in the data file, and returns them in an arraylist.
     *
     * @return an ArrayList of Task objects.
     */
    public ArrayList<Task> loadTasks() {
        File dataFile = new File(this.path);
        if (!dataFile.exists()) {
            dataFile = createDataFile();
        }
        ArrayList<Task> list = new ArrayList<>();

        try {
            Scanner sc = new Scanner(dataFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskDetails = task.split(" " + "\\|" + " ");
                Task toAdd = convertToTask(taskDetails);
                list.add(toAdd);
            }
            sc.close();
        } catch (IOException | DukeException e) {
            System.out.println(e.getMessage());
        }
        return list;
    }

    /**
     * Writes the tasks in the given list to the data file.
     * This overwrites the existing data in the file.
     *
     * @param list list of Task objects.
     */
    public void updateFile(ArrayList<Task> list) {
        try {
            //check if file exists, else create
            File dataFile = new File("./data/duke.txt");
            if (!dataFile.exists()) {
                dataFile = createDataFile();
            }

            //create a FileWriter object to write to file. Note that this overwrites the existing data!
            FileWriter file = new FileWriter("./data/duke.txt");
            BufferedWriter writer = new BufferedWriter(file);

            for (int i = 0; i < list.size(); i++) {
                Task task = list.get(i);
                String taskStr = task.convertTask();
                writer.write(taskStr);
                writer.newLine();
                writer.flush();
            }

            writer.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
