package alpha;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;

/**
 * Class that handles creating the file, reading from the file, storing tasks to the file and updating the file.
 *
 * @author Wong Joon Hung
 */
public class FileHandler {

    private boolean isCreated = false;
    private File alphaTxt = new File(new File(System.getProperty("user.dir")) +
            File.separator + "data" + File.separator + "alpha.txt");
    private FileWriter writer;
    private BufferedReader reader;

    public FileHandler() {
    }

    /**
     * Checks for the existence of the data/alpha.txt file. Creates it if it does not exist.
     */
    public void checkAndCreate() {
        boolean directoryExists = java.nio.file.Files.exists(java.nio.file.Paths.get("data"));
        // Check if the file exists
        boolean dataExists = java.nio.file.Files.exists(java.nio.file.Paths.get("data", "alpha.txt"));
        try {
            if (!directoryExists) {
                new File("data").mkdir();
                isCreated = alphaTxt.createNewFile();
            } else if (!dataExists) {
                isCreated = alphaTxt.createNewFile();
            }
        } catch (IOException e) {
            System.out.println("There was a problem reading data from the file.");
        }
    }

    /**
     * Checks if the file is created or not. If not, it reads any tasks from the file and adds them to the current
     * task list.
     *
     * @return a new task list with tasks from the file added.
     */
    public TaskList readFromFile() {
        TaskList taskList = new TaskList();
        if (isCreated) {
            return taskList;
        }
        try {
            Scanner sc = new Scanner(alphaTxt);
            while (sc.hasNext()) {
                String input = sc.nextLine();
                String[] splitInput = input.split("\\|");
                String type = splitInput[0];
                String checked = splitInput[1];
                String description = splitInput[2].trim();
                Task task;
                if (type.equals("T ")) {
                    task = ToDo.createToDo(description);
                } else if (type.equals("D ")) {
                    task = Deadline.makeDeadline(description, splitInput[3]);
                } else {
                    task = Event.makeEvent(description, splitInput[3], splitInput[4]);
                }
                if (checked.equals("X")) {
                    assert task != null;
                    task.mark();
                }
                taskList.add(task);
            }
            return taskList;
        } catch (FileNotFoundException e) {
            System.out.println("The file could not be found");
            return taskList;
        } catch (Exception e) { // The code should never reach this stage since everything saved to the file should be correctly formatted.
            return taskList;
        }
    }

    /**
     * Saves a task to the file.
     *
     * @param task The task to add to the file.
     */
    // Saves Task to file whenever a Task is added
    public void saveToFile(Task task) {
        try {
            writer = new FileWriter(alphaTxt, true);
            if (alphaTxt.length() != 0) {
                writer.write(System.lineSeparator());
            }
            if (task instanceof Deadline) {
                writer.write("D |" + task.getStatusIcon() + "| " + task.getDescription() + " | "
                        + ((Deadline) task).getDateBy() + " " + ((Deadline) task).getTimeBy());
            } else if (task instanceof ToDo) {
                writer.write("T |" + task.getStatusIcon() + "| " + task.getDescription());
            } else {
                writer.write("E |" + task.getStatusIcon() + "| " + task.getDescription() + " | " + ((Event) task).getStart()
                        + " | " + ((Event) task).getEnd());
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("I/O Exception");
        }
    }

    /**
     * Checks or unchecks a task in the file depending on the second boolean parameter.
     *
     * @param index The index of the task to be marked or unmarked.
     * @param check Boolean of whether the task is to be checked or unchecked.
     */
    // Checks or unchecks a task in the file. Does so by creating a temp file that copies everything over
    // except the task that is being checked/unchecked.
    public void checkOrUncheck(int index, boolean check) {
        try {
            int temp_index = index;
            File temp = File.createTempFile("file", ".txt", alphaTxt.getParentFile());
            writer = new FileWriter(temp, true);
            reader = new BufferedReader(new FileReader(alphaTxt));
            String curr = reader.readLine();
            while (temp_index > 1) {
                writer.write(curr);
                writer.write(System.lineSeparator());
                temp_index--;
                curr = reader.readLine();
            }
            String[] currSplit = curr.split("\\|");
            if (check) {
                currSplit[1] = "X";
            } else {
                currSplit[1] = " ";
            }
            curr = String.join("|", currSplit);
            writer.write(curr);
            while ((curr = reader.readLine()) != null) {
                writer.write(System.lineSeparator());
                writer.write(curr);
            }

            reader.close();
            writer.close();
            alphaTxt.delete();
            temp.renameTo(alphaTxt);
        } catch (IOException e) {
            System.out.println("There was an issue!");
        }
    }


    /**
     * Deletes a task from the file.
     *
     * @param index Index of the task to be deleted.
     */
    // Deletes a task by creating a temp file and copying everything but the deleted task over.
    public void delete(int index) {
        try {
            int temp_index = index;
            File temp = File.createTempFile("file", ".txt", alphaTxt.getParentFile());
            writer = new FileWriter(temp, true);
            reader = new BufferedReader(new FileReader(alphaTxt));
            String curr;
            while ((curr = reader.readLine()) != null) {
                if (temp_index == 1) {
                    temp_index--;
                    continue;
                }
                if (temp_index != index && index != 1) {
                    writer.write(System.lineSeparator());
                }
                writer.write(curr);
                temp_index--;
            }
            reader.close();
            writer.close();
            alphaTxt.delete();
            temp.renameTo(alphaTxt);

        } catch (IOException e) {
            System.out.println("There was an error!");
        }
    }
}
