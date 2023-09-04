package noel;

import java.util.Scanner;

public class Ui {

    Scanner input;

    public Ui() {
        this.input = new Scanner(System.in);
    }

    public String getNextLine() {
        return this.input.nextLine();
    }

    public void showLoadingError() {
        System.out.println("Error reading file!");
    }

}