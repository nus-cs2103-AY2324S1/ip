public class CommandBye extends Command{
    @Override
    public void accept(Parser input) {
        Rock.terminate();
    }
}
