package phi;

public class Ui {
    public void greeting() {
        String logo = " ___ _  _ ___\n" +
                "| _ \\ || |_ _|\n" +
                "|  _/ __ || | \n" +
                "|_| |_||_|___| \n";
        String greetingMsg = "Hellos! I'm PHI (Programmed Human Interaction)\nWhat can I do for you?\n";

        System.out.println(logo + greetingMsg);
    }

    public void goodbye() {
        String exitMsg = "okk THANKS FOR COMING BYE!";
        System.out.println(exitMsg);
    }
}
