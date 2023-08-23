import Errors.DukeException;
import Errors.InvalidTaskInput;

import java.util.Scanner;

public class Duke {
    public static void main (String[] args) throws DukeException, InvalidTaskInput {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Commands.sayHello();

        Scanner sc = new Scanner(System.in);
        String userInput = "";
        do {
            userInput = sc.nextLine();

            if (userInput.equals("")) {
                throw new DukeException("OOPS, input field cannot be empty. Try entering a task or a command");
            }

            Commands.handleInput(userInput);
        } while (!userInput.toLowerCase().equals("bye"));

        Commands.sayGoodBye();
    }
}
