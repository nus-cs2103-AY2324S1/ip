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
        this.line("Hello! I'm Jokey :). \nWhat can I do for you?");
    }

    private void exit() {
        this.line(" Bye~ Hope to see you again soon! >w<");
    }

    private void interpretCommand() {

    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.greet();
        duke.interpretCommand();
        duke.exit();
    }
}
