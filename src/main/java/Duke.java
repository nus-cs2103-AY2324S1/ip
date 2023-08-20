import java.util.Scanner;

public class Duke {

    String name = "Meg";
    boolean isRunning = true;
    public Duke() {
        String intro1 = String.format("I'm %s. You called me?" +
                "\n", this.name);
        String intro2 = "Make it quick, thanks.";
        System.out.println(intro1);
        System.out.println(intro2 + "\n");
        printHorizontalLine();
        Scanner sc = new Scanner(System.in);
        while (this.isRunning) {
            String msg = sc.nextLine();
            echo(msg);
        }
        exit();
    }

    private void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }

    private void exit() {
        System.out.println("Don't let me see you again!");
        printHorizontalLine();
        System.exit(0);
    }

    private void echo(String message) {
        if (message.equals("bye")) {
            this.isRunning = false;
        } else {
            System.out.println(message + "\n");
            printHorizontalLine();
        }
    }

    public static void main(String[] args) {
        new Duke();
    }
}
