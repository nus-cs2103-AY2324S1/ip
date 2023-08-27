
public class Duke {


    public static void main(String[] args) {
        Parser parser = new Parser();
        CommandProcessor commandProcessor = new CommandProcessor();
        Ui ui = new Ui();
        ui.greeting();

        while (true) {
            // getting the input command
            String command = parser.getInput();

            //break out of the loop when it is "bye"
            if (command.equals("bye")) {
                ui.bye();
                break;
            }

            ui.setAnswerBorder();
            // process the command
            commandProcessor.processCommand(command);
            ui.setAnswerBorder();


        }

        parser.closeParser();
    }
}
