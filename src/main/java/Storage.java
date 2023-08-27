
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.nio.file.Paths;
import java.util.ArrayList;

public class Storage {
    TaskList tasks;
    public Storage(TaskList tasks) {
        this.tasks = tasks;
    }
    public void save() {
        String dataPath = Paths.get("data", "duke.txt").toString();
        try {
            File dataFile = new File(dataPath);
            BufferedWriter writer = new BufferedWriter(new FileWriter(dataFile));
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                String line = task.saveToFileLine();
                writer.write(line);
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("File duke.txt not found.");
        }

    }

    public void load() {
        try {
            String dataPath = Paths.get("data", "duke.txt").toString();
            File dataFile = new File(dataPath);
            BufferedReader reader = new BufferedReader(new FileReader(dataFile));
            for (String line; (line = reader.readLine()) != null;) {
                tasks.add(fileToTask(line));
            }
        } catch (IOException ex) {
            System.out.println("File duke.txt not found.");
        } catch (DukeException ex) {
            System.out.println(ex.toString());
        }
    }

    private Task fileToTask(String line) throws DukeInvalidSavedToFileLineType {
        String[] savedToFileLine = line.split(" \\| ");
        String type = savedToFileLine[0];

        String status = savedToFileLine[1];
        String description = savedToFileLine[2];
        switch (type) {
            case "T":
                return ToDo.create(status, description);
            case "D":
                String due = savedToFileLine[3];
                return Deadline.create(status, description, due);
            case "E":
                String range = savedToFileLine[3];
                String from = range.substring(0, range.indexOf(" to")).replace("from ", "");
                String to = range.substring(range.indexOf("to ")).replace("to ", "");
                return Event.create(status, description, from, to);
            default:
                throw new DukeInvalidSavedToFileLineType();
        }
    }

}
