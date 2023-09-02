package duke;



/** This is the class to parse stuff
 *
 */
public class Parser {
  
    MarkPattern check = new MarkPattern();

    /** The constructor to init
     *
     */
    public Parser() {
    }

    private String reply = "";

    public String get() {
        return reply;
    }

    public void update(String text) {
        System.out.println(text);
        reply += text + "\n";
    }

    /**
     * Enter your things to parse here
     * @param stuff your parse items
     * @param items your tasklist
     * @return returns the modified tasklist
     */
    public TaskList parse(String stuff, TaskList items) {

        if (stuff.equalsIgnoreCase("list")) {

            for (int i = 0; i < items.size(); i++) {
                update((i + 1) + ". " + items.get(i).display());
            }

        } else if (check.unmark(stuff) != -1) {
            items.get(check.unmark(stuff)).done = false;
            update("Gg, not done with " + items.get(check.unmark(stuff)).display());
        } else if (check.mark(stuff) != -1) {
            items.get(check.mark(stuff)).done = true;
            update("Yay, done with " + items.get(check.mark(stuff)).display());
        } else if (check.del(stuff) != -1) {
            update("Deleted: " + items.remove(check.del(stuff)).display());
        }
        else if (check.find1(stuff) != null) {
            for (int i = 0; i < items.size(); i++) {
                if (items.get(i).display().contains(check.find1(stuff))) {
                    update((i+1) + ". " + items.get(i).display());
                }
            }
        }

        else{
            if (stuff.trim().toLowerCase().startsWith("todo")) {
                if (stuff.trim().substring(4).trim().length() == 0) {
                    update("Why empty???");
                } else {
                    update("added: " + stuff.trim().substring(4));
                    items.add(new ToDo(stuff.trim().substring(4)));
                }
            } else if (stuff.trim().toLowerCase().startsWith("deadline")) {
                if (!stuff.toLowerCase().contains("/by")) {
                    update("Hey!!! please use /by to indicate a deadline, dont break me please...");
                } else {
                    String[] parts = stuff.trim().substring(8).split("/by");
                    if (parts[1].trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                        items.add(new Deadline(parts[0].trim(), parts[1].trim()));
                        update("added: " + parts[0].trim() + " (Due by: " + parts[1].trim() + ")");
                    } else {
                        update("wrong date...");
                    }
                }
            } else if (stuff.trim().toLowerCase().startsWith("event")) {
                if (!(stuff.toLowerCase().contains("/from") && stuff.toLowerCase().contains("/to"))) {
                    update("Hey! Where is your /from and /to tags??");
                } else {
                    String[] parts = stuff.trim().substring(5).split("/from");
                    String part1 = parts[0].trim();
                    String[] part23 = parts[1].trim().split("/to");
                    if (part23[0].trim().matches("\\d{4}-\\d{2}-\\d{2}") && part23[1].trim().matches("\\d{4}-\\d{2}-\\d{2}")) {
                        items.add(new Event(part1, part23[0].trim(), part23[1].trim()));
                        update("added: " + part1 + " (From: " + part23[0].trim() + " To: " + part23[1].trim() + ")");
                    } else {
                        update("wrong date format,,,");
                    }
                }
            } else {
                update("I don't understand...");
            }
        }
        return items;
    }
}
