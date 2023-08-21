import java.util.Scanner;

public class UI {
    public void showWelcomeMsg() {
        String logo = "\n" +
                "         __                                             \n" +
                "        [  |                                            \n" +
                " .---.   | |--.    .---.   .---.   _ .--..--.    .--.   \n" +
                "/ /'`\\]  | .-. |  / /__\\\\ / /__\\\\ [ `.-. .-. |  ( (`\\]  \n" +
                "| \\__.   | | | |  | \\__., | \\__.,  | | | | | |   `'.'.  \n" +
                "'.___.' [___]|__]  '.__.'  '.__.' [___||__||__] [\\__) ) \n" +
                "                                                        \n";
        String hello = "Heyo I'm Cheems! Nice to meet you:)" + "\n" + "Want to get some fries on the pier together?";
        System.out.println(logo);
        displayCheems();
        System.out.println(hello);
    }

    public void showExitMsg() {
        String bye = "Okay bye:( Let's get the fries next time.";
        displayCheems();
        System.out.println(bye);
    }

    /*
    May be referenced by other components to display messages.
     */
    public static void displayCheems() {
        System.out.println("> Cheems: ");
    }

    /*
    Only used by UI itself.
     */
    public void displayUser() {
        System.out.println("> You: ");
    }

    public String getInput(Scanner scanner) {
        displayUser();
        return scanner.nextLine();
    }
}
