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

/**
 * Writes data to and retrieves data from storage file.
 */
public class Storage {

    private File dataFile;

    /**
     * Constructs Storage.
     */
    public Storage() {
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
            assert false;
        }
    }

    /**
     * Generates TaskList from stored data.
     *
     * @return TaskList from stored data.
     */
    public TaskList loadTasks() {
        TaskList tasks = new TaskList();
        Scanner sc;
        try {
            sc = new Scanner(dataFile);
        } catch (FileNotFoundException e) {
            sc = new Scanner(System.in);
        }
        assert sc != null;
        try {
            while (sc.hasNext()) {
                String saved = sc.nextLine().replaceAll("\n", "").trim();
                if (saved.equals("")) {
                    continue;
                }
                Command cmd = Parser.parse(saved);
                cmd.execute(tasks, this);
            }
        } catch (DukeException e) {
            tasks.clear();
        }
        return tasks;
    }

    /**
     * Saves TaskList to file.
     *
     * @param tasks TaskList to be saved.
     */
    public void save(TaskList tasks) {
        assert dataFile.exists();
        try {
            FileWriter writer = new FileWriter(dataFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                writer.append(tasks.get(i).toCommand(i + 1));
            }
            writer.flush();
            writer.close();
        } catch (IOException e) {
            assert false;
        }
    }

}
