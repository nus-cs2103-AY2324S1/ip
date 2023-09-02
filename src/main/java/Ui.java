import java.util.List;
public class Ui {
    String name = "Harry Potter";
    String question = "Introducing the Wizarding World Organizer: Your Trusted Guide" +
            " to Efficient & Effective Magical Planning";
    String hello = "Hello muggle! I'm " + name + "\n" + question;
    String bye = "\t" + "Expelliarmus! Hope to see you again muggle! :D";
    public Ui() {}
    public void printHello() {
        System.out.println(hello);
    }
    public void printBye() {
        System.out.println(bye);
    }
    public void printDone(Task done) {
        System.out.println("\t" + "Nice! I've marked this task " +
                "as done:" + "\n" +
                "\t " + done.taskString());
    }
    public void printNotDone(Task notDone) {
        System.out.println("\t" + "OK, I've marked this task " +
                "as not done yet:" + "\n" + "\t" + " " +
                notDone.taskString());
    }
    public void printDelete(Task toBeDeleted, List<Task> tasks) {
        System.out.println("\tNoted. I've removed this task:\n\t " + toBeDeleted.taskString()
                + "\n\tNow you have " + tasks.size() + " tasks in the list.");
    }
    public void printAddTask(Task task, List<Task> tasks) {
        int len = tasks.size();
        String output = "\tGot it. I've added this task:\n\t\t"
                + task.taskString();
        String listLength = len == 1 ? "Now you have " + len + " task in the list." :
                "Now you have " + len + " tasks in the list.";
        System.out.println(output
                + "\n\t" + listLength);
    }
    public void printException(String message) {
        System.out.println(message);
    }
    public void printException() {
        System.out.println("I don't understand what that means D:" +
                " Please input a valid date in the format yyyy-MM-dd HHmm " +
                "(the time in the 24-hour format).");
    }
}
