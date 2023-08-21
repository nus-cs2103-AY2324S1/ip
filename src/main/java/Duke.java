public class Duke {
    public static void display_lines() {
        for (int i = 0; i < 20; i++) {
            System.out.print("- ");
        }
        System.out.println();
    }
    public static void main(String[] args) {
        display_lines();
        String name = "Bruno";
        System.out.println("Woof Woof! I'm " + name + " 🐾");
        System.out.println("How can I help you?");
        display_lines();
        System.out.println("Bye Bye! Hope to see you again soon! 🐶");
        display_lines();
    }
}
