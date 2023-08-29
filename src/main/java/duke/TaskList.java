package duke;

import java.io.*;
import java.util.ArrayList;

public class TaskList {
    private static ArrayList<Task> taskList = new ArrayList<>();

    public int size() {
        return taskList.size();
    }
    public void add(Task task, boolean flag) {
        taskList.add(task);
        if (flag) {
            System.out.println("Got it. I've added this task:\n" + task.toString());
        }
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

    public void delete(Task task) throws DukeException {
        System.out.println("Noted. I've removed this task:\n" + task.toString());
        taskList.remove(task);
        writeToFile();
        System.out.println(this);
    }

    public void list() {
        String returnString = "";
        for (int i = 0; i < taskList.size(); i++) {
            returnString += i+1 + ". " + taskList.get(i).toString() + "\n";
        }
        System.out.println(returnString);
    }

    @Override
    public String toString() {
        return "Now you have " + taskList.size() + " task(s) in the list";
    }
}
