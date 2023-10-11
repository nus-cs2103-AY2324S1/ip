package Alex;

import Alex.commands.Command;
import Alex.commands.Parser;
import Alex.ui.Ui;

/**
 * This class represents the bot that manage our task.
 */
public class Alex {

    public String getResponse(String input) {
        Command c = Parser.parse(input);
        String output = c.execute();
        return output;
    }
    /**
     * This method you initialize our Alex bot in command line to execute, get input from users, process input
     * and give output to user.
     */
    public void run() {
        String greeting = Ui.greet();
        System.out.println(greeting);

        while (true) {
            try {
                String userInput = Ui.readUserInput();
                Command command = Parser.parse(userInput);
                String output = command.execute();
                System.out.println(output);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }


    public static void main(String[] args) {
        new Alex().run();
    }

}
