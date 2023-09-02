package emiya.emiyaexception;

/**
 * An exception that is thrown when the program is unable to create a new directory.
 * This is used to create the directory that the list contents will be saved into.
 */
public class CreateDirectoryFailException extends EmiyaException{
    public CreateDirectoryFailException() {
        super("-----------------------------------------\n" +
                "Failed to create directory\n"
                + "-----------------------------------------\n");
    }
}
