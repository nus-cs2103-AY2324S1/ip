import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String name = "Termina";
        System.out.println("Hello, I am your chatbot!\nMy name is " + name + "\nHow may I help?");
        Scanner scanner = new Scanner(System.in);
        String stuff;
        ArrayList<Task> items = new ArrayList();
        stuff = scanner.nextLine();
        MarkPattern check = new MarkPattern();

        while (!stuff.equalsIgnoreCase("bye")) {
            if(stuff.equalsIgnoreCase("list")) {

                for (int i = 0; i < items.size(); i++) {
                    System.out.println((i+1) + ". " + items.get(i).display());
                }

            }
            else if (check.mark(stuff) != -1) {
                items.get(check.mark(stuff)).done = true;
                System.out.println("Yay, done with " + items.get(check.mark(stuff)).display());
            }

            else if (check.unmark(stuff) != -1) {
                items.get(check.unmark(stuff)).done = false;
                System.out.println("Gg,not done with " + items.get(check.unmark(stuff)).display());
            }

            else{
                System.out.println("added: " + stuff);
                items.add(new Task(stuff));
            }
            stuff = scanner.nextLine();
        }

        System.out.println("Byeeee! Use me again please!");
    }
}
