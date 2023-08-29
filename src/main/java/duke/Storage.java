package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

public class Storage {

    public String filePath;

    public ArrayList<Task> fileData;

    public Storage(String filePath) {
        this.filePath = filePath;
        this.fileData = new ArrayList<Task>();
    }

    public ArrayList<Task> load(String filePath) throws DukeException {
        try {
            File f = new File(filePath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String[] parts = s.nextLine().split("\\|");
                String event = parts[0];
                int mark = Integer.parseInt(parts[1]);
                String description = parts[2];
                if (Objects.equals(event, "T")) {
                    Task newTask = new Todo(description);
                    newTask.isDone = mark == 1;
                    fileData.add(newTask);
                } else if (Objects.equals(event, "D")) {
                    Task newTask = new Deadline(description, parts[3]);
                    newTask.isDone = mark == 1;
                    fileData.add(newTask);
                } else if (Objects.equals(event, "E")) {
                    Task newTask = new Event(description, parts[3], parts[4]);
                    newTask.isDone = mark == 1;
                    fileData.add(newTask);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return fileData;
    }

    public void save(String filePath, ArrayList<Task> newTasks) throws DukeException, IOException {
        try {
            FileWriter fw = new FileWriter(filePath);
            for (int i = 0; i < newTasks.size(); i++) {
                Task newTask = newTasks.get(i);
                int isDoneValue = newTask.isDone ? 1 : 0;
                if (Objects.equals(newTask.tag, "T")) {
                    fw.write(String.format("%s|%d|%s%n", newTask.generalTag(), isDoneValue, newTask.getDescription()));
                } else if (Objects.equals(newTask.tag, "D")) {
                    Deadline deadlineTask = (Deadline) newTask;
                    fw.write(String.format("%s|%d|%s|%s%n", deadlineTask.generalTag(), isDoneValue, deadlineTask.getDescription(), deadlineTask.getDeadline()));
                } else if (Objects.equals(newTask.tag, "E")) {
                    Event eventTask = (Event) newTask;
                    fw.write(String.format("%s|%d|%s|%s|%s%n", eventTask.generalTag(), isDoneValue, eventTask.getDescription(), eventTask.getFrom(), eventTask.getTo()));
                }

            }
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
