package pau.ui;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Name of the chatbot.
     */
    private final String botName = "\n"
            + "██████╗  █████╗ ██╗   ██╗\n"
            + "██╔══██╗██╔══██╗██║   ██║\n"
            + "██████╔╝███████║██║   ██║\n"
            + "██╔═══╝ ██╔══██║██║   ██║\n"
            + "██║     ██║  ██║╚██████╔╝\n"
            + "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n"
            + "                         \n";

    /**
     * The introduction the chatbot prints everytime it is run.
     */
    public String introduction() {
        return " HI! I'm " + botName + "\n";
    }

    /**
     * The goodbye message by the chatbot.
     */
    public String exit() {
        return "byebyeee come play with me next time";
    }

}
