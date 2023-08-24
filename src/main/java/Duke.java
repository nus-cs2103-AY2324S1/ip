import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // makes it such that the scanner takes in inputs from the console

        String openingStr =
                "____________________________________________________________\n" +
                        " Hello! I'm JEOE\n" +
                        " What can I do for you?\n" +
                        "____________________________________________________________\n";
        System.out.println(openingStr);

        String closingStr =
                "____________________________________________________________\n" +
                        " Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
        System.out.println(closingStr);
    }
}
