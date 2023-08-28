package Commands;

public class EchoCommand extends Command {
    private final String str;

    public EchoCommand(String str) {
        this.str = str;
    }

    public void execute() {
        System.out.println(this);
    }

    @Override
    public String toString() {
        return Command.SPACER + "\n" +
                this.str + "\n" +
                Command.SPACER;
    }
}
