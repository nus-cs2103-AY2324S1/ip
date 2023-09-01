import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private File folder;
    private File savedFile;
    private final String filePath;
    private ArrayList<Task> taskList = new ArrayList<>();

    public Storage(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Method to return the taskList.
     * @return taskList the task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Method to write tasks frm ArrayList data structure into .txt file.
     * @param taskList the data structure that contains task objects
     */
    public void write(ArrayList<Task> taskList) throws IOException {
        FileWriter writer = new FileWriter(filePath);
        for (Task task : taskList) {
            String res = null;
            if (task instanceof ToDo) {
                res = ((ToDo) task).writtenFormat() + "\n";
            } else if (task instanceof Deadline) {
                res = ((Deadline) task).writtenFormat() + "\n";
            } else if (task instanceof Event) {
                res = ((Event) task).writtenFormat() + "\n";
            }
            writer.write(res);
        }
        writer.close();
    }

    /**
     * Method to read tasks from .txt file to ArrayList data structure.
     */
    public void read() {
        this.folder = new File("./data");
        this.savedFile = new File(folder, "tasks.txt");

        try {
            Scanner sc = new Scanner(savedFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskArr = task.split(" \\| ");
                String type = taskArr[0];
                String status = taskArr[1];
                String description = taskArr[2];
                String date = taskArr[3];

                switch (type) {
                    case "T":
                        ToDo newTodo = new ToDo(description);
                        if (status.equals("1")) {
                            Task todoTask = (Task) newTodo;
                            todoTask.markDone();
                            taskList.add(todoTask);
                        } else {
                            taskList.add(newTodo);
                        }
                        break;
                    case "D":
                        Deadline newDeadline = new Deadline(description + "/by " + date);
                        if (status.equals("1")) {
                            Task deadlineTask = (Task) newDeadline;
                            deadlineTask.markDone();
                            taskList.add(deadlineTask);
                        } else {
                            taskList.add(newDeadline);
                        }
                        break;
                    case "E":
                        String[] parts = date.split("to");
                        Event newEvent = new Event(description + "/from " + parts[0].trim() + "/to " + parts[1].trim());
                        if (status.equals("1")) {
                            Task eventTask = (Task) newEvent;
                            eventTask.markDone();
                            taskList.add(eventTask);
                        } else {
                            taskList.add(newEvent);
                        }
                        break;
                    default:
                        throw new DukeException("tasks.txt is empty");
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file");

            if (!folder.exists()) {
                if (folder.mkdir()) {
                    System.out.println("Folder created: " + folder.getAbsolutePath());
                } else {
                    System.err.println("Failed to create the folder.");
                }
            }
            try {
                if (savedFile.createNewFile()) {
                    System.out.println("File created: " + savedFile.getAbsolutePath());
                } else {
                    System.out.println("File already exists.");
                }
            } catch (IOException exc) {
                System.err.println("An error occurred: " + e.getMessage());
            }

        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
