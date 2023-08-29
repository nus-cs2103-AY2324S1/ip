import java.io.IOException;
import java.util.ArrayList;
import java.io.FileWriter;

public class FunnyList {

    private ArrayList<Task> taskList;

    public FunnyList() {
        this.taskList = new ArrayList<>();
    }

    public void displayList() {
        Duke.printLine();
        for (int i = 0; i < taskList.size(); i++) {
            System.out.println("\t" + String.valueOf(i + 1) + ". " + taskList.get(i));
        }
        Duke.printLine();
    }

    public void completeTask(int index) throws DukeException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        taskList.get(index).completeTask();
        this.writeToFile();
    }

    public void undoTask(int index) throws DukeException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        taskList.get(index).undoTask();
        this.writeToFile();
    }

    public void deleteTask(int index) throws DukeException {
        if (index < 0 || index >= this.taskList.size()) {
            throw new DukeException("Index does not exist");
        }
        Task task = taskList.get(index);
        taskList.remove(index);
        Duke.printLine();
        System.out.println("\tNoted. I've removed this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + String.valueOf(this.taskList.size()) + " tasks in the list");
        Duke.printLine();
        this.writeToFile();
    }
    public void add(Task task) {
        this.taskList.add(task);
        Duke.printLine();
        System.out.println("\tGot it. I've added this task:");
        System.out.println("\t\t" + task);
        System.out.println("\tNow you have " + String.valueOf(this.taskList.size()) + " tasks in the list");
        Duke.printLine();
        this.writeToFile();
    }

    public void writeToFile() {
        try {
            FileWriter fw = new FileWriter("./tasks.txt");
            for (int i = 0; i < this.taskList.size(); i++) {
                this.taskList.get(i).writeToFile(fw);
                fw.write(System.lineSeparator());
            }
            fw.close();
        } catch(IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
    }
}
