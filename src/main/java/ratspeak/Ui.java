package ratspeak;

public class Ui {
    private static final String ANSWER_BORDER = "==========================================================";
    private static final String BOT_NAME = "RatSpeak";

    /**
     * print greetings in the command line
     */
    public void greeting(String additionalInformation) {
        System.out.println("Hello from " + BOT_NAME + "\nWhat can I do for you?\n");
        System.out.println(additionalInformation);
        System.out.println(ANSWER_BORDER);
    }

    /**
     * print goodbye in the command line
     */
    public void bye() {
        System.out.println(ANSWER_BORDER);
        System.out.println("Bye. Hope to see you again soon!");
    }

    /**
     * print the message that is created by processing the command given
     * @param commandMessage the command output to the user
     */
    public void message(String commandMessage) {
        System.out.println(ANSWER_BORDER);
        System.out.print(commandMessage);
        System.out.println(ANSWER_BORDER);
    }

}
