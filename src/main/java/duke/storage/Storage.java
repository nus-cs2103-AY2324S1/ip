package duke.storage;

import duke.exceptions.JamesBondException;
import duke.parser.Parser;
import duke.tasks.Task;
import duke.tasks.TaskList;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
//CHECKSTYLE.ON: CustomImportOrder
/**
 * The `Storage` class is responsible for handling the storage of task data in a file and loading tasks from a file.
 */
public class Storage {
    private File file;

    /**
     * Constructs a `Storage` object with the specified file path for task data storage.
     *
     * @param filePath The file path where task data is stored.
     */
    public Storage(String filePath) {
        try {
            String[] splited = filePath.split("/");
            File dir = new File(splited[0]);

            if (!dir.exists()) {
                dir.mkdir();
            }
            File content = new File(filePath);
            if (!content.exists()) {
                content.createNewFile();
            }
            this.file = content;
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Loads tasks from the file and returns a `TaskList` containing the loaded tasks.
     *
     * @return A `TaskList` containing the loaded tasks.
     * @throws JamesBondException If an error occurs while loading tasks from the file.
     */
    public TaskList loadTasksFromFile() throws JamesBondException {
        TaskList tasksList = new TaskList();
        try {
            List<String> lines = Files.readAllLines(Paths.get(file.getAbsolutePath()));
            for (String line : lines) {
                Task task = Parser.parseFomSaveFormat(line);
                tasksList.add(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tasksList;
    }

    /**
     * Saves tasks to the file.
     *
     * @param tasks The `TaskList` containing the tasks to be saved.
     */
    public void updateFile(TaskList tasks) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file.getAbsolutePath()))) {
            for (Task task : tasks.getToDos()) {
                writer.write(task.toSaveFormat());
                writer.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
 }
