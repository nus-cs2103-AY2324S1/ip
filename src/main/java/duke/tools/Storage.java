package duke.tools;

import duke.tasks.*;
import duke.exceptions.DukeException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
//    private File folder;
    private File savedFile;
    private final String filePath = "data/tasks.txt";
    private ArrayList<Task> taskList = new ArrayList<>();
    private Parser parser = new Parser();

    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Writes tasks from ArrayList data structure into .txt file.
     *
     * @param taskList the data structure that contains task objects
     */
    public void saveTasks(ArrayList<Task> taskList) {
        for (Task task : taskList) {
            String res = null;
            if (task instanceof ToDo) {
                res = ((ToDo) task).writtenFormat() + "\n";
            } else if (task instanceof Deadline) {
                res = ((Deadline) task).writtenFormat() + "\n";
            } else if (task instanceof Event) {
                res = ((Event) task).writtenFormat() + "\n";
            }

            try {
                FileWriter writer = new FileWriter(filePath);
                writer.write(res);
            } catch (NullPointerException e) {
                System.out.println(("Nothing in task list to write to tasks.txt"));
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * Reads tasks from .txt file to ArrayList data structure.
     */
    public void read() {
        this.savedFile = new File(filePath);
        try {
            Scanner sc = new Scanner(savedFile);
            while (sc.hasNextLine()) {
                String task = sc.nextLine();
                String[] taskArr = this.parser.parseTask(task);
                String type = taskArr[0];
                String status = taskArr[1];
                String description = taskArr[2];
                Task newTask;
                switch (type) {
                case "T":
                    newTask = new ToDo(description);
                    break;
                case "D":
                    String[] deadlineArray = new String[] {description, taskArr[3]};
                    newTask = new Deadline(deadlineArray);
                    break;
                case "E":
                    String[] eventArray = new String[] {description, taskArr[3], taskArr[4]};
                    newTask = new Event(eventArray);
                    break;
                case "C":
                    String[] contactArray = new String[] {description, taskArr[3], taskArr[4]};
                    newTask = new Contact(contactArray);
                    break;
                default:
                    throw new DukeException("You have no tasks yet today!");
                }
                if (status.equals("1")) {
                    newTask.markDone();
                }
                taskList.add(newTask);
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file");
            try {
                if (savedFile.createNewFile()) {
                    System.out.println("File created: " + savedFile.getAbsolutePath());
                }
            } catch (IOException exc) {
                System.err.println(e.getMessage());
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
        }
    }
}
