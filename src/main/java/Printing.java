public class Printing {
    public static void printBlock() {
        System.out.println("████████████████████████████████████████████████");
    }

    public static void print(String str) {
        System.out.println(str);
    }

    public static void greeting() {
        Printing.printBlock();
        System.out.println("Hello I'm Anto\nWhat can I do for you?");
        Printing.printBlock();
    }

    public static void bye() {
        Printing.printBlock();
        System.out.println("Bye. Hope to see you again soon!");
        Printing.printBlock();
    }
}