import java.util.Scanner;

public class Duke {
    private static final String NAME = "404";
    private static final String SPACE = "     ";

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        printLine();
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                        SPACE, NAME, SPACE);
        System.out.println(greeting);
        printLine();
        System.out.println();
        String commands = sc.nextLine();
        while (!commands.equals("bye")) {
            printLine();
            System.out.printf("%s%s%n", SPACE, commands);
            printLine();
            System.out.println();
            commands = sc.nextLine();
        }
        printLine();
        System.out.printf("%sBye. Hope to see you again soon!%n", SPACE);
        printLine();
        sc.close();
    }

    private static void printLine() {
        String line =  "    ____________________________________________________________";
        System.out.println(line);
    }
}
