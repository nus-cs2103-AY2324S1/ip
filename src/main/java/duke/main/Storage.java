package duke.main;

import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Storage {
    private String filePath;

    Storage(String filePath) {this.filePath = filePath;};

    public ArrayList<Task> loadTasksFromFile() throws DukeException {
        ArrayList<Task> tasks = new ArrayList<>();
        try {
            File file = new File(filePath);
            if (!file.exists()) {
                file.getParentFile().mkdirs();
                return tasks;
            }
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                String[] taskData = data.split(" \\| ");
                Task task = new Task("");

                //taskData[0] = task type
                //taskData[1] = Done status
                //taskData[2] = description

                boolean isDone = taskData[1].equals("1");

                if (taskData[0].equals("T")) {
                    task = new ToDo(taskData[2]);
                } else if (taskData[0].equals("D")) {
                    task = new Deadline(taskData[2], taskData[3]);
                } else if (taskData[0].equals("E")) {
                    String[] eventData = taskData[3].split(" to ");
                    task = new Event(taskData[2], eventData[0], eventData[1]);
                } else {
                    System.out.println("error: unknown task type found in file.");
                }

                // Set done status
                if (isDone) {
                    task.markDone();
                } else {
                    task.markNotDone();
                }

                tasks.add(task);

            }

            return tasks;
        } catch (IOException e) {
            throw new DukeException("Error loading file: IOException occurred.");
        }
    }

    public void saveTasksToFile(TaskList tasks) throws DukeException {
        try {
            FileWriter fileWriter = new FileWriter(filePath);
            for (Task task: tasks.getAllTasks()) {
                fileWriter.write(task.toFileString() + "\n");
            }
            fileWriter.close();

        } catch (IOException e) {
            throw new DukeException("Error saving file: IOException occurred.");
        }
    }
}
