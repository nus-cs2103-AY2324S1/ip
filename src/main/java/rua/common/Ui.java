package rua.common;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class Ui {
    private final Scanner in;
    private final PrintStream out;

    public Ui() {
        this(System.in, System.out);
    }

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    public void showLine() {
        StringLogger.append("____________________________________________________________");
    }

    public void showWelcome() {
        StringLogger.append("____________________________________________________________\n"
                + " Hello! I'm Rua, your ChatBot\n"
                + " What can I do for you?\n"
                + "____________________________________________________________");
    }

    public void showGoodbye() {
        StringLogger.append(" Bye. Hope to see you again soon!\n");
    }

    public void showMessage(String str) {
       StringLogger.append(str);
    }

    public void showError(String errorMessage) {
        StringLogger.append("You get an error: " + errorMessage + "\n");
    }

    public void showLoadingError() {
        showError("Given tasklist cannot be loaded. Now creating a new tasklist instead.");
    }

    public String readCommand() {
        return in.nextLine();
    }
}
