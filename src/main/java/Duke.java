import java.util.Scanner;
public class Duke {
    Scanner sc;
    public Duke() {
        this.sc = new Scanner(System.in);
    }
    private void line(String text) {
        System.out.println(text);
        System.out.println("------------------------------------------");
    }

    private void greet() {
        System.out.println("------------------------------------------");
        this.line("  Hello! I'm Jokey :) \n  What can I do for you?");
    }

    private boolean isExit(String reply) {
        return reply.startsWith("bye");
    }
    private void exit() {
        this.line("  Bye~ Hope to see you again soon! >w<");
    }

    private void interact() {
        while(true) {
            String reply = sc.nextLine();
            if (isExit(reply)) {
                exit();
                break;
            }
            this.echo(reply);
        }
    }

    private void echo(String reply) {
        this.line("  " + reply);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interact();
    }
}
