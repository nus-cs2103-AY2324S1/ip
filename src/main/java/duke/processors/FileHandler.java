package duke.processors;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.ToDo;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * A class meant for creating, writing, and updating files.
 */
public class FileHandler {
    private final String PATH = "./data/duke.txt";

    /**
     * check if there is an existing file, if not create a file.
     */
    public void fileCreate() {
        try {
            File file = new File(PATH);
            if (file.createNewFile()) {
                System.out.println("A new schedule is created!");
            } else {
                System.out.println("There is an existing schedule!");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file");
        }
    }

    /**
     * Write the given message to the txt file
     * @param msg the info to be written
     */
    public void writeFile(String msg) {
        try {
            FileWriter fileWriter = new FileWriter(PATH, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write in this File");
        }
    }

    /**
     * Update the line in txt file
     * @param oldLine the line to be updated
     * @param newLine the line used to replace the oldline
     */
    public void updateFile(String oldLine, String newLine) {
        try {
            Scanner sc = new Scanner(new File(PATH));
            StringBuilder stringBuilder = new StringBuilder();
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                if (newLine.isEmpty() && line.equals(oldLine)) {
                    continue;
                }
                stringBuilder.append(line).append(System.lineSeparator());
            }
            String content = stringBuilder.toString();
            if (!newLine.isEmpty()) {
                content = content.replace(oldLine, newLine);
            }
            FileWriter fileWriter = new FileWriter(PATH);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot update the File");
        }
    }

    /**
     * Delete the task in txt file
     * @param toDelete the task to be deleted
     */
    public void DeleteLine(String toDelete) {
        updateFile(toDelete, "");
    }

    /**
     * read the tasks from the txt file and
     * store it in to the tasklist
     * @param arrayList the list to store tasks
     */
    public void readFile(ArrayList<Task> arrayList) {
        try {
            File file = new File(PATH);
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                String data = reader.nextLine();
                Task task;
                boolean isDone = data.charAt(4) == 'X';
                String content = data.substring(7);
                switch (data.substring(1, 2)) {
                case "T" :
                    task = new ToDo(content, isDone);
                    break;
                case "D" :
                    task = new Deadline(content, isDone);
                    break; 
                case "E" :
                    task = new Event(content, isDone);
                    break;
                default:
                    System.out.println(
                            "The content of this task is in the wrong format: "
                                    + content);
                    continue;
                }
                arrayList.add(task);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Cannot find the file");
        }
    }

}
