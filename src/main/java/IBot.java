import java.util.ArrayList;
public class IBot {
    private ArrayList<Task> lst = new ArrayList<>();

    private void addTask(Task t) {
        System.out.println("Got it. I've added this task:");
        lst.add(t);
        System.out.println("  " + t);
        System.out.println("Now you have " + lst.size() + " tasks in the list.\n");
    }

    private void todo(String name) {
        addTask(new Todo(name));
    }

    private void deadline(String name, String time) {
        addTask(new Deadline(name, time));
    }

    private void event(String name, String timeFrom, String timeTo) {
        addTask(new Event(name, timeFrom, timeTo));
    }

    private void mark(int index) {
        if (index <= lst.size()) {
            System.out.println("Nice! I've marked this task as done:");
            lst.get(index - 1).mark();
        } else {
            System.out.println("There is no such a task");
        }
    }

    private void unmark(int index) {
        if (index <= lst.size()) {
            System.out.println("OK, I've marked this task as not done yet:");
            lst.get(index - 1).unmark();
        } else {
            System.out.println("There is no such a task");
        }
    }

    private void list() {
        System.out.println("Here are the tasks in your list:");
        lst.forEach(x -> System.out.println(lst.indexOf(x) + 1 + "." + x));
        System.out.println("");
    }
    public boolean parse(String instr) {
        String keyWord = instr.split(" ")[0];
        String[] temp;
        switch (keyWord) {
            case "":
                System.out.println("you said nothing\n");
                break;
            case "todo":
                todo(instr.substring(5));
                break;
            case "deadline":
                temp = instr.substring(9).split(" /");
                deadline(temp[0], temp[1].substring(3));
                break;
            case "event":
                temp = instr.substring(6).split(" /");
                event(temp[0],
                        temp[1].substring(5), temp[2].substring(3));
                break;
            case "mark":
                mark(Integer.parseInt(instr.substring(5)));
                break;
            case "unmark":
                unmark(Integer.parseInt(instr.substring(7)));
                break;
            case "list":
                list();
                break;
            case "bye":
                System.out.println(" Bye. Hope to see you again soon!");
                return false;
            default:
                //throw new IllegalStateException("Unexpected value: " + keyWord);
                System.out.println("I can't understand.\n");
        }
        return true;
    }
}
