package duke;

public class Ui {
    private static final String ANSWER_BORDER = "=========================================";
    private static final String BOT_NAME = "RatSpeak";

    public void greeting() {
        System.out.println("Hello from " + BOT_NAME + "\nWhat can I do for you?\n" + ANSWER_BORDER);
    }

    public void bye() {
        System.out.println(ANSWER_BORDER);
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void message(String commandMessage) {
        System.out.println(ANSWER_BORDER);
        System.out.print(commandMessage);
        System.out.println(ANSWER_BORDER);

    }

    public void setAnswerBorder() {
        System.out.println(ANSWER_BORDER);
    }
}
