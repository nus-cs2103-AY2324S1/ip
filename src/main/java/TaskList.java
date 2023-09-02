import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class TaskList {
    protected ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void addToList(Task task) {
        this.tasks.add(task);
    }

    public void markDone(int taskNum) {
        this.tasks.get(taskNum).markDone();
    }

    public void markUndone(int taskNum) {
        this.tasks.get(taskNum).markUndone();
    }

    public String printTask(int taskNum) {
        return this.tasks.get(taskNum).toString();
    }

    public String printName(int taskNum) {
        return this.tasks.get(taskNum).getName();
    }
    public void numOfTask() {
        System.out.println("N... Now you have... " + this.tasks.size() + " tasks in the list. ( ‘-ωก̀ )");
    }
    public void writeToFile(String filePath) throws IOException {
        FileWriter fw = new FileWriter(filePath);
        for(int i = 0; i < this.tasks.size(); i++) {
            if(this.tasks.get(i) instanceof Event) {
                Event x = (Event) this.tasks.get(i);
                if(this.tasks.get(i).getStatus()) {
                    fw.write("E | 0 |" + this.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                } else {
                    fw.write("E | 1 |" + this.printName(i) + "|" + x.getStart() + "-" + x.getEnd() + "\n");
                }
            }
            if(this.tasks.get(i) instanceof ToDo) {
                if(this.tasks.get(i).getStatus()) {
                    fw.write("T | 0 |" + this.printName(i) + "\n");
                } else {
                    fw.write("T | 1 |" + this.printName(i) + "\n");
                }
            }
            if(this.tasks.get(i) instanceof Deadline) {
                Deadline x = (Deadline) this.tasks.get(i);
                if(this.tasks.get(i).getStatus()) {
                    fw.write("D | 0 |" + this.printName(i) + "| " + x.getBy() + "\n");
                } else {
                    fw.write("D | 1 |" + this.printName(i) + "| " + x.getBy() + "\n");
                }
            }
        }
        fw.close();
    }

    public void readToList(String filePath)throws FileNotFoundException {
        File f = new File(filePath); // create a File for the given file path
        Scanner s = new Scanner(f); // create a Scanner using the File as the source
        while (s.hasNext()) {
            String line = s.nextLine();
            String[] parts = line.split("\\|");
            if(parts[0].equals("T ")) {
                ToDo temp = new ToDo(parts[2]);
                this.addToList(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if (parts[0].equals("D ")) {
                String[] x = parts[3].split(" ");
                Deadline temp = new Deadline(parts[2], x[1]);
                this.addToList(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
            if(parts[0].equals("E ")) {
                String[] duration = parts[3].split("-");
                Event temp = new Event(parts[2], duration[0], duration[1]);
                this.addToList(temp);
                if(parts[1].equals(" 0 ")) {
                    temp.markDone();
                }
            }
        }
    }
    public void deleteTask(int taskNum) {
        System.out.println(this.printTask(taskNum));
        this.tasks.remove(taskNum);
    }

    public void printList() {
        for(int i = 0; i < tasks.size(); i++) {
            int x = i + 1;
            System.out.println(x + ". " + this.printTask(i));
        }
    }
}
