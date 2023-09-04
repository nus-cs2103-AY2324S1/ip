package duke;

public class Ui {
    private String greeting;
    private String exitGreeting;
    private String horizontalBar;
    private String logo;

    public Ui() {
        this.greeting = "Hello, I'm Capt. Price! What can I do for you today, Sergeant?";
        this.exitGreeting = "Over and out. See you next mission!";
        this.horizontalBar = "-------------------------------------------------";
        this.logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
    }

    public Ui(String greeting, String exitGreeting, String horizontalBar, String logo) {
        this.greeting = greeting;
        this.exitGreeting = exitGreeting;
        this.horizontalBar = horizontalBar;
        this.logo = logo;
    }

    private String botMessage(String message) {
        String space = "    ";
        return space + this.horizontalBar + "\n" + space + message + "\n" + space + this.horizontalBar;
    }
    public void displayGreeting() {
        System.out.println(this.botMessage(this.greeting));
    }

    public void displayExitGreeting() {
        System.out.println(this.botMessage(this.exitGreeting));
    }

    public void displayMessage(String message) {
        System.out.println(botMessage(message));
    }
}
