public class Duke {


    public static void printHorizontalLine() {

        for (int i = 0; i < 50; i++) {
            System.out.print("_");
        }
        System.out.println("");
    }

    public static void main(String[] args) {
        String name = "Donk";
        printHorizontalLine();
        System.out.println("Hello! I'm " + name);
        System.out.println("What can I do for you?");
        printHorizontalLine();

        System.out.println("Bye. Hope to see you soon again soon!");
        printHorizontalLine();

    }
}
