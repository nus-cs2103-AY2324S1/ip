import org.w3c.dom.ls.LSOutput;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String horizontal = "____________________________________________________________";

        Bob bob = new Bob();
        bob.greet();

        while (true) {
            try {
                String line = sc.nextLine();
                String[] words = line.split(" ", 2);
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
                } else if (firstWord.equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(words[1]);
                    bob.deleteTask(index - 1);
                } else if (firstWord.equalsIgnoreCase("todo")) {
                    bob.addTodo(words);
                } else if (firstWord.equalsIgnoreCase("deadline")) {
                    bob.addDeadline(words);
                } else if (firstWord.equalsIgnoreCase("event")) {
                    bob.addEvent(words);
                }  else {
                    System.out.println(horizontal);
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(horizontal);
                }
            } catch(IllegalChatBotExceptions e) {
                System.out.println(e.getMessage());
            }
        }

        bob.bye();
        sc.close();
    }
}

