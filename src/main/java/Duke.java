public class Duke {
    public static void main(String[] args) {
        printLine();
        System.out.println("Hello! I'm FUNNY.\nWhat can I do for you?");
        printLine();
        System.out.println("Bye. Hope to see you again soon!");
    }
    

    public static void printLine() {
        for (int i = 0; i < 80; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
}
