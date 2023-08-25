import java.util.Scanner;

public class UI {
    private Scanner scanner;

    public UI() {
        this.scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        String logo =
                "    ,o888888o.    8 8888        8          .8.    8888888 8888888888 8888888 8888888888 `8.`8888.      ,8'\n" +
                        "   8888     `88.  8 8888        8         .888.         8 8888             8 8888        `8.`8888.    ,8'\n" +
                        ",8 8888       `8. 8 8888        8        :88888.        8 8888             8 8888         `8.`8888.  ,8'\n" +
                        "88 8888           8 8888        8       . `88888.       8 8888             8 8888          `8.`8888.,8'\n" +
                        "88 8888           8 8888        8      .8. `88888.      8 8888             8 8888           `8.`88888'\n" +
                        "88 8888           8 8888        8     .8`8. `88888.     8 8888             8 8888            `8. 8888\n" +
                        "88 8888           8 8888888888888    .8' `8. `88888.    8 8888             8 8888             `8 8888\n" +
                        "`8 8888       .8' 8 8888        8   .8'   `8. `88888.   8 8888             8 8888              8 8888\n" +
                        "   8888     ,88'  8 8888        8  .888888888. `88888.  8 8888             8 8888              8 8888\n" +
                        "    `8888888P'    8 8888        8 .8'       `8. `88888. 8 8888             8 8888              8 8888\n";


        System.out.println("------------------------------------------");
        System.out.println("Hi!! I am\n" + logo);
        System.out.println("What brings you here today?");
        System.out.println("------------------------------------------");
    }

    public void showGoodbye() {
        System.out.println("Oh.. bye");
    }

    public void showLoadingError() {
        System.out.println("Error reading from save file.");
    }

    public String readCommand() {
        return this.scanner.nextLine();
    }

    public void showLine() {
        System.out.println("------------------------------------------");
    }

    public void showError(DukeException error) {
        System.out.println(error.getMessage());
    }

    public void showMessage(String message) {
        System.out.println(message);
    }
}
