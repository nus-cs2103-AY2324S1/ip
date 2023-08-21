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

            else if (check.unmark(stuff) != -1) {
                items.get(check.unmark(stuff)).done = false;
                System.out.println("Gg, not done with " + items.get(check.unmark(stuff)).display());
            }
            else if (check.mark(stuff) != -1) {
                items.get(check.mark(stuff)).done = true;
                System.out.println("Yay, done with " + items.get(check.mark(stuff)).display());
            }

            else{
                if(stuff.trim().toLowerCase().startsWith("todo")) {
                    System.out.println("added: " + stuff.trim().substring(4));
                    items.add(new ToDo(stuff.trim().substring(4)));
                }
                else if(stuff.trim().toLowerCase().startsWith("deadline") && stuff.toLowerCase().contains("/by")) {
                    String[] parts = stuff.trim().substring(8).split("/by");
                    items.add(new Deadline(parts[0].trim(),parts[1].trim()));
                    System.out.println("added: " + parts[0].trim() + " (Due by: " + parts[1].trim() + ")");
                }
                else if(stuff.trim().toLowerCase().startsWith("event") && stuff.toLowerCase().contains("/from") && stuff.toLowerCase().contains("/to")) {
                    String[] parts = stuff.trim().substring(5).split("/from");
                    String part1 = parts[0].trim();
                    String[] part23 = parts[1].trim().split("/to");
                    items.add(new Event(part1,part23[0].trim(), part23[1].trim()));
                    System.out.println("added: " + part1 + " (From: " + part23[0].trim() + " To: " + part23[1].trim() + ")");
                }
                else {
                    System.out.println("I don't understand...");
                }
            }
            stuff = scanner.nextLine();
        }

        System.out.println("Byeeee! Use me again please!");
    }
}
