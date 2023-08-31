package pippi;

import pippi.parser.Parser;
import pippi.storage.Storage;
import pippi.task.Task;
import pippi.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;
public class Pippi {
    private static boolean inPokeball = false;

    public static void returnToPokeball() {
        Pippi.inPokeball = true;
    }

    public void start() {
        Ui.wrapText("Hello trainer, I'm Pippi!\nWhat can I do for you?");
        ArrayList<Task> tasks = Storage.read();
        Scanner scanner = new Scanner(System.in);

        while (!this.inPokeball) {
            String userMessage = scanner.nextLine();
            Parser.reply(userMessage, tasks);
        }
    }
    public static void main(String[] args) {
        Pippi pippi = new Pippi();
        pippi.start();
    }
}
