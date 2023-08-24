import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm ChatBot.\n" +
         "What can I do for you?\n" );

        Scanner scanner = new Scanner(System.in);
        List<String> inputs = new ArrayList<>();
        String input = scanner.nextLine();

        while (!input.equals("bye")) {

            if(input.startsWith("list")){
                for(int i = 0; i < inputs.size(); i++){
                    System.out.println((i + 1) + ". "+ inputs.get(i));
                }
            } else {
                inputs.add(input);
                System.out.println("added: " + input);
            }

            input = scanner.nextLine();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
