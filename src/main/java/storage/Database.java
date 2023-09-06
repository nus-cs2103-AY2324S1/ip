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
     */
    public static void save(ArrayList<Task> list) throws IOException {
        File file = new File("data/tasklist.txt");
        FileWriter fileToWrite = new FileWriter(file);
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
     */
    public static ArrayList<Task> loadData() throws IOException, ArrayIndexOutOfBoundsException {

        File file = new File("data/tasklist.txt");
        ArrayList<Task> list = new ArrayList<>();

        FileReader fileReader = new FileReader(file);
        BufferedReader fileToRead = new BufferedReader(fileReader);
        String nextLine = fileToRead.readLine();

        while (nextLine != null) {
            String[] data = nextLine.split("/");
            String taskType = data[0];
            boolean status = data[1].equals("true");
            if (taskType.equals(Command.TODO.getCommand())) {
                list.add(new ToDo(status, data[2]));
            } else if (taskType.equals(Command.DEADLINE.getCommand())) {
                list.add(new Deadlines(status, data[2], data[3]));
            } else if (taskType.equals(Command.EVENT.getCommand())) {
                list.add(new Events(status, data[2], data[3], data[4]));
            }
            nextLine = fileToRead.readLine();
        }

        fileReader.close();
        fileToRead.close();
        return list;
    }
}
