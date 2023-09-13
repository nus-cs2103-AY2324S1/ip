package duke.tools;

import duke.tasks.Task;
import duke.tasks.ToDo;
import duke.tasks.Deadline;
import duke.tasks.Event;
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

    /**
    public Storage(String filePath) {
        this.filePath = filePath;
    }
     */

    /**
     * Method to return the taskList.
     *
     * @return taskList the task list.
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Method to write tasks frm ArrayList data structure into .txt file.
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
     * Method to read tasks from .txt file to ArrayList data structure.
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
                    String deadline = taskArr[3];

                    Deadline newDeadline = new Deadline(description + "/by " + deadline);

                    if (status.equals("1")) {
                        Task deadlineTask = (Task) newDeadline;
                        deadlineTask.markDone();
                        taskList.add(deadlineTask);
                    } else {
                        taskList.add(newDeadline);
                    }
                    break;
                case "E":
                    String date = taskArr[3];

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
                    throw new DukeException("You have no tasks yet today!");
            }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No such file");

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
