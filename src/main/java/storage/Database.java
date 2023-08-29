package storage;

import enums.Command;
import task.Task;
import task.Deadlines;
import task.ToDo;
import task.Events;
import ui.Reply;

import java.io.*;
import java.util.ArrayList;

/**
 * Class to interface with the text file storing the user task records
 */
public class Database {
    /**
     * Takes in a list of tasks and overwrites it onto the text file
     * @param list list of tasks
     */
    public static void save(ArrayList<Task> list) {
        File file = new File("data/tasklist.txt");
        try {
            FileWriter fileToWrite = new FileWriter(file);
            Task[] tasks = list.toArray(new Task[0]);

            for (int i = 0; i < tasks.length; i++) {
                fileToWrite.write(tasks[i].dataFormat());
                fileToWrite.write(System.lineSeparator());
            }

            fileToWrite.close();
        } catch (IOException e) {
            Reply reply = Reply.init();
            reply.printDialog("Oops! Sorry! There is an issue with the file database. " +
                    "You are required to delete the file and recreate one with the same name");
        }
    }

    /**
     * Retrieves the list of task records from the text file and returns it in an arraylist
     * @return list of tasks saved in the text file
     */
    public static ArrayList<Task> loadData() {

        File file = new File("data/tasklist.txt");
        ArrayList<Task> list = new ArrayList<>();

        try {
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

        } catch (IOException e) {
            Reply reply = Reply.init();
            reply.printDialog("Oops! Sorry! There is an issue with the file database. " +
                    "You are required to delete the file and recreate one with the same name.");
        } catch (ArrayIndexOutOfBoundsException e) {
            Reply reply = Reply.init();
            reply.printDialog("Oops! Sorry! There is an issue with the data formatting in the database.");
        }
        return list;
    }
}
