package james;

import java.util.Scanner;

public class Ui {

    private Parser parser;

    public static String line = "____________________________________________________________";

    public Ui () {
        this.parser = new Parser();
    }

    public void showLoadingError() {
        System.out.println("Error loading file");
    }

    public void start(TaskList tasks) {
        System.out.println(line + "\n" + "Hello! I'm James\n" + "What can I do for you?\n" + line);

        Scanner in = new Scanner(System.in);

        String input = in.nextLine();
        while (!input.equals("bye")) {
            this.parser.parse(tasks, input);
            System.out.println(line);
            input = in.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!\n" + line);
    }
}
