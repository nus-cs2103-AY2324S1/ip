package main;

import exception.DukeException;

import java.io.FileNotFoundException;
import java.io.IOException;

import task.TaskList;
import task.ToDo;
import task.Deadline;
import task.Event;
import task.Task;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;


/**
 * Storage class to create objects that manage saving/loading data
 */
public class Storage {

    String filePath;
    TaskList taskList;

    /**
     * Constructor for Storage class.
     *
     * @param filePath File path from which past saved date, if available, should be read from or load to.
     * @param taskList Contains the ArrayList of Task.
     */
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    /**
     * Saves data in the given TaskList object to the file specified in the file path.
     *
     * @param taskList Contains the ArrayList of Task.
     * @throws DukeException Organic exception for Duke.
     */
    public void saveList(TaskList taskList) throws DukeException {
        this.taskList = taskList;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : taskList.getTaskArrayList()) {
                writer.append(task.outputStoreFormat()).append("\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException("IO exception occurred.");
        }
    }

    /**
     * Loads data from the file specified in the file path.
     *
     * @throws DukeException Organic exception for Duke.
     * @throws IOException input/output error when there is an error reading from the data file.
     */
    void loadList() throws DukeException, IOException {

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader); // BufferedReader wraps the fileReader

            String line;
            while ((line = reader.readLine()) != null) {
                String[] inputArray = line.split(" \\| ");

                switch (inputArray[0]) {
                case "T":
                    ToDo toDo = new ToDo(inputArray[2]);
                    int status = Integer.parseInt(inputArray[1]);
                    if (status == 1) {
                        toDo.setTaskDone(true);
                    }
                    this.taskList.addTask(toDo);
                    break;

                case "E":
                    Event event = new Event(inputArray[2], inputArray[3], inputArray[4]);

                    if (inputArray[1] == "1") {
                        event.setTaskDone(true);
                    }
                    this.taskList.addTask(event);
                    break;

                case "D":
                    Deadline deadline = new Deadline(inputArray[2], inputArray[3]);

                    if (inputArray[1] == "1") {
                        deadline.setTaskDone(true);
                    }
                    this.taskList.addTask(deadline);
                    break;

                default:
                    throw new DukeException("An unexpected error occurred while reading the text file. Error Code:" +
                            " 01");
                }
            }

        } catch (FileNotFoundException e) {
            this.saveList(this.taskList);
        } catch (IOException e) {
            throw new DukeException("IO error occurred. Check the formatting of the text file - data.txt.");
        }
    }
}
