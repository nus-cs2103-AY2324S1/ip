import java.io.*;
import java.nio.file.Files;
import java.util.Scanner;

public class Peko {


    private static final int ECHO = 0;
    private static final int EXIT = 1;
    private static final int COPYPASTA = Integer.MAX_VALUE;
    private static final int READ = 2;
    private static final int WRITE = 3;
    private static final String lineBreak = "------------------------------------------"; //42
    private static final String introText = "Konpeko, Konpeko, Konpeko! \n" +
            "Usada Pekora-peko! almondo almondo!";
    private static final String exitText = "Otsupeko! Bye bye!";
    private static String currInput;
    private static final Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        String input;
        boolean loop = true;
        int responseValue;
        intro();

        while (loop) {
            input = interaction();
            responseValue = getResponseValue(input);
            switch (responseValue) {
                case ECHO:
                    System.out.println(input);
                    System.out.println(lineBreak);
                    break;
                case READ:
                    try {
                        readList();
                    } catch (FileNotFoundException e) {
                        System.out.println("Missing File Peko! Pain Peko");
                    }
                    break;
                case WRITE:
                    try {
                        addToList(input);
                    } catch (IOException e) {
                        System.out.println("Wakaranai Peko!");
                    }
                    break;
                case COPYPASTA:
                    try  {
                        degen();
                    } catch (FileNotFoundException e) {
                        System.out.println("Hentai!");
                    } finally {
                        loop = false;
                    }
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
                output = EXIT;
                break;
            case "tellmeajoke":
                output = COPYPASTA;
                break;
            case "list":
                output = READ;
                break;
            default:
                output = WRITE;
                break;
        }

        return output;
    }

    public static void readList() throws FileNotFoundException {
        File file = new File("src/main/List.txt");
        Scanner sc = new Scanner(file);
        System.out.println("--------------LIST-PEKO------------------");
        int curr = 1;
        while (sc.hasNextLine()) {
            System.out.println(curr + ". " + sc.nextLine());
            curr++;
        }
    }

    public static void addToList(String s) throws IOException {
        Writer temp;
        temp = new BufferedWriter(new FileWriter("src/main/List.txt", true));
        temp.append("[ ] " + s + "\n");
        temp.close();
        System.out.println("Added: " + s + " Peko!");

    }
    public static void degen() throws FileNotFoundException {
        File text = new File("src/main/Copypasta.txt");
        Scanner sc = new Scanner(text);
        while (sc.hasNextLine()) {
            System.out.println(sc.nextLine());
        }
        System.out.println(lineBreak);

    }
    public static void exit() {
        System.out.println(exitText);
    }
}
