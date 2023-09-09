package noelPackage.helper;

import java.io.ByteArrayInputStream;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Ui {

    Scanner input;
    Boolean isTest;
    ByteArrayInputStream simulatedInput;

    public Ui() {
        this.input = new Scanner(System.in);
    }


    public String getNextLine() {
        if (this.input.hasNextLine()) {
            return this.input.nextLine();
        } else if (this.isTest) {
            return simulatedInput.toString();
        } else {
            throw new NoSuchElementException("No line found");
        }
    }


    public void showLoadingError() {
        System.out.println("Error reading file!");
    }

}