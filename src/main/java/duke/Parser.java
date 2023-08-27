package duke;

import duke.Deadline;
import duke.Event;
import duke.MarkPattern;

public class Parser {

    public Parser() {
    }
    MarkPattern check = new MarkPattern();

    public TaskList parse(String stuff, TaskList items) {

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
        else if (check.find1(stuff) != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).display().contains(check.find1(stuff))) {
                    System.out.println((i+1) + ". " + items.get(i).display());
                }
            }
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
                    if (parts[1].trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                        items.add(new Deadline(parts[0].trim(),parts[1].trim()));
                        System.out.println("added: " + parts[0].trim() + " (Due by: " + parts[1].trim() + ")");
                    }
                    else {
                        System.out.println("wrong date...");
                    }
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
                    if (part23[0].trim().matches("\\d{4}-\\d{2}-\\d{2}") && part23[1].trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                        items.add(new Event(part1,part23[0].trim(), part23[1].trim()));
                        System.out.println("added: " + part1 + " (From: " + part23[0].trim() + " To: " + part23[1].trim() + ")");
                    } else {
                        System.out.println("wrong date format,,,");
                    }
                }
            }
            else {
                System.out.println("I don't understand...");
            }
        }
        return items;
    }
}
