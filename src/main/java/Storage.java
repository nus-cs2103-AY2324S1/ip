import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Handles reading and writing tasks to the storage file.
 */
public class Storage {
    private File file;

    /**
     * Constructor for Storage.
     *
     * @param path The path to the storage file.
     */
    public Storage(String path) {
        this.file = new File(path);
    }

    /**
     * Reads tasks from the storage file.
     *
     * @return An ArrayList of Task objects.
     */
    public ArrayList<Task> read() {
        ArrayList<Task> taskList = new ArrayList<>();

        try {
            Scanner sc = new Scanner(this.file);

            while (sc.hasNextLine()) {
                try {
                    String temp = sc.nextLine();
                    String[] task = temp.split("\\|");

                    if (temp.charAt(0) == 'D') {
                        taskList.add(new Deadline(task[2].trim(), task[1].trim().equals("1"), task[3].trim()));
                    } else if (temp.charAt(0) == 'E') {
                        String[] timeframe = task[3].split("-");
                        taskList.add(new Event(task[2].trim(), task[1].trim().equals("1"), timeframe[0].trim(), timeframe[1].trim()));
                    } else if (temp.charAt(0) == 'T') {
                        taskList.add(new Todo(task[2].trim(), task[1].trim().equals("1")));
                    } else {
                        System.out.println("Unknown task type: " + temp.charAt(0));
                    }
                } catch (Exception e) {
                    System.out.println("Error reading item: " + e);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
            createFile();
        }

        return taskList;
    }

    /**
     * Creates a new file if it does not exist
     * Creates parent directories if needed.
     */    
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

    /**
     * Adds a task in String format to the storage file.
     *
     * @param fileFormat The formatted task to add.
     */
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

    /**
     * Updates a task in the storage file.
     *
     * @param index The index of the task to update.
     * @param updatedFile The updated task in intended String format.
     */    
    public void updateTask(int index, String updatedFile) {
        try {
            Scanner sc = new Scanner(this.file);
            ArrayList<String> updatedTaskList = new ArrayList<>();
            int indx = 0;

            while (sc.hasNextLine()) {
                String currTask = sc.nextLine();

                if (indx != index) {
                    updatedTaskList.add(currTask);
                } else {
                    if (updatedFile != null) {
                        updatedTaskList.add(updatedFile);
                    }
                }

                indx++;
            }

            sc.close();

            FileWriter filewriter = new FileWriter(this.file);
            filewriter.write(String.join("\n", updatedTaskList));
            filewriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}