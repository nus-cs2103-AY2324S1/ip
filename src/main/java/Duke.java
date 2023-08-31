import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static TaskList taskList = new TaskList();
    private static File taskListFile = null;

    public static void main(String[] args) {
        // Greeting
        Ui.greet();

        // Initialise file
        boolean isSuccessful = Storage.initialiseTaskListFile(taskList) == 1;
        if (!isSuccessful) {
            // No file found / created
            return;
        }

        // Get input and store it
        Scanner in = Ui.initialiseUi();

        while (true) {
            String s = Ui.getInput(in);

            try {
                boolean isParsed = Parser.parseInput(s, taskList) == 1;
                if (!isParsed) {
                    break;
                }

                Storage.updateTaskListFile(taskList);
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
