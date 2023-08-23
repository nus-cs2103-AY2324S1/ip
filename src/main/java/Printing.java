public class Printing {
    public static void printBlock() {
        System.out.println("===---===---===---===---===---===---===---===");
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
            System.out.println(String.format("%d. %s",
                    temp, storage[i]));
        }
        Printing.printBlock();
    }

    public static void printMarkAsDone(int index) {
        Task[] storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(storage[index]);
        Printing.printBlock();
    }

    public static void printUnmark(int index) {
        Task[] storage = Storage.getStorage();
        Printing.printBlock();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(storage[index]);
        Printing.printBlock();
    }

    public static void printAdded(Task task) {
        Printing.printBlock();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks in the list.", Storage.getLength()));
        Printing.printBlock();
    }
}