package duke;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Storage {
    private String filepath;
    Storage(String filepath) {
        this.filepath = filepath;
    }

    public void save(ArrayList<Task> allTasks) throws LukeException {
        try {
            FileWriter fw = new FileWriter(filepath);
            String inputTxt = "";

            for (Task task : allTasks) {
                inputTxt += task.toSaveStr() + "\n";
            }

            fw.write(inputTxt.trim());
            fw.close();
        } catch (IOException e) {
            throw new LukeException("Error writing saved tasks into '" + filepath
                    + "'\n\n Save aborted");
        }
    }

    public ArrayList<Task> load() throws LukeException {
        try {
            ArrayList<Task> allTasks = new ArrayList<>();
            File f = new File(filepath);
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String savedData = s.nextLine();
                String[] taskDetails = Arrays.stream(savedData.split("\\|")).map(String::trim).toArray(String[]::new);

                if (taskDetails.length < 2) {
                    throw new LukeException("Unknown data '" + savedData + "'");
                }

                boolean isDone;
                switch (taskDetails[1]) {
                case "Done":
                    isDone = true;
                    break;
                case "Not Done":
                    isDone = false;
                    break;
                default:
                    throw new LukeException("Task neither 'Done' nor 'Not Done'");
                }

                Task createdTask;
                switch (taskDetails[0]) {
                case "T":
                    createdTask = Todo.createTodo(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                    break;
                case "D":
                    createdTask = Deadline.createDeadline(
                            Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                    break;
                case "E":
                    createdTask = Event.createEvent(Arrays.copyOfRange(taskDetails, 2, taskDetails.length), isDone);
                    break;
                default:
                    throw new LukeException("Unknown Task Type '" + taskDetails[0] + "'");
                }
                allTasks.add(createdTask);
            }
            return allTasks;
        } catch (LukeException e) {
            throw new LukeException(
                    "Error reading saved tasks from '" + filepath + "': \n"
                            + e.getMessage() + "\n\n No tasks loaded"
            );
        } catch (FileNotFoundException e) {
            throw new LukeException(
                    "Could not find file '" + filepath + "'\n\n No tasks loaded"
            );
        }
    }
}
