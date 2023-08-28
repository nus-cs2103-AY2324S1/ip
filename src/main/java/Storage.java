import java.util.ArrayList;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Storage {

    private ArrayList<Task> store = new ArrayList<Task>();
    private String filePath;

    Storage(String filePath) {
        this.filePath = filePath;
    }

    public void writeTask(Task task) {
        store.add(task);
        saveData();
    }

    public Task updateTask(int taskIndex, String status) {
        Task selectedTask = store.get(taskIndex);
        if (status.equals("mark")) {
            selectedTask.markTask();
        } else if (status.equals("unmark")) {
            selectedTask.unmarkTask();
        } else if (status.equals("delete")) {
            store.remove(taskIndex);
        }
        saveData();
        return selectedTask;
    }

    public void saveData() {
        StringBuilder sb = new StringBuilder();
        for (Task task : store) {
            sb.append(task.exportData()).append("\n");
        }
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                file.createNewFile();
            }
            FileWriter fw = new FileWriter(file);
            fw.write(String.valueOf(sb));
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.out.println("Write failure!");
        }
    }

    public ArrayList<Task> load() throws DukeException {
        File f = new File(filePath);
        try {
            Scanner s = new Scanner(f); // create a Scanner using the File as the source
            while (s.hasNext()) {
                // Process file line input
                String newLine = s.nextLine();
                String[] taskComponents = newLine.split(" \\| ");

                String type = taskComponents[0];
                String isCompleted = taskComponents[1];
                String desc = taskComponents[2];
                if (type.equals("T")) {

                    Task item = new ToDo(taskComponents[2]);
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                } else if (type.equals("D")) {

                    if (taskComponents.length < 3) {
                        throw new DukeException("Invalid Deadline Format!");
                    }
                    Task item = new Deadline(desc, taskComponents[3]);
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                } else if (type.equals("E")) {

                    if (taskComponents.length < 3) {
                        throw new DukeException("Invalid Event Format!");
                    }
                    String[] timeComponents = taskComponents[3].split("-", 2);
                    if (taskComponents.length < 2) {
                        throw new DukeException("Invalid Event Format!");
                    }
                    Task item = new Event(desc, timeComponents[0], timeComponents[1]);
                    if (isCompleted.equals("X")) {
                        item.markTask();
                    }
                    store.add(item);

                }
            }
        } catch (FileNotFoundException e) {
            throw new DukeException("File not Found!");
        }

        return store;
    }

}
