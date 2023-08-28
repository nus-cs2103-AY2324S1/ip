package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

public class Storage {
    private String filePath;

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    public ArrayList<Task> load() throws Exception {
        ArrayList<Task> arr = new ArrayList<>();
        File f = new File(filePath);
        try {
            f.createNewFile();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        try {
            Scanner dataSc = new Scanner(f);
            try {
                while (dataSc.hasNextLine()) {
                    String[] task = dataSc.nextLine().trim().split("\\|");
                    task[0] = task[0].trim();
                    int taskType = task[0].equals("T") ? 0 : task[0].equals("D") ? 1 : 2;
                    switch (taskType) {
                        case 0: {
                            String dataString = "todo " + task[2].trim();
                            arr.add(Todo.of(dataString));
                            break;
                        }
                        case 1: {
                            String dataString = "deadline " + task[2].trim() + " /by " + task[3].trim();
                            arr.add(Deadline.of(dataString));
                            break;
                        }
                        case 2: {
                            String dataString = "event " + task[2].trim() + " /from " + task[3].trim() + " /to "
                                    + task[4].trim();
                            arr.add(Event.of(dataString));
                            break;
                        }
                    }
                    if (task[1].trim().equals("1")) {
                        arr.get(arr.size() - 1).markAsDone();
                    } else {
                        arr.get(arr.size() - 1).markAsNotDone();
                    }
                }
            } catch (DukeException e) {
                System.out.println("file is corrupted! dieee");
            }
            dataSc.close();
        } catch (FileNotFoundException e) {
            throw e;
        }
        return arr;
    }

    public void update(TaskList arr) {
        try {
            FileOutputStream object = new FileOutputStream(this.filePath, false);
            for (char c : arr.storage().toCharArray()) {
                object.write(c);
            }
            object.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
