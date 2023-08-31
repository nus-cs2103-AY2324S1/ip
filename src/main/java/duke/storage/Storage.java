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

/**
 * The Storage class deals with savings tasks in the file and
 * loading tasks from the file.
 */
public class Storage {
    String filePath;

    /**
     * Constructs a Storage object.
     *
     * @param filePath The filepath of the file containing the tasks.
     */
    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Saves the tasks into the file in hard drive.
     *
     * @param tasks The current list of tasks to be saved into the file.
     * @param ui The user interface of the Duke application.
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

    /**
     * Loads the tasks from the file
     *
     * @return The ArrayList of the tasks saved into the Duke application.
     * @throws DukeException If the task type in the file is invalid, or file is not found.
     */
    public ArrayList<Task> load() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File file = new File(filePath);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String fullTaskCommand = scanner.nextLine();
                String[] taskParts = fullTaskCommand.split("\\|");
                String taskType = taskParts[0].trim();
                int taskStatus = Integer.parseInt(taskParts[1].trim());

                switch (taskType) {
                case "T":
                    Task todo = new ToDo(taskParts[2].trim());
                    tasks.add(todo);
                    todo.markStatusFromFile(taskStatus);
                    break;
                case "D":
                    String datePart = taskParts[3].trim();
                    String timePart = taskParts[4].trim();
                    LocalDate date = LocalDate.parse(datePart, DateTimeFormatter.ofPattern("d MMM yyyy", Locale.US));
                    String formattedDate = date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    LocalTime time = LocalTime.parse(timePart, DateTimeFormatter.ofPattern("h.mma", Locale.US));
                    String formattedTime = time.format(DateTimeFormatter.ofPattern("HHmm"));

                    Task deadline = new Deadline(taskParts[2].trim(), formattedDate, formattedTime);
                    tasks.add(deadline);
                    deadline.markStatusFromFile(taskStatus);
                    break;
                case "E":
                    Task event = new Event(taskParts[2].trim(),
                            taskParts[3].trim().replace("from", ""),
                            taskParts[4].trim().replace("to", ""));
                    tasks.add(event);
                    event.markStatusFromFile(taskStatus);
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
