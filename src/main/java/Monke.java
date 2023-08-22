import java.util.Scanner;

public class Monke {

    public static void speak(String msg) {
        System.out.println("\t" + msg);
    }
    public static void greet() {
        Monke.speak("Hello, I'm Monke. OOGA BOOGA!");
        Monke.speak("What can I do for you?");
        Monke.printHorizontalLine();
    }

    public static void exit() {
        Monke.speak("Bye. Hope to see you again soon! OOGA BOOGA!");
        Monke.printHorizontalLine();
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.next();
            if (input.equals("bye")) {
                break;
            }
            Monke.printHorizontalLine();
            Monke.speak(input);
            Monke.printHorizontalLine();
        }
        Monke.printHorizontalLine();
        sc.close();
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        Monke.echo();
        Monke.exit();
    }
}
