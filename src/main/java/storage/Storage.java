package storage;

import duke.Parser;
import duke.Ui;

import taskutil.Deadline;
import taskutil.Event;
import taskutil.TaskList;
import taskutil.Todo;

import java.util.Scanner;
import java.time.LocalDateTime;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.FileNotFoundException;

public class Storage {

    private static final String FILE_NAME = "TaskData.txt";

    private final String fileDirectory;
    private final String fileLocation;

    public Storage(String fileDirectory) {
        this.fileDirectory = fileDirectory;
        this.fileLocation = fileDirectory + "/" + FILE_NAME;
    }

    /**
     * Creates directory and txt file for storing task data if they do not exist, else does nothing.
     */
    private boolean openFile() {
        File directory = new File(fileDirectory);
        File dataFile = new File(fileDirectory + "/" + FILE_NAME);
        try {
            if (directory.mkdir() && dataFile.createNewFile()) {
                Ui.output("File to store task data have been created and stored at:\n       "
                        + fileDirectory + "/" + FILE_NAME);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Write data from ArrayList of tasks to a file.
     * @param taskList An ArrayList of tasks.
     */
    public void writeToFile(TaskList taskList) {
        try {
            FileWriter file = new FileWriter(fileLocation);
            file.write(taskList.listToStringData());
            file.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Loads task data from txt file to chatbot.
     */
    public boolean loadData(TaskList taskList) {
        if (!this.openFile()) {
            Ui.output("An error occured where the file cannot be read");
            return false;
        }

        try {
            File dataFile = new File(fileLocation);
            Scanner reader = new Scanner(dataFile);

            // Add error checking for wrong data format (probably as long as split works shld be fine)
            // Maybe add command to just delete data file and create new one if can't read
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                String[] splitInput = data.split(" \\| ");
                boolean isDone = splitInput[1].equals("X");

                // Data is in format [type, isDone, title, from/by, to], from/by/to are only present depending on type.
                switch(splitInput[0]) {
                case "T":
                    Todo todo = new Todo(splitInput[2]);
                    todo.changeStatus(isDone);
                    taskList.addTask(todo, false);
                    break;
                case "D":
                    Deadline deadline = new Deadline(splitInput[2], Parser.parseDate(splitInput[3]));
                    deadline.changeStatus(isDone);
                    taskList.addTask(deadline, false);
                    break;
                case "E":
                    LocalDateTime start = Parser.parseDate(splitInput[3]);
                    LocalDateTime end = Parser.parseDate(splitInput[4]);
                    Event event = new Event(splitInput[2], start, end);
                    event.changeStatus(isDone);
                    taskList.addTask(event, false);
                    break;
                }
            }
            return true;
        } catch (ArrayIndexOutOfBoundsException e) { // File formatted with wrong no. of " | " dividers for task types.
            Ui.output("There seems to be a problem with reading in data from:\n      [" + fileLocation
                    + "]\n\n     Proceeding will overwrite the current data file [Y/N]:");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
}
