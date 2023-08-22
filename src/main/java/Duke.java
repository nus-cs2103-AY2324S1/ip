import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> arr;
    String partition = "--------------------------------------";

    public Duke() {
        this.arr = new ArrayList<>();
    }

    public void printExitMessage() {
        String exitMsg = "Bye. Hope to see you again soon!";
        System.out.println(partition + "\n   " + exitMsg + "\n" + partition);
    }

    public void addToList(String text) {
        ToDo newTask = new ToDo(text);
        this.arr.add(newTask);
        System.out.println(partition + "\nadded:\n" + newTask + "\n" +
        "You have " + this.arr.size() + " tasks in the list.\n" + partition);
    }

    public void addToList(String text, String dueDate) {
        Deadline newTask = new Deadline(text, dueDate);
        this.arr.add(newTask);
        System.out.println(partition + "\nadded:\n" + newTask + "\nYou have "
        + this.arr.size() + " tasks in the list.\n" + partition);
    }

    public void addToList(String text, String startDate, String endDate) {
        Event newTask = new Event(text, startDate, endDate);
        this.arr.add(newTask);
        System.out.println(partition + "\nadded:\n" + newTask + "\nYou have "
        + this.arr.size() + " tasks in the list.\n" + partition);
    }

    public void markTask(int index) {
        Task curr = arr.get(index - 1);
        curr.changeStatus();
        System.out.println(partition + "\nNice! I've marked this task as done:\n" 
        + curr + "\n" + partition);
    }

    public void unmarkTask(int index) {
        Task curr = arr.get(index - 1);
        curr.changeStatus();
        System.out.println(partition + "\nOK, I've marked this task as not done yet:\n"
        + curr + "\n" + partition);
    }

    public void printList() {
        System.out.println(partition);
        for (int i = 0; i < arr.size(); i++) {
            int index = i + 1;
            System.out.println(index + ". " + arr.get(i));
        }
        System.out.println(partition);
    }

    public void initialise() {
        System.out.println(partition + "\n" + "Hello! I'm Rion");
        System.out.println("What can I do for you?\n" + partition);
    }

    public void runRion() {
        this.initialise();

        boolean endBot = false;
        while (!endBot) {
            String input = this.sc.nextLine();
            if (input.equals("bye")) {
                endBot = true;
            } else if (input.equals("list")) {
                this.printList();
            } else if (input.startsWith("mark")) {
                int currIndex = Integer.parseInt(input.replace("mark ", ""));
                this.markTask(currIndex);
            } else if (input.startsWith("unmark")) {
                int currIndex = Integer.parseInt(input.replace("unmark ", ""));
                this.unmarkTask(currIndex);
            } else if (input.startsWith("todo")) {
                String description = input.replace("todo", "");
                this.addToList(description);
            } else if (input.startsWith("deadline")) {
                String description = input.replace("deadline ", "");
                String[] details = description.split(" /by ");
                this.addToList(details[0], details[1]);
            } else {
                String description = input.replace("event", "");
                String[] details = description.split(" /");
                details[1] = details[1].replace("from ", "");
                details[2] = details[2].replace("to ", "");
                this.addToList(details[0], details[1], details[2]);
            }
        }
        this.printExitMessage();
    }
    public static void main(String[] args) {
        Duke rion = new Duke();
        rion.runRion();
    }
}
