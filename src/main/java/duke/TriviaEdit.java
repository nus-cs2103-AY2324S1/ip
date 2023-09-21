package duke;

public class TriviaEdit extends Command {
    private String question;

    private String answer;

    private TriviaList trivia;

    public TriviaEdit(String question, String answer, TriviaList trivia) {
        this.question = question;
        this.answer = answer;
        this.trivia = trivia;
    }

    public String execute() {
        trivia.editAnswer(question, answer);
        return Ui.triviaEdit(question, answer);
    }

}