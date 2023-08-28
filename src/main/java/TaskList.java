import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TaskList {
    private List<Task> list;
    private Storage storage;

    public TaskList() {
        this.list = new ArrayList<>();
        this.storage = new Storage();
    }

    public void setHardDiskFilePath(String filePath) {
        this.storage.setHardDiskFilePath(filePath);
    };

    public void loadData() {
        this.storage.loadData(this.list);
    }

    public void listOutEverything() {
        System.out.println(Ui.i5 + "Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(Ui.i5 + (i + 1) + "." + this.list.get(i));
        }
    }

    public boolean isOutOfRange(int index) {
        return index < 0 || this.list.size() <= index;
    }

    public void mark(int index) {
        this.list.get(index).mark();
        this.storage.updateHardDisk(this.list);
    }

    public void unmark(int index) {
        this.list.get(index).unmark();
        this.storage.updateHardDisk(this.list);
    }

    public void add(Task task) {
        this.list.add(task);
        System.out.println(Ui.i5 + "Got it. I've added this task:");
        System.out.println(Ui.i7 + task);
        System.out.println(Ui.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        this.storage.updateHardDisk(this.list);
    }

    public void remove(int index) {
        Task t = this.list.remove(index);
        System.out.println(Ui.i5 + "Noted. I've removed this task:");
        System.out.println(Ui.i7 + t);
        System.out.println(Ui.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        this.storage.updateHardDisk(this.list);
    }
}
