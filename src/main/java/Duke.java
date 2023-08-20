import java.util.Scanner;
public class Duke {

    private static final String[] taskList = new String[100];
    private static int taskTotal = 0;

    /**
     * Formats chatbot output with borders, first line indentation and new line at the end
     * @param message message to be displayed to user
     */
    public static void output(String message) {
        String line = "    ____________________________________________________________\n";
        System.out.println(line + "     " + message + "\n" + line);
    }

    public static void main(String[] args) {
        Duke.output("Hey its Jyuuni, your helpful assistant\n     How can I help you?");

        Scanner userInput = new Scanner(System.in);
        boolean isRun = true;

        // Waits for input until program stops running
        while (isRun) {
            String command = userInput.nextLine();
            if (command.equals("list")) {
                String line = "    ____________________________________________________________";
                System.out.println(line);
                for (int i = 0; i < taskTotal; i++) {
                    System.out.println("     " + (i + 1) + ". " + taskList[i]);
                }
                System.out.println(line + "\n");
            } else if (command.equals("end")) {
                Duke.output("Come back if you need anything else!");
                isRun = false;
            } else {
                taskList[taskTotal++] = command;
                Duke.output("added: " + command);
            }
        }
    }
}
