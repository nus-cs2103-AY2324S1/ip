package errors;


public enum TaskErrors {
    ERR_ADDING_ITEM ("Error adding item in %s"),
    ERR_USING_MARK ("Invalid use of mark, use: \"mark <task number>\"");


    private final String errorMessage;
    TaskErrors(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void printErrorMessage(Exception e) {
        System.err.println(this.errorMessage + ": " + e);
    }
}
