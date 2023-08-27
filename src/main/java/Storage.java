import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File file;

    public Storage(String path) {
        this.file = new File(path);
    }

    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                String[] task = sc.nextLine().split("\\|");

                if (task[0] == "T") {
                    taskList.add(new Todo(task[2], task[1].equals("1")));
                    break;
                } else if (task[0] == "D") {
                    taskList.add(new Deadline(task[2], task[1].equals("1"), task[3]));
                    break;
                } else if (task[0] == "E") {
                    String[] timeframe = task[3].split("-");
                    taskList.add(new Event(task[2], task[1].equals("1"), timeframe[0], timeframe[1]));
                    break;
                } else {
                    System.out.println("Unknown task type: " + task[0]);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            createFile();
        }

        return taskList;
    }

    private void createFile() {
        this.file.getParentFile().mkdir();
        try {
            if (this.file.createNewFile()) {
                System.out.println("File has been created successfully: " + this.file.getPath());
            }
        } catch (IOException e) {
            System.out.println(e);
            System.out.println("There is an error creating the file.");
        }
    }

    public void addTask(String fileFormat) {
        try {
            FileWriter fw = new FileWriter(this.file, true);

            if (this.file.length() != 0) {
                fw.write("\n");
            }

            fw.write(fileFormat);
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }

    }

    public void updateTask(int index, String updatedFile) {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<String> updatedTaskList = new ArrayList<>();
            int idx = 0;

            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();

                if (idx != index) {
                    updatedTaskList.add(currTask);
                } else {
                    if (updatedFile != null) {
                        updatedTaskList.add(updatedFile);
                    }
                }

                idx++;
            }

            sc.close();

            FileWriter fw = new FileWriter(this.file);
            fw.write(String.join("\n", updatedTaskList));
            fw.close();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}
