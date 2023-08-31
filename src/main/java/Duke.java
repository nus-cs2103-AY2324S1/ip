import tasks.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

import tasks.Task;
import tasks.TaskList;

public class Duke {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String logo = "  OOOO                         OOOO\n" +
                " O    O     w           w     O    O\n" +
                " O    O      w   w w   w      O    O\n" +
                " O    O       w w   w w       O    O\n" +
                "  OOOO         w     w         OOOO";

        String welcomeMessage = "────────────────────────────────────\n" +
                "Hello >u<! I'm OwO_bot\n\n" +
                logo + "\n\n" +
                "How can I help ♥w♥ ?\n" +
                "────────────────────────────────────";

        String exitMessage = "────────────────────────────────────\n" +
                "Bye! Hope to see you again soon!\n" +
                "────────────────────────────────────";

        System.out.println(welcomeMessage);

        String userInput = scanner.nextLine();
        String filePath = "data/duke.txt";

        //checking
        File directory = new File("data");
        File file = new File(filePath);


        if (!directory.exists()) {
            directory.mkdir();
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        TaskList taskList = new TaskList(filePath);

        while (!userInput.equals("bye")) {
            System.out.println("────────────────────────────────────\n");
            taskList.inputHandler(userInput);
            userInput = scanner.nextLine();
            System.out.println("────────────────────────────────────");
        }

        writeToDisk(taskList.getTasks());

        System.out.println(exitMessage);
        scanner.close();
    }


    public static void writeToDisk(ArrayList<Task> taskList) {

        String filePath = "data/duke.txt";

        File file = new File(filePath);

        try {
            FileWriter writer = new FileWriter(file, false);
            for (int i = 0; i < taskList.size(); i++) {

                String content = taskList.get(i).toText();
                writer.write(content);
                writer.write("\n");


            }

            writer.close();
            System.out.println("Task list updated.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
