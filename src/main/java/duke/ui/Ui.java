package duke.ui;

public class Ui {
    private StringBuilder response = new StringBuilder();
    public void appendResponse(String message) {
        this.response.append(message);
    }
}
