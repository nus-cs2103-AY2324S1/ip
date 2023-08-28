public class BarbieNoListNameException extends BarbieException{
    public BarbieNoListNameException() {
        super("Awe man Barbie, we can't find any list name in your request!\n"
                + "Make sure to save it alongside a list name!");
    }
}
