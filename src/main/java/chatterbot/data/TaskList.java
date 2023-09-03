package chatterbot.data;

import chatterbot.storage.Storage;

import java.io.FileNotFoundException;
import java.util.ArrayList;

    public class TaskList {

    private static ArrayList<Task> list;

    public TaskList(ArrayList<Task> list) {
        this.list = list;
    }

    public static void initiateTaskList(Storage storage) {
        try {
            storage.copyFileContents("data/ChatterBot.txt");
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        }
    }

    public static String convertToString(ArrayList<Task> list) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Task task : list) {
            stringBuilder.append(task.forFile()).append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

}
