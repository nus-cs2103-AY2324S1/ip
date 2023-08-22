public class IntroHandler implements ICommandHandler{

    @Override
    public void execute(String command) {
        String name = Main.getInstance().getName();
        Main.getInstance().say("Hello! I'm " + name, true, false);
        Main.getInstance().say("What can I do for you?", false, true);
    }
}
