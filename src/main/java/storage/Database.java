package storage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import enums.Command;
import task.Deadlines;
import task.Events;
import task.Task;
import task.ToDo;

/**
 * Class to interface with the text file storing the user task records
 */
public class Database {

    /**
     * Takes in a list of tasks and overwrites it onto the text file
     * @param list list of tasks
     * @throws IOException from file handling errors
     */
    public static void saveTasks(ArrayList<Task> list) throws IOException {
        //Open file and feed it into writer
        File file = getDataFile();
        FileWriter fileToWrite = new FileWriter(file);

        //Write all contents from the local list into file
        Task[] tasks = list.toArray(new Task[0]);
        for (int i = 0; i < tasks.length; i++) {
            fileToWrite.write(tasks[i].dataFormat());
            fileToWrite.write(System.lineSeparator());
        }

        fileToWrite.close();
    }

    /**
     * Retrieves the list of task records from the text file and returns it in an arraylist
     * @return list of tasks saved in the text file
     * @throws IOException from file handling errors
     * @throws ArrayIndexOutOfBoundsException for file data corruption errors
     */
    public static ArrayList<Task> loadTasks() throws IOException, ArrayIndexOutOfBoundsException {
        ArrayList<Task> list = new ArrayList<>();

        //Open the required file and feed it into the reader
        File file = getDataFile();
        FileReader fileReader = new FileReader(file);
        BufferedReader fileToRead = new BufferedReader(fileReader);
        String nextLine = fileToRead.readLine();

        //Read and add all contents into file
        while (nextLine != null) {
            String[] data = nextLine.split("/");
            String taskType = data[0];
            boolean status = data[1].equals("true");
            if (taskType.equals(Command.TODO.toString())) {
                list.add(new ToDo(status, data[2]));
            } else if (taskType.equals(Command.DEADLINE.toString())) {
                list.add(new Deadlines(status, data[2], data[3]));
            } else if (taskType.equals(Command.EVENT.toString())) {
                list.add(new Events(status, data[2], data[3], data[4]));
            }
            nextLine = fileToRead.readLine();
        }

        fileReader.close();
        fileToRead.close();
        return list;
    }

    private static File getDataDirectory() {
        String jarPath = Database.class
                .getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .getPath();
        File jarFile = new File(jarPath);
        File parentDirectory = jarFile.getParentFile();
        File dataDirectory = new File(parentDirectory, "save_data");

        // Check if the directory exists. If not, create it.
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs(); // This will create the directory.
        }
        return dataDirectory;
    }

    private static File getDataFile() {
        File directory = getDataDirectory();
        File file = new File(directory, "tasklist.txt");

        // Check if the file exists. If not, create it.
        if (!file.exists()) {
            try {
                file.createNewFile(); // This will create the file.
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return file;
    }
}
