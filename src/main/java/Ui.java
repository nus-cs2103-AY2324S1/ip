import java.util.ArrayList; // import the ArrayList class

public class Ui {
    public Ui(){}

    public void printLines(){
        System.out.println("____________________________________________________________");
    }
    public void printTaskAdded() { System.out.println("Got it. I've added this task:"); }

    public void showError(String taskType){
        this.printLines();
        System.out.println("☹ OOPS!!! The description of a " + taskType + " cannot be empty.");
        this.printLines();
    }

    public void showNumTasks(int i){
        this.printLines();
        System.out.println("Now you have "+ String.valueOf(i) + " tasks in the list.");
        this.printLines();
    }

    public void hello() {
        String logo =
                "___________  __________  __________  ||   \n"
                        +"|         | |         | |         |  ||      \n"
                        +"-----------  ---------- ----------   ||  \n"
                        + "    ||         ||          ||        ||       \n"
                        + "    ||         ||          ||        ||           \n"
                        + "    ||         ||          ||        ||           \n"
                        + "    ||         ||          ||        ||       \n"
                        + "    ||      __________  __________   ||      \n"
                        + "    ||      |         | |         |  ______ \n"
                        + "    ||      ----------  ----------   ______    \n";
        this.printLines();
        System.out.println("Hello! I'm \n" + logo);
        System.out.println("What can I do for you?");
        this.printLines();
    }
    public void goodbye() { System.out.println("Bye. Hope to see you again soon!");}

    public void tasksInList(ArrayList<Task> myList) {
        this.printLines();
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task t: myList){
            System.out.println(String.valueOf(i) + "." + t.toString());
            i++;
        }
        this.printLines();
    }

    public void taskDone(Task item) {
        this.printLines();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(item.toString());
        this.printLines();
    }
    public void taskUndone(Task item) {
        this.printLines();
        System.out.println("OK, I've marked this task as not done yet:");
        System.out.println(item.toString());
        this.printLines();
    }

    public void taskAdd(Task item, ArrayList<Task> myList){
        this.printLines();
        System.out.println("Got it. I've added this task:");
        System.out.println(item.toString());
        System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
        this.printLines();
    }
    public void taskDelete(Task item, ArrayList<Task> myList){
        this.printLines();
        System.out.println("Noted. I've removed this task:");
        System.out.println(item.toString());
        System.out.println("Now you have "+ String.valueOf(myList.size()) + " tasks in the list.");
        this.printLines();
    }

    public static void badDateLoaded(){
        System.out.println("Invalid date/time format encountered");
    }

    public void unrecognisedCommand(){
        System.out.println("____________________________________________________________");
        System.out.println(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
        System.out.println("____________________________________________________________");
    }

    public static void Error(){
        System.out.println("An error occurred");
    }

    public static void Error(Exception e){
        System.out.println("An error occurred");
    }

}
