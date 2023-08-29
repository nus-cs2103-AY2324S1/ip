package duke;

public class Ui {

    private static final String LINE_SEPARATOR = "____________________________________________________________";
    private static final String GREETING_MESSAGE = LINE_SEPARATOR + "\n"
            + " Hello! I'm IRIS\n"
            + " What can I do for you?\n"
            + LINE_SEPARATOR;
    private static final String GOODBYE_MESSAGE = LINE_SEPARATOR + "\n"
            + "Bye. Hope to see you again soon!\n"
            + LINE_SEPARATOR;

    public void printIntroMsg() {
        System.out.println(GREETING_MESSAGE);
    }

    public void printOutroMsg() {
        System.out.println(GOODBYE_MESSAGE);
    }

    public void printSeparator() {
        System.out.println(LINE_SEPARATOR);
    }

}
