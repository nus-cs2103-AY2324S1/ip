package buddy;

import buddy.utils.BuddyException;
import buddy.utils.Storage;
import buddy.utils.Ui;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;

public class Buddy {
    private static String name = "Task Buddy";
    private static Storage storage;

    public static void main(String[] args) throws BuddyException {

        Scanner scanner = new Scanner(System.in);
        String command;

        TaskList tasks = storage.readFile();

        Ui.printGreeting(name);

        while (true) {
            command = scanner.nextLine();
            if (command.equalsIgnoreCase("bye")) {
                Ui.printFarewell();
                break;
            }
            if (command.equalsIgnoreCase("list")) {
                System.out.print(tasks);
            } else if (command.startsWith("mark") || command.startsWith("unmark")
                    || command.startsWith("delete")) {
                String[] arrOfCmd = command.split(" ");
                Integer taskIndex = Integer.valueOf(arrOfCmd[1]) - 1;

                try {
                    // Task thisTask = tasks.getTask(taskIndex);
                    if (command.startsWith("mark")) {
                        tasks.markAsDone(taskIndex);
                        storage.writeToFile(tasks);
                    }
                    if (command.startsWith("unmark")) {
                        tasks.markAsNotDone(taskIndex);
                        storage.writeToFile(tasks);
                    }
                    if (command.startsWith("delete")) {
                        tasks.deleteTask(taskIndex);
                        storage.writeToFile(tasks);
                    }
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid task number.");
                }

            } else {
                tasks.processCommand(command);
                storage.writeToFile(tasks);
            }
        }
    }
}
