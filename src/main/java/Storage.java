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

    private boolean parseIsDone(String isDone) {
        return isDone.equals("true");
    }

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
