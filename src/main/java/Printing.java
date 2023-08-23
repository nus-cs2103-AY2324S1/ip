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

    public static void list() {
        String[] storage = Storage.getStorage();
        int length = Storage.getLength();
        Printing.printBlock();
        for (int i = 0; i < length; i++) {
            int temp = i + 1;
            System.out.println(temp + ". " + storage[i]);
        }
        Printing.printBlock();
    }
}