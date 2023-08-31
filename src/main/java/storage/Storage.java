package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import common.DateParser;
import data.TaskList;
import data.exception.DukeException;
import data.tasks.Deadline;
import data.tasks.Event;
import data.tasks.Task;
import data.tasks.Todo;

public class Storage {
    private final String fileDir;
    private final String filePath;

    public Storage(String filePath, String fileDir) {
        this.filePath = filePath;
        this.fileDir = fileDir;
    }

    public ArrayList<Task> load() {
        try {
            ArrayList<Task> tasks = new ArrayList<>();
            BufferedReader br = new BufferedReader(
                new FileReader(filePath)
            );

            String line;
            while((line = br.readLine()) != null) {
                String[] parse = line.strip().split("\\|");
                // Shortest length is 3 for any task type
                if (parse.length < 3) continue;
                Task task = this.createTask(parse);
                if (task != null) {
                    tasks.add(task);
                }
            }

            // Close the reader after parsing the file
            br.close();
            return tasks;
        } catch (FileNotFoundException f) {
            // Create a new data directory if it cannot be found
            new File(fileDir).mkdir();
            return null;
        } catch (IOException e) {
            return null;
        }
    }

    private Task createTask(String[] parse) {
        String taskType = parse[0];
        boolean isDone = parse[1].equals("1");

        // Create task based on type given
        switch(taskType) {
        case "T":
            return new Todo(parse[2], isDone);
        case "D":
            if (parse.length < 4) break;
            return new Deadline(
                parse[2], 
                DateParser.parseDateString(parse[3]), 
                isDone
            );
        case "E":
            if (parse.length < 5) break;
            return new Event(
                parse[2], 
                DateParser.parseDateString(parse[3]), 
                DateParser.parseDateString(parse[4]), 
                isDone
            );
        default:
            return null;
        }
        return null;
    }

    public void update(TaskList tasks) throws DukeException {
        try {
            FileWriter fw = new FileWriter(filePath);
            fw.write(tasks.toString());
            fw.close();
        } catch (IOException e) {
            throw new DukeException("Sorry error with saving tasks!");
        }
    }
}
