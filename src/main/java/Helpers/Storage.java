package Helpers;

import Exceptions.ErrorStorageException;
import Tasks.Deadline;
import Tasks.Events;
import Tasks.Task;
import Tasks.Todo;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 * Represents Storage class that deals with processing read and write to data storage
 */
public class Storage {
    private final static DateTimeFormatter dateTimeFormat = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private final String path;

    /**
     * Public constructor for Storage which initialize read/write
     *
     * @param path
     * @throws ErrorStorageException
     */
    public Storage(String path) throws ErrorStorageException {

        this.path = path;
        File file = new File(this.path);
        File parentDirectory = file.getParentFile();
        if (!parentDirectory.exists()) {
            parentDirectory.mkdirs();
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new ErrorStorageException(e.getMessage());
            }
        }

    }

    /**
     * A method for reading the text file and processing into a list of tasks
     *
     * @return An arraylist of tasks
     * @throws ErrorStorageException
     */
    public ArrayList<Task> read() throws ErrorStorageException {
        TaskList taskList = new TaskList();
        try {
            File file = new File(this.path);
            FileReader reader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(reader);
            String readLine;

            while ((readLine = bufferedReader.readLine()) != null) {
                //E | 0 | project meeting | 2pm | 4pm
                String[] lines = readLine.split(" \\| ");
                //System.out.println(Arrays.toString(lines));
                String type = lines[0];
                try {
                    switch (type) {
                        case "T":
                            taskList.addTask(new Todo(lines[2], !lines[1].equals("0")));
                            break;
                        case "D":
                            taskList.addTask(new Deadline(lines[2], !lines[1].equals("0"),
                                    LocalDateTime.parse(lines[3], dateTimeFormat)));
                            break;
                        case "E":
                            taskList.addTask(new Events(lines[2], !lines[1].equals("0"),
                                    LocalDateTime.parse(lines[3], dateTimeFormat),
                                    LocalDateTime.parse(lines[4], dateTimeFormat)));
                            break;
                        default:
                            break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    e.getMessage();
                }
            }
            reader.close();
        } catch (IOException ex) {
            throw new ErrorStorageException(ex.getMessage());

        }
        return taskList.getTaskList();
    }

    /**
     * A method for reading the text file and processing into a list of tasks
     *
     * @return An arraylist of tasks
     * @throws IOException
     */
    public void write(ArrayList<Task> taskList) {

        try {
            FileWriter writer = new FileWriter(this.path, false);
            BufferedWriter bufferedWriter = new BufferedWriter(writer);

            for (Task task : taskList) {
                bufferedWriter.write(task.getData());
                bufferedWriter.newLine();
            }

            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}