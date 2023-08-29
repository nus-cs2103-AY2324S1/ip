import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File CURRENT_FILE;
    public Storage() throws DukeException{
        try {
            File directory = new File("./data");
            if (!directory.exists()) {
                directory.mkdir();
            }
            this.CURRENT_FILE = new File("./data/duke");
            if (!CURRENT_FILE.exists()) {
                this.CURRENT_FILE.createNewFile();
            }
        }
        catch (Exception e) {
            throw new DukeException("Cannot Make File :'(");
        }
    }
    public ArrayList<Task> getTaskList() throws DukeException {
        ArrayList<Task> taskList = new ArrayList<Task>();
        try {
            Scanner sc = new Scanner(CURRENT_FILE);
            while (sc.hasNextLine()) {
                String input = sc.nextLine();
                String type = input.substring(1, 2);
                String done = input.substring(5, 6);
                String description = input.substring(8).replaceAll("from:", "/from")
                        .replaceAll("by:", "/by"). replaceAll("to:", "/to").replaceAll("[()]", "");
                if (type.equalsIgnoreCase("T")) {
                    taskList.add(new Todo(description));
                } else if (type.equalsIgnoreCase("D")) {
                    taskList.add(new Deadline(description));
                } else if (type.equalsIgnoreCase("E")) {
                    taskList.add(new Event(description));
                }
                if (done.equalsIgnoreCase("X")) {
                    taskList.get(taskList.size() - 1).markDone();
                }

            }
            return taskList;
        }
        catch (IOException e) {
            throw new DukeException("Cannot Read File :'(");
        }
    }
    public void writeFile(ArrayList<Task> taskList) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(this.CURRENT_FILE);
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                fileWriter.write(task.toString() + "\n");
            }
            fileWriter.close();
        }
        catch (IOException e) {
            throw new DukeException("Cannot Write File :'(");
        }
    }
}
