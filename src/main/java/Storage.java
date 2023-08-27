import ExceptionFolder.NoTasksStoredException;

import java.io.*;
import java.util.ArrayList;

public class Storage {

    private String dataFolderName;
    private String fileName;
    public Storage(String filePath) {
        String[] filePathSegmented = filePath.split("/");
        this.dataFolderName = filePathSegmented[0];
        this.fileName = filePathSegmented[1];
    }

    public ArrayList<Task> load() throws NoTasksStoredException {
        ArrayList<Task> storedTasks = new ArrayList<>();
        File dataFolder = new File(this.dataFolderName);
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
        File file = new File(dataFolder, this.fileName);
        if (file.exists()) {
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
        } else {
            throw new NoTasksStoredException("No stored tasks detected");
        }
        return storedTasks;
    }

    public void save(ArrayList<Task> tasks) {
        try {
            FileWriter writer = new FileWriter(this.fileName);
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                writer.write(task.saveTask());

                if (i < tasks.size() - 1) {
                    writer.write(System.lineSeparator());
                }
            }
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
