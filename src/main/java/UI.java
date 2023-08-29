import java.util.Scanner;

public class UI {


    //End trigger to end the program


    // Intro message
    private String intro = "____________________________________________________________\n" +
            " Hello! I'm Alpha\n" +
            " What can I do for you?\n" + "____________________________________________________________";

    //Outro message
    private String end = "____________________________________________________________\n" +
            " Bye. Hope to see you again soon!\n" +
            "____________________________________________________________";

    public UI() {
    }

    public void introduce() {
        System.out.println(intro);
    }

    public void goodbye() {
        System.out.println(end);
    }

    public void taskAdded(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                "Alright! I've added this task:\n " + " " + task
                + "\nNow you have " + size + " tasks in the list.\n" +
                "____________________________________________________________");
    }

    public void mark(Task task) {
        System.out.println("____________________________________________________________\n" +
                "Nice! I've marked this task as done:\n" + "  " +
                task +
                "\n____________________________________________________________");
    }

    public void unmark(Task task) {
        System.out.println("____________________________________________________________\n" +
                "Cool! I've marked this task as not done yet:\n" + "  " +
                task +
                "\n____________________________________________________________");

    }

    public void delete(Task task, int size) {
        System.out.println("____________________________________________________________\n" +
                "Noted. I've removed this task:\n" + "  " + task + "\n Now You have " + size +
                " tasks in the list.\n"  +
                "____________________________________________________________");
    }

    public void list(int size, TaskList taskList) {
        System.out.println("____________________________________________________________\n" +
                "Here are the tasks in your list:");
        for (int i = 0; i < size; i++) {
            int plusOne = i + 1; // Increment by one so starting display index is 1
            System.out.println(plusOne + ". " + taskList.getTask(i));
        }
        System.out.println("____________________________________________________________");
    }

    public String read() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }
}
