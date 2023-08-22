import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontal = "____________________________________________________________";

        Bob bob = new Bob();
        bob.greet();

        while (true) {
            String line = sc.nextLine();
            String[] words = line.split(" ");
            String firstWord = words[0];

            if (firstWord.equalsIgnoreCase("bye")) {
                break;
            } else if (firstWord.equalsIgnoreCase("list")) {
                bob.listOut();
            } else if (words.length > 1 && firstWord.equalsIgnoreCase("mark")) {
                int index = Integer.parseInt(words[1]);
                bob.markTask(index - 1);
            } else if (words.length > 1 && firstWord.equalsIgnoreCase("unmark")) {
                int index = Integer.parseInt(words[1]);
                bob.unmarkTask(index - 1);
            } else {
                bob.addTask(line);
            }
        }

        bob.bye();
        sc.close();
    }
}

