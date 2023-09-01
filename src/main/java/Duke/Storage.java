package Duke;

import java.io.*;
import java.util.Scanner;

public class Storage {
    public static TaskList load(String filepath, Parser parser) {
        TaskList taskList = new TaskList();
        File file = new File(filepath);
        try {
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String text = scanner.nextLine();
                if (text.equals("")) {
                    continue;
                }
                parser.handleInput(text, taskList, true);
            }
        } catch (FileNotFoundException ex) {
            System.out.println("FileNotFound");
        }
        return taskList;
    }

    public static void save (TaskList taskList) {
            String filepath = "./data/duke.txt";
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(filepath))) {
                writer.write(""); // Clear the file by writing an empty string
                for (int i = 1; i <= taskList.size(); i++) {
                    taskList.getTask(i).save(filepath);
                }
            } catch (IOException e) {
                    e.printStackTrace();
            }
        }
    }
