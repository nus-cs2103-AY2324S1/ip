public class Ui {

    public void showIntro() {
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
    }

    public void showLoadingError() {
        System.out.println("No saved tasks");
    }

    public void showBye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public void removeTask(TaskList list, Task removedTask) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(removedTask.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    public void printList(TaskList list) {
        if (list.getSize() == 0) {
            System.out.println("There are no tasks in your list.");
        } else {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < list.getSize(); i++) {
                System.out.println((i + 1) + ". " + list.getTask(i).toString());
            }
        }
    }

    public void mark(TaskList list, int number) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.getTask(number).toString());
    }

    public void unMark(TaskList list, int number) {
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(list.getTask(number).toString());
    }

    public void addTodo(TaskList list, Todo newTodo) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newTodo.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    public void addDeadline(TaskList list, Deadline newDeadline) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newDeadline.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

    public void addEvent(TaskList list, Event newEvent) {
        System.out.println("Got it. I've added this task:");
        System.out.println(newEvent.toString());
        System.out.println("Now you have " + list.getSize() + " tasks in the list.");
    }

}
