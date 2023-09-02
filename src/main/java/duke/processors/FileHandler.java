package duke.processors;

import duke.task.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class FileHandler {
    private final String path = "./data/duke.txt";

    public void fileCreate() {
        try {
            File file = new File(path);
            if (file.createNewFile()) {
                System.out.println("A new schedule is created!");
            } else {
                System.out.println("There is an existing schedule!");
            }
        } catch (IOException e) {
            System.out.println("Cannot create the file");
        }
    }

    public void writeFile(String msg) {
        try {
            FileWriter fileWriter = new FileWriter(path, true);
            fileWriter.write(msg);
            fileWriter.write(System.lineSeparator());
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot write in this File");
        }
    }

    public void updateFile(String oldLine, String newLine) {
        try {
            Scanner sc = new Scanner(new File(path));
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
            FileWriter fileWriter = new FileWriter(path);
            fileWriter.write(content);
            fileWriter.close();
        } catch (IOException e) {
            System.out.println("Cannot update the File");
        }
    }

    public void DeleteLine(String toDelete) {
        updateFile(toDelete, "");
    }

    public void readFile(ArrayList<Task> arrayList) {
        try {
            File file = new File(path);
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
