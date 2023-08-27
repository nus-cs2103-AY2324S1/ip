
public class Duke {


    public static void main(String[] args) {
        String chatBotName = "RatSpeak";
        Parser parser = new Parser();
        CommandProcessor commandProcessor = new CommandProcessor();
        System.out.println("Hello from " + chatBotName + "\nWhat can I do for you?");

        while (true) {
            // getting the input command
            String command = parser.getInput();

            //break out of the loop when it is "bye"
            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            // process the command
            commandProcessor.processCommand(command);


        }

        parser.closeParser();
    }
}
