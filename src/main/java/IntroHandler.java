public class IntroHandler implements ICommandHandler{

    @Override
    public void execute() {
        String name = Main.getInstance().getName();
        System.out.println("Hello! I'm " + name + "\nWhat can I do for you?");
    }
}
