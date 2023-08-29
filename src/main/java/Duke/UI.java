package Duke;

import java.util.Scanner;

public class UI {
    private String logo = "       ___\n" +
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
        System.out.println("Hello from\n" + logo);
        System.out.println("Hello I'm HEAD");
    }
    public String getInput(){
        Scanner sc = new Scanner(System.in);
        System.out.println("SHOW ME WHAT YOU'VE GOT");
        String input = sc.nextLine();
        return input;
    }
}
