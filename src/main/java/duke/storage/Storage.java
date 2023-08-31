package duke.storage;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import duke.ui.Ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Storage {
    String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the task list data to the storage file.
     *
     * @param tasks List of tasks to be added into the storage file.
     */
    public void save(ArrayList<Task> tasks, Ui ui) {
        File f = new File(this.filePath);

        if (!f.exists()) {
            try {
                // Create folder and file
                f.getParentFile().mkdirs();
                f.createNewFile();
            } catch (IOException e) {
                ui.showErrorMessage(e.getMessage());
            }
        }

        try {
            // Write tasks into hard disk
            FileWriter fileWriter = new FileWriter(this.filePath);
            for (Task task : tasks) {
                fileWriter.write(task.toFileFormat());
            }
            fileWriter.close();
        } catch (IOException e) {
            ui.showErrorMessage(e.getMessage());
        }
    }

    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String fullTaskCommand = scanner.nextLine();
                String[] taskParts = fullTaskCommand.split("\\|");
                String taskType = taskParts[0].trim();

                switch (taskType) {
                case "T":
                    tasks.add(new ToDo(taskParts[2].trim()));
                    break;
                case "D":
                    String datePart = taskParts[3].replace(" by ", "").trim();
                    String timePart = taskParts[4].trim();

                    LocalDate date = LocalDate.parse(datePart, DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US));
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    LocalTime time = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("h.mma", Locale.US));
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("HHmm"));

                    tasks.add(new Deadline(taskParts[2].trim(), formattedDate, formattedTime));
                    break;
                case "E":
                    tasks.add(new Event(taskParts[2].trim(), taskParts[3].trim(), taskParts[4].trim()));
                    break;
                default:
                    throw new DukeException("Invalid task type in file: " + taskType);
                }
            }
            scanner.close();
        } catch (DukeException | FileNotFoundException e) {
            throw new DukeException("File not found: " + filePath);
        }

        return tasks;
    }
}
