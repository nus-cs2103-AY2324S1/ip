import Errors.DukeException;
import Errors.InvalidTaskException;
import Errors.InvalidTaskInput;

import java.util.Scanner;

public class Duke {
    public static void main (String[] args) throws DukeException, InvalidTaskInput, InvalidTaskException {
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
            try {
                    userInput = sc.nextLine();

                    if (userInput.equals("")) {
                        throw new DukeException("OOPS, input field cannot be empty. Try entering a task or a command");
                    }

                    Commands.handleInput(userInput);
                } catch (DukeException error) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println(error);
                    System.out.println("____________________________________________________________\n");
                }
            } while (!userInput.toLowerCase().equals("bye"));

            Commands.sayGoodBye();
        }

}
