public class Ui {

    private final String LINE = "_______________________________________";
    private final String INDENTATION = "  ";

    public void showWelcome() {
        String logo = "                     _                 _      \n" +
                " _ __ ___  ___ _ __ (_)_ __ ___  _ __ (_)_  __\n" +
                "| '__/ _ \\/ __| '_ \\| | '__/ _ \\| '_ \\| \\ \\/ /\n" +
                "| | |  __/\\__ \\ |_) | | | | (_) | | | | |>  < \n" +
                "|_|  \\___||___/ .__/|_|_|  \\___/|_| |_|_/_/\\_\\\n" +
                "              |_|                             ";
        System.out.println(LINE);
        System.out.println(logo);
        System.out.println(LINE);
        System.out.println("Hello! I'm your personal AI");
        System.out.println("What can I do for you?");
        System.out.println(LINE);
    }

}
