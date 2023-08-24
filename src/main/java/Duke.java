public class Duke {
    public static void main(String[] args) {
        greet();
        exit();
    }

    public static void greet() {
        lines();
        System.out.println("Hello! I'm Max");
        System.out.println("What can I do for you?");
        lines();
    }

    public static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
        lines();
    }

    public static void lines() {
        System.out.println("");
        for (int i = 0; i < 50; i++) {
            System.out.print("-");
        }
        System.out.println("");
    }
}
