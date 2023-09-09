package smolbrain;

/**
 * Stub for Ui class
 */
public class UiStub {

    public String showLine() {
        return "____________________________________________________________";
    }

    public String showError(Exception e) {
        return "OOPS!!! " + e;
    }

    public String showMessage(String s) {
        return s;
    }

}
