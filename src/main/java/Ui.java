import java.util.List;

public class Ui {
    public void printGreeting() {
        System.out.println("\n" +
                " ____   __   ____   __  \n" +
                "/ ___) / _\\ (  _ \\ / _\\ \n" +
                "\\___ \\/    \\ )   //    \\\n" +
                "(____/\\_/\\_/(__\\_)\\_/\\_/\n");
        System.out.println("Hello! I'm Sara");
        System.out.println("What can I do for you?");
    }

    public void printFarewell() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void printList(List<Task> toDoList) {
        System.out.println("Here are the tasks in your List:");
        for (int i = 0; i < toDoList.size(); i++) {
            System.out.println(((i + 1) + ". " + toDoList.get(i)));
        }
    }
}
