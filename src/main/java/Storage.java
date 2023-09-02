import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import java.io.BufferedReader;
import java.io.FileReader;

import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    String filePath;
    TaskList taskList;
    public Storage(String filePath, TaskList taskList) {
        this.filePath = filePath;
        this.taskList = taskList;
    }

    void saveList(TaskList taskList) throws DukeException {
        this.taskList = taskList;
        try {
            File file = new File(filePath);
            file.getParentFile().mkdirs();

            BufferedWriter writer = new BufferedWriter(new FileWriter(file));

            for (Task task : taskList.getTaskArrayList()) {
                writer.append(task.storeFormat()).append("\n");
            }

            writer.close();
        } catch (IOException e) {
            throw new DukeException("IO exception occurred.");
        }
    }

    void loadList() throws DukeException, IOException {

        try {
            File file = new File(filePath);
            FileReader fileReader = new FileReader(file);
            BufferedReader reader = new BufferedReader(fileReader); // BufferedReader wraps the fileReader

            String line;
            while ((line = reader.readLine()) != null) {
                String[] inputArray = line.split(" \\| ");

                switch(inputArray[0]) {
                    case "T":
                        ToDo toDo = new ToDo(inputArray[2]);
                        if (inputArray[1] == "1") {
                            toDo.taskDone(true);
                        }
                        this.taskList.addTask(toDo);
                        break;

                    case "E":
                        Event event = new Event(inputArray[2], inputArray[3], inputArray[4]);

                        if (inputArray[1] == "1") {
                            event.taskDone(true);
                        }
                        this.taskList.addTask(event);
                        break;

                    case "D":
                        Deadline deadline = new Deadline(inputArray[2], inputArray[3]);

                        if (inputArray[1] == "1") {
                            deadline.taskDone(true);
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
