package bellcurvegod.parser;

import bellcurvegod.command.ExitCommand;
import bellcurvegod.command.ListTasksCommand;
import bellcurvegod.command.MarkCommand;
import bellcurvegod.command.UnmarkCommand;
import bellcurvegod.exception.EmptyDescriptionException;
import bellcurvegod.exception.InvalidCommandException;
import bellcurvegod.task.Task;
import bellcurvegod.tasklist.TaskList;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    /**
     * Respond to commands entered by the user,
     * and exits when the user types the bellcurvegod.command bye.
     */
    public static void parse() {
        ArrayList<Task> tasks = TaskList.getTaskList();

        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            String[] words = input.split(" ");

            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                ListTasksCommand.run();
            } else if (words[0].equals("mark")) {
                MarkCommand.run(tasks.get(Integer.parseInt(words[1]) - 1));
            } else if (words[0].equals("unmark")) {
                UnmarkCommand.run(tasks.get(Integer.parseInt(words[1]) - 1));
            } else if (words[0].equals("delete")) {
                TaskList.delete(tasks.get(Integer.parseInt(words[1]) - 1));
            } else {
                try {
                    TaskList.addTask(input);
                } catch (InvalidCommandException e) {
                    System.out.println(e.getMessage());
                } catch (EmptyDescriptionException e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        ExitCommand.run();
        sc.close();
    }
}
