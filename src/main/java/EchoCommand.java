/**
 * Echo command which echos what the user inputs
 */
public class EchoCommand implements Command {
    @Override
    public void run(String input) {
        System.out.println(String.format("\t %s\n\t", input) + DukeConstants.HORIZONTAL_LINE);
    }
}
