package cheems;

import java.util.Scanner;

public class UI {
    public static void showWelcomeMsg() {
        String logo = "\n" +
                "         __                                             \n" +
                "        [  |                                            \n" +
                " .---.   | |--.    .---.   .---.   _ .--..--.    .--.   \n" +
                "/ /'`\\]  | .-. |  / /__\\\\ / /__\\\\ [ `.-. .-. |  ( (`\\]  \n" +
                "| \\__.   | | | |  | \\__., | \\__.,  | | | | | |   `'.'.  \n" +
                "'.___.' [___]|__]  '.__.'  '.__.' [___||__||__] [\\__) ) \n" +
                "                                                        \n";
        System.out.println("Hello from");
        System.out.println(logo);

        String hello = "Heyo I'm cheems.Cheems! Nice to meet you:)" + "\n" + "Want to get some fries on the pier together?";
        printWithFormat(hello);
    }

    public static void showExitMsg() {
        String bye = "Okay bye:( Let's get the fries next time.";
        printWithFormat(bye);
    }

    public static String getInput(Scanner scanner) {
        System.out.println("> You: ");
        return scanner.nextLine();
    }

    public static void printWithFormat(String msg) {
        System.out.println("> cheems.Cheems: ");
        System.out.println(msg);
    }
}
