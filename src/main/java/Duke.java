import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //check if ./data/duke.txt exists
        File input = new File("./data/duke.txt");
        try {
            if (!input.exists()) {
                input.createNewFile();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //Initializers
        Scanner sc = new Scanner(System.in);
        String horizontal = "____________________________________________________________";

        Bob bob = new Bob();
        bob.greet();
        //reads the existing file for existing tasks written in the txt file.
        bob.fRead("./data/duke.txt");

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
                    bob.rewriteFile();
                } else if (words.length > 1 && firstWord.equalsIgnoreCase("unmark")) {
                    int index = Integer.parseInt(words[1]);
                    bob.unmarkTask(index - 1);
                    bob.rewriteFile();
                } else if (firstWord.equalsIgnoreCase("delete")) {
                    int index = Integer.parseInt(words[1]);
                    bob.deleteTask(index - 1);
                    bob.rewriteFile();
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
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        bob.bye();
        sc.close();
    }
}

