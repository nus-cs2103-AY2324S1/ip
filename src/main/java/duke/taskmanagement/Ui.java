package duke.taskmanagement;
import java.util.List;
import java.util.Scanner;

public class Ui {
    public Scanner sc = new Scanner(System.in);

    public String readInCmd() {
//        if (sc.hasNextLine()){
            return sc.nextLine();

    }
    public Ui() {
    }

    public void greet(){
        System.out.println("Hello! I'm JJ\n" +
                "What can I do for you?\n" + "\n");
    }

    public void bye(){
        System.out.println("Bye. Hope to see you again soon!\n");
    }
    public void printList(int size, List<Task> ls){
        System.out.println("Here are the tasks in your list: ");
        for(int i = 0; i < size;i++) {
            int j = i + 1;
            System.out.println(j + "." + ls.get(i));
        }
    }

    public void printMarkUndone(String task) {
        System.out.println("OK, I've marked this task as not done yet:\n" + task);
    }

    public void printMarkDone(String str) {
        System.out.println("Nice! I've marked this task as done:\n" + str);
    }

    public void printRemoveTask(String str, int size) {
        System.out.println("Noted. I've removed this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    public void printAddTask(String str, int size) {
        System.out.println("Got it. I've added this task:\n" + str + "\n" + "Now you have " + size + " tasks in the list.\n");
    }

    public void printFilterList(List<Task> filteredList) {
        System.out.println("Here are the matching tasks in your list: ");
        for(int i = 0; i < filteredList.size() ;i++) {
            int j = i + 1;
            System.out.println(j + "." + filteredList.get(i));
        }
    }
    public void closeScanner() {
        sc.close();
    }
}
