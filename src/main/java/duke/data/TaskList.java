package duke.data;

import duke.assets.tasks.TaskAbstract;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TaskList {
    private ArrayList<TaskAbstract> taskList;

    public TaskList () {
        this.taskList = new ArrayList<TaskAbstract>();
    }

    public void addTask(TaskAbstract newTask) {
        this.taskList.add(newTask);
    }

    public void markTaskAt(int index) {
        this.taskList.get(index).completeTask();
    }

    public void unmarkTaskAt(int index) {
        this.taskList.get(index).undo();
    }

    public void writeToFile() {
        try {
            File myFile = new File("./duke.txt");
            FileWriter fw = new FileWriter(myFile);
            PrintWriter pw = new PrintWriter(fw);
            for (TaskAbstract t : this.taskList) {
                pw.println(t.saveToTextFormat());
            }
            pw.close();
        } catch (IOException IOExcept) {
            System.out.println("ChadGPT: Please check if your I/O is working as intended.");
        }
    }

    public void listTasks() {
        int counter = 0;
        for (TaskAbstract t : this.taskList) {
            System.out.print("    " + ++counter + ". ");
            t.printStatus();
        }
    }
}
