/**
 * Exit command which terminates the program
 */
public class ExitCommand implements Command {
    @Override
    public void run(String input) {
        System.out.println(DukeConstants.EXIT_MESSAGE);
        System.exit(1);
    }
}
