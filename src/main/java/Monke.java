public class Monke {
    public static void greet() {
        System.out.println("Hello, I'm Monke.");
        System.out.println("What can I do for you?");
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void printHorizontalLine() {
        for (int i = 0; i < 100; i++) {
            System.out.print("_");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Monke.printHorizontalLine();
        Monke.greet();
        Monke.printHorizontalLine();
        Monke.bye();
    }
}
