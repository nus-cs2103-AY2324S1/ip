package emiyaexception;

public class CreateDirectoryFailException extends EmiyaException{
    public CreateDirectoryFailException() {
        super("-----------------------------------------\n" +
                "Failed to create directory\n"
                + "-----------------------------------------\n");
    }
}
