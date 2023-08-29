package duke;
public class Ui {
    private String line = "    ______________________________________________";
    public void greet() {
        System.out.println(line + "\n    Hello, I'm your task manager :)\n    What can I do for you?\n" + line);
    }
    public void bye() {
        System.out.println("    Bye. Hope to see you again soon!");
    }
    public void horizontalLine() {
        System.out.println(line);
    }
    public void oops() {
        System.out.println("    ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    }
}
