import Exceptions.DukeException;

import java.util.Scanner;

/**
 * Main class for the Duke application.
 * This class handles user interactions and manages tasks using the Archive class.
 */
public class Duke {

    private static Archive archive;

    public static void main(String[] args) {
        archive = new Archive();
        String logo = "       ___\n" +
                "    . -^   `--,\n" +
                "   /# =========`-_\n" +
                "  /# (--====___====\\\n" +
                " /#   .- --.  . --.|\n" +
                "/##   |  * ) (   * ),\n" +
                "|##   \\    /\\ \\   / |\n" +
                "|###   ---   \\ ---  |\n" +
                "|####      ___)    #|\n" +
                "|######           ##|\n" +
                " \\##### ---------- /\n" +
                "  \\####           (\n" +
                "   `\\###          |\n" +
                "     \\###         |\n" +
                "      \\##        |\n" +
                "       \\###.    .)\n" +
                "        `======/";

        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm HEAD");
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("SHOW ME WHAT YOU'VE GOT");
            String input = sc.nextLine();
            try {
                if (input.equals("GET SCHWIFTY")) {
                    System.out.print("I LIKE WHAT YOU'VE GOT. GOOD JOB.");
                    return;
                } else if (input.equals("list")) {
                    archive.print();
                } else if (input.startsWith("mark")) {
                    archive.markTask(input);
                } else if (input.startsWith("unmark")) {
                    archive.unmarkTask(input);
                } else if (input.startsWith("delete")) {
                    archive.deleteTask(input);
                } else {
                    archive.addTask(input);
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
