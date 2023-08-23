import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static List<String> archive = new ArrayList<>();
    public static void main(String[] args) {
        String logo = "       ___          \n" +
                "    . -^   `--,      \n" +
                "   /# =========`-_   \n" +
                "  /# (--====___====\\ \n" +
                " /#   .- --.  . --.| \n" +
                "/##   |  * ) (   * ),\n" +
                "|##   \\    /\\ \\   / |\n" +
                "|###   ---   \\ ---  |\n" +
                "|####      ___)    #|\n" +
                "|######           ##|\n" +
                " \\##### ---------- / \n" +
                "  \\####           (  \n" +
                "   `\\###          |  \n" +
                "     \\###         |  \n" +
                "      \\##        |   \n" +
                "       \\###.    .)   \n" +
                "        `======/     ";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm HEAD");
        System.out.println("What can I do for you?");
        System.out.println("Bye. Hope to see you again soon!");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("SHOW ME WHAT YOU'VE GOT");
            String input = sc.nextLine();
            if (input.equals("GET SCHWIFTY")) {
                System.out.println("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                return;
            } else if (input.equals("list")) {
                for (int i = 0; i < archive.size(); i++) {
                    System.out.println(i + ". " + archive.get(i));
                }
            } else {
                System.out.println("added: " + input);
                archive.add(input);
            }
        }

    }
}
