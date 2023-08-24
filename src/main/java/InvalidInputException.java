public class InvalidInputException extends Exception {
    public InvalidInputException(String message) {
        super(message + ":" + "\n\tI don't know what that means D: Please enter valid inputs. Below are the valid" +
                " inputs I can take\n" +
                "\t1. list \n\t2. todo \n\t3. deadline " +
                "\n\t4. event \n\t5. mark \n\t6. unmark \n\t7. bye");
    }

}
