import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class DukeManager {
    public static ArrayList<Task> loadData(File file) {
        ArrayList<Task> storedTasks = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] lineSegmented = line.split("\\|");
                String taskType = lineSegmented[0];
                String isDone = lineSegmented[1];
                switch (isDone) {
                    case "X":
                        switch (taskType) {
                            case "T":
                                Todo todoTask = new Todo(lineSegmented[2]);
                                todoTask.markAsDone();
                                storedTasks.add(todoTask);
                                break;
                            case "D":
                                Deadline deadlineTask = new Deadline(lineSegmented[2], lineSegmented[3]);
                                deadlineTask.markAsDone();
                                storedTasks.add(deadlineTask);
                                break;
                            case "E":
                                Event eventTask = new Event(lineSegmented[2], lineSegmented[3], lineSegmented[4]);
                                eventTask.markAsDone();
                                storedTasks.add(eventTask);
                                break;
                            default:
                                System.out.println("Unknown Task Type");
                                break;
                        }
                        break;
                    case " ":
                        switch (taskType) {
                            case "T":
                                Todo todoTask = new Todo(lineSegmented[2]);
                                storedTasks.add(todoTask);
                                break;
                            case "D":
                                Deadline deadlineTask = new Deadline(lineSegmented[2], lineSegmented[3]);
                                storedTasks.add(deadlineTask);
                                break;
                            case "E":
                                Event eventTask = new Event(lineSegmented[2], lineSegmented[3], lineSegmented[4]);
                                storedTasks.add(eventTask);
                                break;
                            default:
                                System.out.println("Unknown Task Type");
                                break;
                        }
                        break;
                    default:
                        System.out.println("Unknown isDone marker");
                        break;
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storedTasks;
    }
}
