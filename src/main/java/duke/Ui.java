package duke;

public class Ui {
    private static final String DIVIDER = "___________________________________________________\n";
    private static final String BYE_MESSAGE = "B... b...bye bye!... And ... see you! :))\n";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    public Ui() {
        introduction();
    }

    public String introduction() {
        return DIVIDER + "Hello from\n" + LOGO + "Ko...ko...ko..nichi...wa!!! I... I am Gotoh... Hitori desu!\n"
                + "You... can call me... Bocchi. They usually... call me Bocchi chan...\n"
                + "What can... can I do for you?\n" + DIVIDER;
    }

    public static String line() {
        return DIVIDER;
    }

    public static String sayBye(){
        return BYE_MESSAGE;
    }

    public String showLoadingError() {
        return line() + "The file cannot be loaded!\n" + line();
    }
}
