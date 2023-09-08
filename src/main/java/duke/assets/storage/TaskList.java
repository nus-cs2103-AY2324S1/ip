package duke.assets.storage;

import duke.assets.tasks.TaskAbstract;
import duke.dukeexceptions.StateCannotBeAlteredException;

import java.io.IOException;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class TaskList {
    private static final String HORIZONTAL = "------------------------------------------------------------" +
            "---------------------------";
    private ArrayList<TaskAbstract> taskList;
    private int numberOfTasks;

    public TaskList () {
        this.taskList = new ArrayList<TaskAbstract>();
        this.numberOfTasks = 0;
    }

    public void addTask(TaskAbstract newTask) {
        this.taskList.add(newTask);
        this.numberOfTasks++;
    }

    public void markTaskAt(int index) {
        if (index < 0 || index >= this.numberOfTasks) {
            if (this.numberOfTasks == 0) {
                System.out.println("ChadGPT: Please add at least one task to your list first :)");
            } else {
                System.out.println("ChadGPT: Ensure the index is of in the range 1 - " + this.numberOfTasks);
            }
        } else {
            try {
                this.taskList.get(index).completeTask();
                System.out.println("ChadGPT: Great, I'll mark task!");
            } catch (StateCannotBeAlteredException exp) {
                System.out.println("ChadGPT: Task is already complete :-)");
            }
        }
    }

    public void unmarkTaskAt(int index) {
        if (index < 0 || index >= this.numberOfTasks) {
            if (this.numberOfTasks == 0) {
                System.out.println("ChadGPT: Please add at least one task to your list first :)");
            } else {
                System.out.println("ChadGPT: Ensure the index is of in the range 1 - " + this.numberOfTasks);
            }
        } else {
            try {
                this.taskList.get(index).undo();
                System.out.println("ChadGPT: I'll unmark it for now but do remember to complete it!");
            } catch (StateCannotBeAlteredException exp) {
                System.out.println("ChadGPT: Task is already incomplete :-)");
            }
        }
    }

    public void deleteTaskAt(int index) {
        if (index < 0 || index >= this.numberOfTasks) {
            if (this.numberOfTasks == 0) {
                System.out.println("ChadGPT: Can't delete from an empty list :(");
            } else {
                System.out.println("ChadGPT: Ensure the index is of in the range 1 - " + this.numberOfTasks);
            }
        } else{
            System.out.println("ChadGPT: Deleted!");
            this.taskList.remove(index);
            this.numberOfTasks--;
        }
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

    public String saveToFileFormat() {
        String returnString = "";
        for (TaskAbstract t : taskList) {
            returnString += t.saveToTextFormat();
            returnString += "\n";
        }
        return returnString;
    }

    public void listTasks() {
        int counter = 0;
        for (TaskAbstract t : this.taskList) {
            System.out.print("    " + ++counter + ". ");
            t.printStatus();
        }
    }

    public void clearList() {
        this.taskList.clear();
        this.numberOfTasks = 0;
    }
}
