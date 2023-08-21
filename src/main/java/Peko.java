import java.util.Scanner;

public class Peko {


    private static final int ECHO = 0;
    private static final int EXIT = 1;
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static String currInput;
    private static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String input = "";
        boolean loop = true;
        int responseValue = 0;
        intro();

        while (loop) {
            input = interaction();
            responseValue = getResponseValue(input);
            switch (responseValue) {
                case ECHO:
                    System.out.println(input);
                    System.out.println(lineBreak);
                    break;
                case EXIT:
                    loop = false;
                    break;
                default:
            }
        }
        exit();
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

    public static String interaction() {
        currInput = scanner.next();
        System.out.println(lineBreak);
        return currInput;
    }

    public static int getResponseValue(String input) {
        int output = 0;
        switch (input.toLowerCase()) {
            case "otsupeko":
                output = 1;
        }

        return output;
    }
    public static void exit() {
        System.out.println(exitText);
    }
}
