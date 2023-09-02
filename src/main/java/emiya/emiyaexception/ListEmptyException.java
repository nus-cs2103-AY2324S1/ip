package emiya.emiyaexception;

public class ListEmptyException extends EmiyaException {
    public ListEmptyException() {
        super("-----------------------------------------\n"
                + "List is empty! Please add items to list before trying to display list contents!\n"
                + "-----------------------------------------\n");
    }
}
