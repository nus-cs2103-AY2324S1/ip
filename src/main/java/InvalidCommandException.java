public class InvalidCommandException extends JarvisException{

    @Override
    public String getMessage() {
        return super.getMessage() + "I'm sorry, sir. " +
                "I am still learning but currently I don't know what that means :-(";
    }
}
