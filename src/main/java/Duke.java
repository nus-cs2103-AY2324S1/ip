public class Duke {
    public static void main(String[] args) {
        String chatBotName = "RatSpeak";
        Parser parser = new Parser();
        CommandProcessor commandProcessor = new CommandProcessor();
        System.out.println("Hello from " + chatBotName + "\nWhat can I do for you?");

        while (true) {
            String command = parser.getInput();

            if (command.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            commandProcessor.processCommand(command);


        }

        parser.closeParser();
    }
}
