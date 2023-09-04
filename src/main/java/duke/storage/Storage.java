package duke.storage;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import duke.command.Command;
import duke.exception.DukeException;
import duke.object.TaskList;
import duke.parser.Parser;
import duke.ui.SilentUi;
import duke.ui.Ui;

/**
 * Writes data to and retrieves data from storage file.
 */
public class Storage {

    private File dataFile;
    private Ui display;

    /**
     * Constructor for storage object.
     *
     * @param display The UI to inform user of any failures.
     */
    public Storage(Ui display) {
        this.display = display;
        this.dataFile = new File(String.join(File.separator, ".", "duke_data", "data.txt"));
        makeFile(this.dataFile);
    }

    private void makeFile(File file) {
        File dir = file.getParentFile();
        if (!dir.exists()) {
            dir.mkdirs();
        }
        try {
            file.createNewFile();
        } catch (IOException e) {
            display.print("Failed to create data file.");
        }
    }

    /**
     * Generates TaskList from stored data.
     *
     * @return TaskList from stored data.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        Ui ui;
        try {
            ui = new SilentUi(new Scanner(dataFile));
        } catch (FileNotFoundException e) {
            ui = new SilentUi(new Scanner(System.in));
        }
        try {
            while (ui.hasNext()) {
                String saved = ui.readCommand();
                if (saved.equals("")) {
                    continue;
                }
                Command cmd = Parser.parse(saved);
                cmd.execute(tasks, ui, this);
            }
        } catch (DukeException e) {
            display.print("Data file is corrupted. Starting from a clear state...");
            tasks.clear();
        }
        ui.close();
        return tasks;
    }

    /**
     * Saves TaskList to file.
     *
     * @param tasks TaskList to be saved.
     */
    public void save(TaskList tasks) {
        try {
            FileWriter writer = new FileWriter(dataFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                writer.append(tasks.get(i).toCommand(i + 1));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            display.print("Failed to save data file.");
        }
    }

}
