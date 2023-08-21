@FunctionalInterface
public interface  Command {
    boolean execute(TaskList tasks,Ui ui); // Executes, and return if the program should end

}

