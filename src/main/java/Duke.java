import java.util.Scanner;
public class Duke {
    /**
     * Formats chatbot output with borders, first line indentation and new line at the end
     * @param message message to be displayed to user
     */
    public static void output(String message) {
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     " + message + "\n" + line);
    }
    public static void main(String[] args) {
        Duke.output("Hey I'm Jyuuni, your helpful assistant\n     How can I help you?");

        Scanner userInput = new Scanner(System.in);
        boolean isRun = true;

        // Waits for input until program stops running
        while (isRun) {
            String command = userInput.nextLine();
            if (command.equals("end")) {
                Duke.output("Come back if you need anything else!");
                isRun = false;
            } else {
                Duke.output(command);
            }
        }
    }
}
