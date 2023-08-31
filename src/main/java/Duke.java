import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import java.io.File;

public class Duke {

    private static StoreList list = new StoreList();

    private static UserInterface userInterface;
    public static void main(String[] args) {
        try {
            userInterface = new UserInterface(new Storage(), new StoreList());

            String logo = "Nino!";
            System.out.println("Hello, my name is " + logo);
            System.out.println(wrapper("What can I do for you?"));
            userInterface.start();
            userInterface.readCommandLine();
            userInterface.exit();

        } catch (DukeException e) {
            System.out.println(e.toString());
            return;
        }
    }

    private static void reader() {
        Scanner sc = new Scanner(System.in);
        Reading: while (true) {
            String line = sc.nextLine();
            if (line.length() == 0) {
                printer("Err: No command input");
                continue Reading;
            }
            String[] instruction = line.split(" ", 2);
            Commands cmd = Commands.get(instruction[0]);
            boolean hasSecondPart = instruction.length == 2;
            String response;
            switch (cmd) {
                case bye:
                    response = "Goodbye.";
                    sc.close();
                    printer(response);
                    list.writeToFile();
                    break Reading;
                case list:
                    response = list.toString();
                    break;
                case deadline:
                case todo:
                case event:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.add(cmd, instruction[1]);
                    break;
                case mark:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.markDone(instruction[1]);
                    break;
                case unmark:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.markUndone(instruction[1]);
                    break;
                case delete:
                    if (!hasSecondPart) {
                        response = missingDetails(cmd);
                        break;
                    }
                    response = list.delete(instruction[1]);
                    break;
                default:
                    response = "Err: Unknown command - " + instruction[0];
                    break;
            }
            printer(response);
        }
    }

    private static String wrapper(String line) {
        String frame = "=====================";
        return String.format("%s\n%s", line, frame);
    }

    private static void printer(String item) {
        System.out.println(wrapper(item));
    }

    private static String missingDetails(Commands cmd) {
        return "Err: no details given after command - " + cmd.toString();
    }
}
