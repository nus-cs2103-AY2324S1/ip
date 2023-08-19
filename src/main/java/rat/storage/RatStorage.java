package rat.storage;
import java.util.ArrayList;
import rat.print.RatPrinter;

public class RatStorage {

    private final ArrayList<Task> storage;

    public RatStorage() {
        storage = new ArrayList<>();
    }

    @Override
    public String toString() {
        String str = "";
        for (int i = 0; i < this.storage.size(); i++) {
            str += (i + 1) + ". " + this.storage.get(i).toString() + "\n";
        }
        return str;
    }

    public void addToDo(String item) {
        ToDo newToDo = new ToDo(item);
        this.storage.add(newToDo);
        RatPrinter.printWithLines("added: " + newToDo);
    }

    public void addDeadline(String deadline, String name) {
        Deadline newDeadline = new Deadline(deadline, name);
        this.storage.add(newDeadline);
        String msg = "Got it. I've added this Deadline:\n"
                + newDeadline.toString()
                + "\nNow you have " + this.storage.size() + " tasks in the list.";
        RatPrinter.printWithLines(msg);
    }

    public void addEvent(String startTime, String endTime, String name) {
        Event newEvent = new Event(startTime, endTime, name);
        this.storage.add(newEvent);
        String msg = "Got it. I've added this Event:\n"
                + newEvent.toString()
                + "\nNow you have " + this.storage.size() + " tasks in the list.";
        RatPrinter.printWithLines(msg);
    }


    public void markItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            RatPrinter.printWithLines("invalid index");
            return;
        } else if (this.storage.get(index - 1) == null) {
            RatPrinter.printWithLines("item not found");
            return;
        }
        Task item = this.storage.get(index - 1);
        item.markDone();
        RatPrinter.printWithLines("Nice! I've marked this task as done: " + storage.get(index - 1).toString());
    }

    public void unmarkItemDone(int index) {
        if (index > this.storage.size() || index < 1) {
            RatPrinter.printWithLines("invalid index");
            return;
        } else if (this.storage.get(index - 1) == null) {
            RatPrinter.printWithLines("item not found");
            return;
        }
        Task item = this.storage.get(index - 1);
        item.unmarkDone();
        RatPrinter.printWithLines("Ok, I've marked this task as not done yet: " + storage.get(index - 1).toString());
    }

    public void listItems() {
        RatPrinter.printWithLines(this.toString());
    }
}