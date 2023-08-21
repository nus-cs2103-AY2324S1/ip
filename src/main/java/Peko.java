import java.util.Scanner;

public class Peko {


    private static final int ECHO = 0;
    private static String currInput;
    private static Scanner scanner = new Scanner(System.in);
    private static String lineBreak = "------------------------------------------"; //42
    private static String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Usada Pekora-peko! almondo almondo!";
    private static String exitText = "Otsupeko! Bye bye!";
    public static void main(String[] args) {
        String input = "";
        int responseValue = 0;
        intro();
        input = interaction();

        switch (responseValue) {
            case ECHO:
                System.out.println(input);
            default:
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
    public static void exit() {
        System.out.println(exitText);
    }
}
