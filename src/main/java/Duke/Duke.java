package Duke;

import java.io.IOException;
import java.util.Scanner;

public class Duke {
    private Commands taskManager;
    public Storage dataBase;
    private String filePath = "./tasklist.txt";

    public Duke() throws IOException {
        this.dataBase = new Storage(filePath);
        this.taskManager = new Commands(dataBase.load());
    }


    public static void main (String[] args) throws DukeException, IOException {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke bot = new Duke();
        bot.taskManager.sayHello();

        Scanner sc = new Scanner(System.in);
        String userInput = "";

        do {
            try {
                    userInput = sc.nextLine();

                    if (userInput.equals("")) {
                        throw new DukeException("OOPS, input field cannot be empty. Try entering a task or a command");
                    }

                    bot.taskManager.handleInput(userInput);
                } catch (DukeException error) {
                    System.out.println("____________________________________________________________\n");
                    System.out.println(error);
                    System.out.println("____________________________________________________________\n");
                }
            } while (!userInput.toLowerCase().equals("bye"));

            bot.dataBase.save(bot.taskManager.tasks);
            bot.taskManager.sayGoodBye();
        }

}
