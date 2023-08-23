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
        Task[] storage = Storage.getStorage();
        int length = Storage.getLength();
        Printing.printBlock();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < length; i++) {
            int temp = i + 1;
            System.out.println(String.format("%d. [%s] %s",
                    temp, storage[i].getStatusIcon(), storage[i].description));
        }
        Printing.printBlock();
    }

    public static void printMarkAsDone(int index) {
        Task[] storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(String.format("[%s] %s",
                storage[index].getStatusIcon(), storage[index].description));
        Printing.printBlock();
    }

    public static void printUnmark(int index) {
        Task[] storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(String.format("[%s] %s",
                storage[index].getStatusIcon(), storage[index].description));
        Printing.printBlock();
    }
}