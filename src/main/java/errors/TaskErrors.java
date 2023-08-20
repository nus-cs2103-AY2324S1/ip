package errors;


import ui.Ui;

public enum TaskErrors {
    ERR_ADDING_ITEM ("..o.o..beep..Error adding item in %s"),
    ERR_USING_MARK ("..o.o..beep..Invalid use of mark, use: \"mark <task number>\""),
    ERR_USING_UNMARK ("..o.o..beep..Invalid use of unmark, use: \"unmark <task number>\"");


    private final String errorMessage;
    TaskErrors(String errorMessage) {
        this.errorMessage = errorMessage;
    }


    public void printErrorMessage(Exception e) {
        Ui.wrapPrintWithHorizontalRules(this.errorMessage +
                String.format("\nConnecting the dots: %s", e));
    }
}
