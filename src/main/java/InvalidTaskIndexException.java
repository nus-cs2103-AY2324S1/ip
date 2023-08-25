public class InvalidTaskIndexException extends JarvisException{
    private int index;

    public InvalidTaskIndexException(int index) {
        super();
        this.index = index;
    }

    @Override
    public String getMessage() {
        return super.getMessage() + "I'm sorry, sir. Your target task is not found.";
    }
}
