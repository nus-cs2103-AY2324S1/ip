public class Ui {
    private static final String DIVIDER = "____________________________________________________________";
    private static final String BYE_MESSAGE = "B... b...bye bye!... And ... see you! :))";
    private static final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    public Ui() {
        introduction();
    }
    public void introduction() {
        System.out.println(DIVIDER);
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Ko...ko...ko..nichi...wa!!! I... I am Gotoh... Hitori desu! Σ(っ °Д °;)っ");
        System.out.println("You... can call me... Bocchi. They usually... call me Bocchi chan...");
        System.out.println("What can... can I do for you? (°□°;) ");
        System.out.println(DIVIDER);
    }
    public static void line() {
        System.out.println(DIVIDER);
    }
    public static void sayBye(){
        System.out.println(BYE_MESSAGE);
    }
    public void showLoadingError() {
        line();
        System.out.println("The file cannot be loaded!");
        line();
    }
}
