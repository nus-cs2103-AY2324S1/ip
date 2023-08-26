import java.io.File;
import java.io.FileWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Storage {
    private final File file;

    public Storage(String file_Path) throws IOException, DukeException{
        File savedFile = new File(file_Path);
        try {
            if (!savedFile.exists() && !savedFile.createNewFile()) {
                throw new IOException("Failed to create a new file.");
            }
        } catch (IOException e) {
            throw new DukeException("Error while saving the file: " + e.getMessage());
        }
        this.file = savedFile;
    }

    public ArrayList<Task> loadTasks() throws FileNotFoundException, DukeException {
        ArrayList<Task> tasks = new ArrayList<>();

        try (Scanner sc = new Scanner(this.file)) {
            while (sc.hasNext()) {
                String curLine = sc.nextLine();
                String[] splits = curLine.split("#");
                switch (splits[0].trim()) {
                    case "T": {
                        Task newTask = new Todo(splits[2].trim(), (splits[1].trim().equals("1")));
                        tasks.add(newTask);
                        break;
                    }
                    case "D": {
                        Task newTask = new Deadline(splits[2].trim(), splits[3].trim(), (splits[1].trim().equals("1")));
                        tasks.add(newTask);
                        break;
                    }
                    case "E": {
                        Task newTask = new Event(splits[2].trim(), splits[3].trim(), (splits[1].trim().equals("1")));
                        tasks.add(newTask);
                        break;
                    }
                    default: {
                        throw new DukeException("Invalid task type in the data file.");
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("Storage file not found.");
        }  catch (Exception e) {
            throw new DukeException("Error occurred when reading the data file: " + e.getMessage());
        }
        return tasks;
    }
    public void save(ArrayList<Task> tasks) throws IOException {
        FileWriter fw = new FileWriter(this.file);
        String textToAdd = translateToStore(tasks);
        fw.write(textToAdd);
        fw.close();
    }

    public String translateToStore(ArrayList<Task> tasks) {
        StringBuilder result = new StringBuilder();

        for (Task task : tasks) {
            switch (task.type) {
            case TODO:
                result.append("T # ")
                      .append(task.isDone ? "1" : "0")
                      .append(" # ")
                      .append(task.description)
                      .append("\n");
                break;
            case DEADLINE:
                Deadline deadline = (Deadline) task;
                result.append("D # ")
                        .append(task.isDone ? "1" : "0")
                        .append(" # ")
                        .append(task.description)
                        .append(" # ")
                        .append(deadline.d_time)
                        .append("\n");
                break;
            case EVENT:
                Event event = (Event) task;
                result.append("E # ")
                        .append(task.isDone ? "1" : "0")
                        .append(" # ")
                        .append(task.description)
                        .append(" # ")
                        .append(event.e_start)
                        .append(" - ")
                        .append(event.e_end)
                        .append("\n");
                break;
            default:
                    throw new IllegalStateException("Unexpected task type: " + task.type);
            }
        }

        return result.toString();
    }


}
