import java.util.ArrayList;
import java.util.Scanner;

//The main class to run
public class Duke {
    //Main method to start the program
    public static void main(String[] args) {
        String name = "Termina";
        System.out.println("Hello, I am your chatbot!\nMy name is " + name + "\nHow may I help?");
        Scanner scanner = new Scanner(System.in);
        String stuff;
        HardDrive load = new HardDrive("tasks.ser");
        ArrayList<Task> items = load.loadDataFromFile();
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
            else if (check.del(stuff) != -1) {
                System.out.println("Deleted: " + items.remove(check.del(stuff)).display());
            }

            else{
                if(stuff.trim().toLowerCase().startsWith("todo")) {
                    if(stuff.trim().substring(4).trim().length() == 0) {
                        System.out.println("Why empty???");
                    } else {
                        System.out.println("added: " + stuff.trim().substring(4));
                        items.add(new ToDo(stuff.trim().substring(4)));
                    }
                }
                else if(stuff.trim().toLowerCase().startsWith("deadline") ) {
                    if (!stuff.toLowerCase().contains("/by")) {
                        System.out.println("Hey!!! please use /by to indicate a deadline, dont break me please...");
                    }
                    else {
                        String[] parts = stuff.trim().substring(8).split("/by");
                        items.add(new Deadline(parts[0].trim(),parts[1].trim()));
                        System.out.println("added: " + parts[0].trim() + " (Due by: " + parts[1].trim() + ")");
                    }
                }
                else if(stuff.trim().toLowerCase().startsWith("event")) {
                    if (!(stuff.toLowerCase().contains("/from") && stuff.toLowerCase().contains("/to"))) {
                        System.out.println("Hey! Where is your /from and /to tags??");
                    }
                    else {
                        String[] parts = stuff.trim().substring(5).split("/from");
                        String part1 = parts[0].trim();
                        String[] part23 = parts[1].trim().split("/to");
                        items.add(new Event(part1,part23[0].trim(), part23[1].trim()));
                        System.out.println("added: " + part1 + " (From: " + part23[0].trim() + " To: " + part23[1].trim() + ")");
                    }
                }
                else {
                    System.out.println("I don't understand...");
                }
            }
            load.saveDataToFile(items);
            stuff = scanner.nextLine();
        }

        System.out.println("Byeeee! Use me again please!");
    }
}
