public class Duke {
    public static void main(String[] args) {
        printIntro();
        printEnd();
    }

    private static void printIntro() {
        printLine();
        System.out.println("Hello! I'm Roe!\n" + "What can I do for you?\n");
    }

    private static void printEnd() {
        printLine();
        System.out.println("Bye. Hope to see you again soon!\n");
        printLine();
    }

    private static void printLine() {
        int charCount = 50;
        for (int i = 0; i < charCount; i++) {
            System.out.print("─");
        }
        System.out.print("\n");
    }
}
