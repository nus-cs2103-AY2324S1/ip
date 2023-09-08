package duke;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public int size() {
        return taskList.size();
    }
    public String add(Task task, boolean flag) {
        taskList.add(task);
        if (flag) {
            System.out.println("Got it. I've added this task:\n" + task.toString());
            return "Got it. I've added this task:\n" + task;
        }
        return "";
    }

    public void writeToFile() throws DukeException {
        try {
            File file = new File("./data/duke.txt");
            file.delete();
            FileWriter writer = new FileWriter("./data/duke.txt");
            for (Task task : taskList) {
                writer.write(task.toString() + "\n");
            }
            writer.close();
        } catch (IOException e) {
            throw new DukeException("OOPS! I cannot write to the file.");
        }
    }

    public Task get(int i) {
        return taskList.get(i);
    }

    public String delete(Task task) throws DukeException {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        String removedTask = task.toString();
        taskList.remove(task);
        writeToFile();
        System.out.println(this);
        return "Noted. I've removed this task:\n" + removedTask + "\n" + this;
    }

    public String list() {
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
        }
        System.out.println(returnString);
        return returnString;
    }

    @Override
    public String toString() {
        return "Now you have " + taskList.size() + " task(s) in the list";
    }
}
