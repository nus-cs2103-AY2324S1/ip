package io;

import java.util.Scanner;
/**
 * Used to handle input
 * and output produced by bot.
 * 
 * @author Alvis Ng (supermii2)
 */
public class Ui {
    /** Line separator to separate input and output */
    private static String lineBreak = "____________________________________________________________";
    /** Logo used on startup */
    private static String logo = "\r\n"
            + "__________               __                          \r\n"
            + "\\______   \\ ____   ____ |  | __ _____ _____    ____  \r\n"
            + " |       _//  _ \\_/ ___\\|  |/ //     \\\\__  \\  /    \\ \r\n"
            + " |    |   (  <_> )  \\___|    <|  Y Y  \\/ __ \\|   |  \\\r\n"
            + " |____|_  /\\____/ \\___  >__|_ \\__|_|  (____  /___|  /\r\n"
            + "        \\/            \\/     \\/     \\/     \\/     \\/ \r\n";
    /** Scanner object for input and output */
    private Scanner scanner;
    /**
     * Constructor used to
     * create UI object
     */
    public Ui() {
        this.scanner = new Scanner(System.in);
    }
    /**
     * Say a string without a
     * line separator
     * @param words
     */
    public void say(String words) {
        System.out.println("\t" + words);
    }
    /**
     * Say a string with a
     * line separator
     * @param words
     */
    public void respond(String words) {
        String response = words + "\n" + lineBreak;
        say(response.replaceAll("\n", "\n\t"));
    }
    /**
     * Set of methods to run
     * on startup of the bot
     */
    public void startup() {
        say(logo);
        respond("Startup successful!");
    }
    /**
     * Set of methods to run 
     * when the bot closing the bot
     */
    public void close() {
        scanner.close();
        respond("Client closed");
    }
    /**
     * Used to handle user input
     * @return String typed by user.
     */
    public String getInput() {
        String input = this.scanner.nextLine();
        return input;
    }
}
