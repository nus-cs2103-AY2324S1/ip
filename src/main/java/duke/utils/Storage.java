package duke.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import duke.tasks.*;

public class Storage {
    private String filepath;

    public Storage(String filepath) {
        this.filepath = filepath;
    }

    public ArrayList<Task> readFile() throws FileNotFoundException {
        File file = new File(this.filepath);
        Scanner fileScanner = new Scanner(file);
        ArrayList<Task> tasks = new ArrayList<>();
        while (fileScanner.hasNext()) {
            String line = fileScanner.nextLine();
            tasks.add(constructTaskFromFile(line));
        }

        return tasks;
    }


    public void writeToFile(ArrayList<Task> tasks) throws IOException {
        // create data folder if it does not exist
        File file = new File(this.filepath);
//        System.out.println(System.getProperty("user.dir"));
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdir();
        }

        FileWriter fileWriter = new FileWriter(this.filepath);
        for (Task task : tasks) {
            String line = task.toString() + "\n";
            fileWriter.write(line);
        }
        fileWriter.close();
    }


    public Task constructTaskFromFile(String line) {
        String type = line.substring(1, 2);
        String doneString = line.substring(4, 5);
        String text = line.substring(7);

        String description;
        Task newTask = new Task("");

        switch (type) {
            case "T":
                description = text;
                newTask = new ToDo(text);
                break;

            case "D":
                int OpenBracketIndex = text.indexOf("(by: ");
                description = text.substring(0, OpenBracketIndex -1);
                String by = text.substring(OpenBracketIndex + 5, text.length()-1);
                newTask = new Deadline(description, by, true);
                break;

            case "E":
                int fromIndex = text.indexOf("(from: ");
                int toIndex = text.indexOf("to: ");

                description = text.substring(0, fromIndex-1);
                String from = text.substring(fromIndex+7, toIndex-1);
                String to = text.substring(toIndex+4, text.length()-1);
                newTask = new Event(description, from, to, true);
                break;
        }

        boolean done = doneString.equals("X");
        newTask.setDone(done);
        return newTask;
    }
}
