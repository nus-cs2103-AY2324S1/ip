package pippi;

import pippi.parser.Parser;
import pippi.storage.Storage;
import pippi.task.Task;
import pippi.ui.Ui;

import java.util.Scanner;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

public class Pippi {
    private static boolean inPokeball = false;
    private ArrayList<Task> tasks;

    public static void returnToPokeball() {
        Pippi.inPokeball = true;
    }

    public String getResponse(String userInput) {
        return Parser.reply(userInput, tasks);
    }

    /**
     * Initiates the program's start and working flow
     */
    public void start() {
        Ui.wrapText("Hello trainer, I'm Pippi!\nWhat can I do for you?");
        tasks = Storage.read();
        /*
        Scanner scanner = new Scanner(System.in);

        while (!this.inPokeball) {
            String userMessage = scanner.nextLine();
            Parser.reply(userMessage, tasks);
        }
         */
    }
    public static void main(String[] args) {
        Pippi pippi = new Pippi();
        pippi.start();
    }
}
