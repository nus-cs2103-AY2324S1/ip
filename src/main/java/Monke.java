import java.util.Scanner;

public class Monke {
    private static String[] list = new String[100];
    private static int index = 0;

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

    public static void listen() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                Monke.displayList();
                continue;
            }
            Monke.printHorizontalLine();
            Monke.speak("added: " + input);
            Monke.addToList(input);
            Monke.printHorizontalLine();
        }
        Monke.printHorizontalLine();
        sc.close();
    }

    public static void addToList(String item) {
        Monke.list[index] = item;
        Monke.index++;
    }

    public static void displayList() {
        Monke.printHorizontalLine();
        for (int i = 0; i < Monke.index; i++) {
            Monke.speak((i + 1) + ". " + Monke.list[i]);
        }
        Monke.printHorizontalLine();
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        Monke.listen();
        Monke.exit();
    }
}
