import java.util.Scanner;

public class Duke {

    public static void runProgram() {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            Parser.parseCommands(input);
            input = sc.nextLine();
        }
        sc.close();
    }

    public static void main(String[] args) {
        Ui.greet();
        runProgram();
        Ui.exit();
    }
}
