package Others;

import java.util.Scanner;

public class Ui {
    Scanner scanner;
    public Ui () {
        scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console
    }

    public String nextLine() {
        return scanner.nextLine();
    }

    public void showOpenString() {
        String openingStr = "____________________________________________________________\n" +
                " Hello! I'm JEOE\n" +
                " What can I do for you?\n" +
                " type :\n" +
                " list => to list out items in storage\n" +
                " _Anything else_ => store in storage\n" +
                "____________________________________________________________\n";
        System.out.println(openingStr);
    }

    public void showEndString() {
        String endStr = "____________________________________________________________\n" +
                " Bye. Hope to see you again soon!\n" +
                "____________________________________________________________\n";
        System.out.println(endStr);
        scanner.close();
    }

    public void displayReply(String reply) {
        System.out.println("____________________________________________________________\n" +
                reply +
                "____________________________________________________________\n");
    }
}
