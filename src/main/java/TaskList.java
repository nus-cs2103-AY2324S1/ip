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
    private String filePath;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public void setHardDiskFilePath(String filePath) {
        this.filePath = filePath;
    }

    public void loadData() {
        String directoriesPathString = this.getParentDirectoriesPathFromFilePath();
        Path directoriesPath = Paths.get(directoriesPathString);

        try {
            Files.createDirectories(directoriesPath);
            File data = new File(this.filePath);
            boolean fileAlreadyExists = !data.createNewFile();

            if (fileAlreadyExists) {
                Scanner fileScanner = new Scanner(data);
                while (fileScanner.hasNextLine()) {
                    String input = fileScanner.nextLine();
                    char taskType = input.charAt(0);

                    switch (taskType) {
                        case 'T':
                            this.loadTodo(input);
                            break;
                        case 'D':
                            this.loadDeadline(input);
                            break;
                        case 'E':
                            this.loadEvent(input);
                            break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }

    }

    public void loadTodo(String input) {
        int[] vLines = new int[2];
        for (int i = 0, j = 0; i < input.length(); i++) {
            if (input.charAt(i) != '|') {
                continue;
            }
            vLines[j++] = i;
        }

        Task task = new Todo(input.substring(vLines[1] + 2));
        if (input.charAt(4) == '1') {
            task.mark();
        }
        this.list.add(task);
    }

    public void loadDeadline(String input) {
        int[] vLines = new int[3];
        for (int i = 0, j = 0; i < input.length(); i++) {
            if (input.charAt(i) != '|') {
                continue;
            }
            vLines[j++] = i;
        }

        String description = input.substring(vLines[1] + 2, vLines[2] - 1);
        String by = input.substring(vLines[2] + 2);

        Task task = new Deadline(description, by);
        if (input.charAt(4) == '1') {
            task.mark();
        }
        this.list.add(task);
    }

    public void loadEvent(String input) {
        int[] vLines = new int[4];
        for (int i = 0, j = 0; i < input.length(); i++) {
            if (input.charAt(i) != '|') {
                continue;
            }
            vLines[j++] = i;
        }

        String description = input.substring(vLines[1] + 2, vLines[2] - 1);
        String from = input.substring(vLines[2] + 2, vLines[3] - 1);
        String to = input.substring(vLines[3] + 2);

        Task task = new Event(description, from, to);
        if (input.charAt(4) == '1') {
            task.mark();
        }
        this.list.add(task);
    }

    public String getParentDirectoriesPathFromFilePath() {
        String path = "";
        for (int i = this.filePath.length() - 1; i >= 0; i--) {
            if (!this.filePath.substring(i, i + 1).equals("/")) {
                continue;
            }
            path = this.filePath.substring(0, i + 1);
            break;
        }
        return path;
    }

    public void listOutEverything() {
        System.out.println(Duke.i5 + "Here are the tasks in your list:");
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println(Duke.i5 + (i + 1) + "." + this.list.get(i));
        }
    }

    public boolean isOutOfRange(int index) {
        return index < 0 || this.list.size() <= index;
    }

    public void mark(int index) {
        this.list.get(index).mark();
        updateHardDisk();
    }

    public void unmark(int index) {
        this.list.get(index).unmark();
        updateHardDisk();
    }

    public void add(Task task) {
        this.list.add(task);
        System.out.println(Duke.i5 + "Got it. I've added this task:");
        System.out.println(Duke.i7 + task);
        System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        updateHardDisk();
    }

    public void remove(int index) {
        Task t = this.list.remove(index);
        System.out.println(Duke.i5 + "Noted. I've removed this task:");
        System.out.println(Duke.i7 + t);
        System.out.println(Duke.i5 + "Now you have " + this.list.size() + " tasks in the list.");
        updateHardDisk();
    }

    public void updateHardDisk() {
        File oldFile = new File(this.filePath);
        oldFile.delete();
        File newFile = new File(this.filePath);

        try {
            FileWriter fileWriter = new FileWriter(newFile);
            for (Task task : this.list) {
                fileWriter.write(task.toStringForSave() + "\n");
            }
            fileWriter.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
