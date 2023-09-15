package duke.trivia;

public class Trivia {
    private String question;
    private String answer;

    /**
     * Constructs a <code>Trivia</code> object.
     * @param question The question of the trivia.
     * @param answer The answer of the trivia.
     */
    public Trivia(String question, String answer) {
        this.question = question;
        this.answer = answer;
    }

    public String getQuestion() {
        return question;
    }

    public String getAnswer() {
        return answer;
    }

    public boolean checkAnswer(String userAnswer) {
        return this.answer.equalsIgnoreCase(userAnswer.trim());
    }

    public String toFileFormat() {
        return "TRIVIA | " + getQuestion() + " | " + getAnswer();
    }

    /**
     * Converts a trivia from file format.
     * @param line The file format of the trivia.
     * @return A Trivia object.
     */
    public static Trivia fromFileFormat(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length != 3 || !parts[0].equals("TRIVIA")) {
            return null; // Return null or throw an exception if the format is incorrect
        }
        return new Trivia(parts[1], parts[2]);
    }

    @Override
    public String toString() {
        return "Question: " + question + "\nAnswer: " + answer;
    }
}
