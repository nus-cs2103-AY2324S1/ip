public class ContentMissingException extends JarvisException{
    private String type;

    public ContentMissingException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String getMessage() {
        return super.getMessage() +
                "I'm sorry, sir. The content of a " + this.type + " cannot be empty.";
    }
}
