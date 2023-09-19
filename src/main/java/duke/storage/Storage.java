package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.time.format.DateTimeFormatter;

import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.parser.Parser;

/**
 * Handles reading and writing tasks to file.
 */
public class Storage {
    Scanner sc;
    Ui ui;
    DateTimeFormatter fileFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    /**
     * Initialises the storage object with a scanner for file input and a Ui object.
     * 
     * @param ui The Ui object to handle user interaction.
     */
    public Storage(Ui ui) {
        sc = new Scanner(System.in);
        this.ui = ui;
    }

    /**
     * Reads the tasks from the file and returns an ArrayList of tasks.
     * 
     * @return An ArrayList of tasks.
     * @throws FileNotFoundException If the file is not found.
     * @throws Exception             If there are any other errors reading the file.
     */
    public ArrayList<Task> readFile() throws Exception {
        ArrayList<Task> list = new ArrayList<Task>();

        try {
            File taskFile = new File("tasks.txt");
            Scanner taskScanner = new Scanner(taskFile);
            Parser parser = new Parser(ui);

            List<String> lines = Files.lines(Paths.get("tasks.txt"))
                    .collect(Collectors.toList());

            list = lines.stream()
                    .map(parser::parseFileTask)
                    .collect(Collectors.toCollection(ArrayList::new));

            taskScanner.close();
            assert list != null : "Task list should not be null";
        } catch (FileNotFoundException e) {
            // File does not exist
            try {
                // Create new file
                File taskFile = new File("tasks.txt");
                taskFile.createNewFile();
            } catch (Exception f) {
                ui.showError("Error creating new file.");
            }
        }
        return list;
    }

    /**
     * Writes the tasks to the file.
     * 
     * @param list The TaskList object containing the tasks to be written to file.
     * @throws Exception If there are any errors writing to the file.
     */
    public void writeFile(TaskList list) throws Exception {
        FileWriter fw = new FileWriter("tasks.txt");

        FileWriter taskWriter = new FileWriter("tasks.txt");
        list.stream().forEach(task -> {
            try {
                taskWriter.write(task.toFileString() + "\n");
            } catch (Exception e) {
                ui.showError("Error writing to file.");
            }
        });

        taskWriter.close();

        assert fw != null : "File writer should not be null";
        fw.close();
    }
}
