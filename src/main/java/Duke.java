import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    static List<String> wordsList = new ArrayList<>();
    static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    static String divider = "    ____________________________________________________________\n";

    static String logo_bird = "    (• >       (• >       (• >       (• >       (• >       (• >\n"
            +  "    /))        /))        /))        /))        /))        /))\n"
            +  "     ``         ``         ``         ``         ``         ``\n"
            + divider;

    static String greet = "    Hello! I'm Birdy\n"
            + "    chirp chirp! How can I help?\n"
            + divider;

    static String parting = divider + "    chirp! See you around!\n"
            + divider;
    public static void echo() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else {
                System.out.println(divider + "    " + input + "\n" + divider);
            }
        }
    }

    public static void list() {
        Scanner sc = new Scanner(System.in);
        while(true) {
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            } else if (input.equals("list")) {
                System.out.println(divider);
                for (int i = 0; i < wordsList.size(); i++) {
                    System.out.println("    " + (i + 1) + ". " + wordsList.get(i));
                }
                System.out.println(divider);
            } else {
                System.out.println(divider + "    added: " + input + "\n" + divider);
                wordsList.add(input);
                System.out.println(wordsList.size());
            }
        }
    }
    public static void main(String[] args) {
        System.out.println("    chirp chirp!\n" + logo_bird + greet);

        list();

        System.out.println(parting);
    }


}
