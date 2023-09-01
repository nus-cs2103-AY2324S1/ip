package pau.util;

public class Ui {
    private final String botName = "\n" +
            "██████╗  █████╗ ██╗   ██╗\n" +
            "██╔══██╗██╔══██╗██║   ██║\n" +
            "██████╔╝███████║██║   ██║\n" +
            "██╔═══╝ ██╔══██║██║   ██║\n" +
            "██║     ██║  ██║╚██████╔╝\n" +
            "╚═╝     ╚═╝  ╚═╝ ╚═════╝ \n" +
            "                         \n";

    public void introduction() {
        System.out.println(" HI! I'm " + botName + "\n" + "ENTERTAIN MEEE");
    }
    public void exit() {
        System.out.println("byebyeee come play with me next time");
    }

}
