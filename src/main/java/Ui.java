import java.util.Scanner;

public class Ui {

    private final String GREETING = "Hello! I'm JED, your personal chat-bot!\nWhat can I do for you?";
    private final String GOODBYE = "Bye. Hope to see you again soon!";

    private Scanner sc;

    public Ui() {
        this.sc = new Scanner(System.in);
    }

    public String getInput() {
        return this.sc.nextLine();
    }

    public void talk(String str) {
        String line = "_".repeat(50);
        System.out.println(line);
        System.out.println(str);
        System.out.println(line);
    }

    public void greet() {
        talk(GREETING);
    }

    public void bye() {
        talk(GOODBYE);
        sc.close();
    }
}
