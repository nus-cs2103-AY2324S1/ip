package juke;

import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Objects;

public class Storage {
    File savefile;
    public Storage(String filepath) {
       this.savefile = new File("./savefile.txt");
        if (!savefile.exists()) {
            try {
                savefile.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Checks if task in data has been completed.
     * @param isDone isDone portion of the task in data.
     * @return Whether the task has been completed.
     */
    private boolean parseIsDone(String isDone) {
        return isDone.equals("true");
    }

    /**
     * Parses one task which was stored in the savefile.
     * @param data The data string representation of the task.
     * @return The parsed Task object.
     */
    private Task parseOne(String data) {
        String[] parts = data.split("\\|");
        if (Objects.equals(parts[0], "T")) {
            return new Todo(parts[2], parseIsDone(parts[1]));
        }
        if (Objects.equals(parts[0], "D")) {
            return new Deadline(parts[2], parseIsDone(parts[1]), LocalDate.parse(parts[3]));
        }
        if (Objects.equals(parts[0], "E")) {
            return new Event(parts[2], parseIsDone(parts[1]),
                    LocalDate.parse(parts[3]), LocalDate.parse(parts[4]));
        }
        return null;
    }

    /**
     * Loads all tasks from the savefile.
     * @return An ArrayList of Tasks which was loaded from the savefile.
     * @throws JukeError If there was an error parsing any of the data.
     */
    public ArrayList<Task> load() throws JukeError {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(savefile));
            // Read the lines from the file one by one.
            String line;
            while ((line = reader.readLine()) != null) {
                tasks.add(parseOne(line));
            }

        } catch (IOException e) {
            throw new JukeError("Failed to load data.");
        }
        return tasks;
    }

    /**
     * Writes a task to the savefile.
     * @param task The task to be written.
     * @throws JukeError If the writing operation failed.
     */
    public void write(Task task) throws JukeError {
        try {
            FileWriter writer = new FileWriter(savefile, true);
            writer.write(task.toData() + "\n");
            writer.flush();
            writer.close();
        } catch (IOException e) {
            throw new JukeError("Failed to write to storage.");
        }
    }

    /**
     * Updates all tasks in the savefile, useful when a task is deleted or modified.
     * @param tasks An ArrayList of all tasks to be written.
     */
    public void updateAll(ArrayList<Task> tasks) {
        try {
            FileWriter deleter = new FileWriter(savefile, false);
            deleter.write("");
            for (Task task : tasks) {
                FileWriter writer = new FileWriter(savefile, true);
                writer.write(task.toData() + "\n");
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
