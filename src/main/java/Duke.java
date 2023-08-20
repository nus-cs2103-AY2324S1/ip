public class Duke {

    public static void main(String[] args) {
        Duke.botPrintLine("");
        Duke.botPrintLine("Greetings user, I'm Didier. How can I help you?");

        Duke.botPrintLine("Goodbye! If you need help in the future, don't hesitate to ask me.");
    }

    /**
     * Prints the message to the standard output, formatted in a specific way emulate the bot.
     *
     * @param message The message to be printed.
     */
    private static void botPrintLine(String message) {
        String lineBreak = "---------------------------------------------------------------------";
        System.out.println(message + "\n" + lineBreak);
    }
}
