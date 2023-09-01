package duke;

public class Ui {

    private final String name;
    private int invalidInputCount = 0;

    public Ui() {
        this.name = "Meg";
    }

    public void printHorizontalLine() {
        for (int i = 0; i < 20; i++) {
            System.out.print("-");
        }
        System.out.println();
    }
    public void printCommands() {
        System.out.printf("\033[3mtask\033[0m - Create a new task%n");
        System.out.printf("\033[3mtodo\033[0m - Create a new todo%n");
        System.out.printf("\033[3mdeadline\033[0m - Create a new deadline%n");
        System.out.printf("\033[3mevent\033[0m - Create a new event%n");
        System.out.printf("\033[3mlist\033[0m - View your current tasks and completion status%n");
        System.out.printf("\033[3mmark\033[0m - Mark a task as complete%n");
        System.out.printf("\033[3munmark\033[0m - Mark a task as incomplete%n");
        System.out.printf("\033[3mdelete\033[0m - Delete a task%n");
        System.out.printf("\033[3mbye\033[0m - Exit the program%n");
        System.out.printf("\033[3mfind\033[0m - Find all tasks containing a given keyword%n");
        printHorizontalLine();
    }

    public void printSelfIntroduction() {
        System.out.printf("I'm %s. Nice to meet you.%n", this.name);
        System.out.println("I support the following commands:" + "\n");
        printCommands();
    }

    public void printEndOfOperation() {
        System.out.println();
        System.out.printf("Anything else you want me to do?%n");
        System.out.printf("Just so you know, you can input \033[3mcommands\033[0m " +
                "to view the commands again.%n");
        printHorizontalLine();
    }

    public void incrementInvalidInputs() {
        this.invalidInputCount++;
    }

    public int getInvalidInputCount() {
        return this.invalidInputCount;
    }

}
