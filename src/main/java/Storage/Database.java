package Storage;

import Enums.Command;
import Task.Task;
import Task.Deadlines;
import Task.ToDo;
import Task.Events;
import Ui.Reply;

import java.io.*;
import java.util.ArrayList;

public class Database {
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
