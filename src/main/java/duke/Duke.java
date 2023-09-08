package duke;

import duke.assets.parser.Parser;
import duke.dukeexceptions.CorruptDataException;
import duke.assets.storage.TaskList;
import duke.assets.storage.Storage;

import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Duke {
    static final String logo = "\n   _____ _    _          _____   _____ _____ _______ \n" +
            "  / ____| |  | |   /\\   |  __ \\ / ____|  __ \\__   __|\n" +
            " | |    | |__| |  /  \\  | |  | | |  __| |__) | | |   \n" +
            " | |    |  __  | / /\\ \\ | |  | | | |_ |  ___/  | |   \n" +
            " | |____| |  | |/ ____ \\| |__| | |__| | |      | |   \n" +
            "  \\_____|_|  |_/_/    \\_\\_____/ \\_____|_|      |_|   \n";
    static final String horizontal = "----------------------------------------------------------------------------" +
            "-----------";
    static final Storage storage = new Storage();

    public static void main(String[] args) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println(horizontal + logo + horizontal);

        while (!storage.readFromFile()) {
            System.out.print("User: ");
            String nextLine = sc.nextLine();
            if (nextLine.equals("exit")) {
                sc.close();
                System.exit(0);
            } else if (nextLine.equals("\n")) {
                continue;
            }
        }

        System.out.println("ChadGPT: Welcome to ChadGPT, What would you like to do today?\n" + horizontal);
        System.out.print("User: ");
        while (!sc.hasNext("bye")) {
            String nextLine = sc.nextLine();
            storage.passUserCommand(nextLine);
            System.out.print(horizontal + "\nUser: ");
        }
        sc.close();

        storage.writeToFile();
        System.out.print("ChadGPT: Bye. Hope to see you again soon!\n" + horizontal);
    }
}