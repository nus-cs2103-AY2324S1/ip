package duke;


import duke.parser.CommandProcessor;

public class Duke {

    private final CommandProcessor commandProcessor;
    private final Ui ui;
    private final InputReceiver inputReceiver;




    public Duke() {
        commandProcessor = new CommandProcessor();
        ui = new Ui();
        inputReceiver = new InputReceiver();
    }



    public void run() {
        ui.greeting();

        while (true) {
            // getting the input command
            String command = inputReceiver.getInput();

            //break out of the loop when it is "bye"
            if (command.equals("bye")) {
                ui.message("Bye!!! Hope to see you again!\n");
                break;
            }

            // process the command
            ui.message(commandProcessor.processCommand(command));


        }

        inputReceiver.closeParser();
    }


    public static void main(String[] args) {
        new Duke().run();
    }





    public String getResponse(String command) {
        return commandProcessor.processCommand(command);
    }
}
