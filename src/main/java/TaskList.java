import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class TaskList {
    private ArrayList<Task> list;
    private File file;

    public TaskList() {
        this.list = new ArrayList<Task>();
    }

    public TaskList(File file) {
        this.list = new ArrayList<Task>();
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String storedTask = scanner.nextLine();
                String[] taskArray = storedTask.split(",");
                Task task;
                if (taskArray[0].startsWith("Todo")) {
                    task = new ToDoTask(taskArray[2]);
                } else if (taskArray[0].startsWith("Deadline")) {
                    task = new DeadlineTask(taskArray[2], taskArray[3]);
                } else {
                    task = new EventTask(taskArray[2], taskArray[3], taskArray[4]);
                }
                if ((taskArray[1]).equals("1")) {
                    task.setDone();
                }
                this.list.add(task);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        } catch (DukeException e) {
            System.out.println(e);
        }
        this.file = file;
    }

    public void addTask(Task task) {
        this.list.add(task);
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void setTaskComplete(int i) {
        Task task = this.list.get(i);
        task.setDone();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
    }

    public void setTaskIncomplete(int i) {
        Task task = this.list.get(i);
        task.setNotDone();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(task);
    }

    public Task getTask(int i) {
        return this.list.get(i);
    }

    public void deleteTask(int i) {
        Task task = this.list.get(i);
        this.list.remove(i);
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + this.list.size() + " task(s) in the list.");
    }

    public void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println(i + 1 + "." + list.get(i));
        }
    }

    public void close() {
        try {
            FileWriter writer = new FileWriter(this.file);
            for (Task task : list) {
                writer.write(task.toFileString());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while saving your tasks.");
            e.printStackTrace();
        }
    }
}
