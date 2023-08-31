package duke;

import java.util.Scanner;

class Ui {
    Scanner sc;
    public Ui() {
        this.sc = new Scanner(System.in);
    }
    public void showLoadingError() {
        System.out.println("An error has occurred while attempting" +
                " to load the data file. A new file will be created!");
    }
    public void end() {
        this.sc.close();
    }

}
