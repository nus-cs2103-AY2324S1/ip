public class Ui {
    private static final String ANSWER_BORDER = "=====================================";
    private static final String BOT_NAME = "RatSpeak";

    void greeting() {
        System.out.println("Hello from " + BOT_NAME + "\nWhat can I do for you?\n" + ANSWER_BORDER);
    }

    void bye() {
        System.out.println(ANSWER_BORDER);
        System.out.println("Bye. Hope to see you again soon!");
    }

    void setAnswerBorder() {
        System.out.println(ANSWER_BORDER);
    }
}
