import java.io.*;
import java.util.Scanner;

public class Peko {
    private static int pos;
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Hololive san kisei no\n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static SaveHandler saveHandler;
    public static void main(String[] args) {
        UserInputHandler UIhandler;
        intro();
        while (true) {
            UIhandler = new UserInputHandler();
            UIhandler.newInput();
            if (!UIhandler.run()) {
                break;
            }
        }
        System.out.println(exitText);
    }

    public static void intro() {
        String pekoLogo = " _____      _\n"
                + "|     |___ | | ______\n"
                + "|  ___/ _ \\| |/ /    \\\n"
                + "| |  <  __/|   <  <>  |\n"
                + "|_|   \\___||_|\\_\\____/";
        System.out.println(pekoLogo);

        System.out.println(lineBreak);
        System.out.println(introText);
        System.out.println(lineBreak);
    }
}
