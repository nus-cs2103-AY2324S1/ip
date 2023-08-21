import java.util.Scanner;

public class Duke {
    private static final String NAME = "404";
    private static final String SPACE = "     ";
    private static final int STORE_SIZE = 100;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String[] texts = new String[STORE_SIZE];
        int text_pointer = 0;
        printLine();
        String greeting = String.format("%sHello! I'm %s%n%sWhat can I do for you?",
                                        SPACE, NAME, SPACE);
        System.out.println(greeting);
        printLine();
        System.out.println();
        String commands = sc.nextLine();
        while (!commands.equals("bye")) {
            printLine();
            if (!commands.equals("list")) {
                System.out.printf("%sadded: %s%n", SPACE, commands);
                texts[text_pointer] = commands;
                text_pointer++;
            } else {
                for (int i = 0; i < text_pointer; i++) {
                    if (texts[i] == null) {
                        break;
                    } else {
                        String out = String.format("%s%d. %s", SPACE, i + 1, texts[i]);
                        System.out.println(out);
                    }
                }
                if (text_pointer == 0) {
                    System.out.printf("%sSorry, there is nothing to list!%n", SPACE);
                }
            }
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
