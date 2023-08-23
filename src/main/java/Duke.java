import java.util.*;
public class Duke {
    public static void main(String[] args) {
        String name = "Harry Potter";
        String question = "What can I do for you?";
        System.out.println("Hello! I'm " + name + "\n" + question);

        Scanner sc = new Scanner(System.in);
        String str = sc.nextLine();
        String output = "";
        Integer i = 1;
        List<String> lst = new ArrayList<>();

        while (!str.equals("bye")) {
            if (!str.equals("list")) {
                lst.add(str);
                System.out.println("\t" + "added: " + str);
            } else {
                for (String el : lst) {
                    output += i.toString() + ". " + el + "\n" + "\t";
                    i++;
                }
                System.out.println("\t" + output);
                output = ""; //reset
                i = 1; //reset
            }
            str = sc.nextLine();
        }
        System.out.println("\t" + "Bye. Hope to see you again soon!");
    }
}
