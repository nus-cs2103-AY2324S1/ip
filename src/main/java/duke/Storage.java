package duke;

import duke.exceptions.InvalidStartEndException;
import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles interactions between task list and hard drive.
 */
public class Storage {
    private String path;
    private Ui ui;

    public Storage(String path, Ui ui) {
        this.path = path;
        this.ui = ui;
    }

    /**
     * Converts a string of the format YYYY-MM-dd HH:mm to a LocalDateTime object.
     *
     * @param str a datetime string.
     * @return the corresponding LocalDateTime object.
     * @throws DateTimeParseException if str is not of the correct format.
     */
    public static LocalDateTime convertToDateTime(String str) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
        return dateTime;
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
        return dataFile;
    }

    /**
     * Retrieves the tasks stored in the ./data/duke.txt file.
     * Converts each task string into their corresponding Task object.
     * Tasks are then added to an ArrayList.
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

                if (!task.isBlank()) {
                    // | is a special symbol
                    String[] taskDetails = task.split(" " + "\\|" + " ");
                    String type = taskDetails[0];
                    int status = Integer.parseInt(taskDetails[1]);
                    String desc = taskDetails[2];

                    switch (type) {
                    case "T":
                        ToDo toDo = new ToDo(status, desc);
                        list.add(toDo);
                        break;

                    case "D":
                        LocalDateTime date = convertToDateTime(taskDetails[3]);
                        Deadline deadline = new Deadline(status, desc, date);
                        list.add(deadline);
                        break;

                    case "E":
                        LocalDateTime start = convertToDateTime(taskDetails[3]);
                        LocalDateTime end = convertToDateTime(taskDetails[4]);

                        try {
                            Event event = new Event(status, desc, start, end);
                            list.add(event);
                        } catch (InvalidStartEndException e) {
                            ui.showError(e.getMessage());
                        }
                        break;
                    }
                }
            }
            sc.close();
        } catch (IOException e) {
            ui.showError(e.getMessage());
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
