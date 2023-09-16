package Alex;

/**
 * This class represents the bot that manage our task.
 */
public class Alex {

    /**
     * This method you initialize our Alex bot to execute, get input from users, process input and give output to user.
     */
    public void run() {
        Ui.greet();

        while (true) {
            String command = Ui.readUserInput();
            Command c = Parser.parse(command);
            c.execute();
        }
    }

    public static void main(String[] args) {
        new Alex().run();
    }
}
