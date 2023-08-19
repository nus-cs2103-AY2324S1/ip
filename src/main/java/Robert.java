public class Robert {
    private static void greetUser() {
        String logo = "    ____        __              __ \n"
                + "   / __ \\____  / /_  ___  _____/ /_\n"
                + "  / /_/ / __ \\/ __ \\/ _ \\/ ___/ __/\n"
                + " / _, _/ /_/ / /_/ /  __/ /  / /_  \n"
                + "/_/ |_|\\____/_.___/\\___/_/   \\__/  \n"
                + "\n";

        String text = "Hello! I'm Robert, your Personal Assistant Chatbot.\n"
                + "What can I do for you today?";

        String output = formatOutput(logo + text);
        System.out.println(output);
    }

    private static void exitChatbot() {
        String output = formatOutput("Goodbye. Hope to see you again soon!");
        System.out.println(output);
    }

    private static String formatOutput(String output) {
        final String HORIZONTAL_LINE = "\t____________________________________________________________\n";

        String[] outputLines = output.split("\n");
        for (int i = 0; i < outputLines.length; i++) {
            outputLines[i] = "\t" + outputLines[i] + "\n";
        }
        String indentedOutput = String.join("", outputLines);
        String formattedOutput = indentedOutput + HORIZONTAL_LINE;

        return formattedOutput;
    }

    public static void main(String[] args) {
        greetUser();
        exitChatbot();
    }
}
