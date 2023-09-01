package pau.util;

/**
 * Deals with interactions with the user.
 */
public class Ui {

    /**
     * Name of the chatbot.
     */
    private final String botName = "\n" +
            "██████╗  █████╗ ██╗   ██╗\n" +
            "██╔══██╗██╔══██╗██║   ██║\n" +
            "██████╔╝███████║██║   ██║\n" +
            "██╔═══╝ ██╔══██║██║   ██║\n" +
            "██║     ██║  ██║╚██████╔╝\n" +
            "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n" +
            "                         \n";

    /**
     * The introduction the chatbot prints everytime it is run.
     */
    public void introduction() {
        System.out.println(" HI! I'm " + botName + "\n" + "ENTERTAIN MEEE");
    }

    /**
     * The goodbye message by the chatbot.
     */
    public void exit() {
        System.out.println("byebyeee come play with me next time");
    }

}
