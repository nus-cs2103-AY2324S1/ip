package io;
import java.util.Scanner;

/**
 * UI handles input and output
 */
public class Ui {
    private static String lineBreak = "____________________________________________________________";
    private static String logo = "\r\n"
            + "__________               __                          \r\n"
            + "\\______   \\ ____   ____ |  | __ _____ _____    ____  \r\n"
            + " |       _//  _ \\_/ ___\\|  |/ //     \\\\__  \\  /    \\ \r\n"
            + " |    |   (  <_> )  \\___|    <|  Y Y  \\/ __ \\|   |  \\\r\n"
            + " |____|_  /\\____/ \\___  >__|_ \\__|_|  (____  /___|  /\r\n"
            + "        \\/            \\/     \\/     \\/     \\/     \\/ \r\n";
    private Scanner scanner;
    public Ui() {
        this.scanner = new Scanner(System.in);
    }

    public void say(String words) {
        System.out.println("\t" + words);
    }
    public void respond(String words) {
        String response = words + "\n" + lineBreak;
        say(response.replaceAll("\n", "\n\t"));
    }
    public void startup() {
        say(logo);
        respond("Startup successful!");
    }
    public void close() {
        scanner.close();
        respond("Client closed");
    }
    public String getInput() {
        String input = this.scanner.nextLine();
        return input;
    }
}
