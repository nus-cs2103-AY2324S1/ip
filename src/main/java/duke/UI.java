package duke;

import java.util.Scanner;

public class UI {
    private final String LOGO = "       ___\n" +
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
    public UI(){}

    public void printIntro() {
        System.out.println("Hello from\n" + LOGO);
        System.out.println("Hello I'm HEAD");
    }
    public String getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("SHOW ME WHAT YOU'VE GOT");
        String input = sc.nextLine();
        return input;
    }
}
