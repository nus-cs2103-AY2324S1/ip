package javai;
public class Ui {

    String line = "     ____________________________________________________________";
    public void welcome() {
        System.out.println(line + "\n      Hello, I'm JavAI.\n      What can I do for you?\n" + line);
    }

    public void displayLine() {
        System.out.println(line);
    }
    public void printAddTask(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Got it. I've added this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }
    public void printList(TaskList tasks) {
        try {
            displayLine();
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println((i + 1) + "." + tasks.get(i).toString());
            }
            displayLine();
        } catch (JavAIException e) {
            showLoadingError(e);
        }
    }
    public void printDelete(Task task, TaskList tasks) {
        displayLine();
        System.out.println("Noted. I've removed this task:\n" + task +
                "\nNow you have " + tasks.size() + " tasks in the list.");
        displayLine();
    }
    public void printDone(Task task) {
        displayLine();
        System.out.println("Nice! I've marked this task as done:\n" + task);
        displayLine();
    }

    public void printUndone(Task task) {
        displayLine();
        System.out.println("Ok! I've marked this task as not done yet:\n" + task);
        displayLine();
    }

    public void exit() {
        System.out.println(line + "\n      Bye. Hope to see you again soon!\n" + line);
    }
    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }
    public void print(String string) {
        System.out.println(string);
    }

}
