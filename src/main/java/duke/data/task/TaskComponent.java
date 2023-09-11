package duke.data.task;

import duke.exception.InvalidInputException;
import java.time.LocalDate;

public abstract class TaskComponent {
}

final class Description extends TaskComponent {
    protected String description;

    public Description(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return this.description;
    }

    public boolean hasKeyword(String keyword) {
        return this.description.contains(keyword);
    }
}

final class By extends TaskComponent {
    protected LocalDate by;

    public By(String by) throws InvalidInputException {
        try {
            this.by = LocalDate.parse(by);
        } catch (Exception e) {
            throw new InvalidInputException("Invalid date format, should be YYYY-MM-DD");
        }
    }

    @Override
    public String toString() {
        // Accept dates in a format such as yyyy-mm-dd format (e.g., 2019-10-15) and
        // print in a different format such as MMM dd yyyy e.g., (Oct 15 2019).
        return this.by.format(java.time.format.DateTimeFormatter.ofPattern("MMM dd yyyy"));
    }
}

final class From extends TaskComponent {
    protected String from;

    public From(String from) {
        this.from = from;
    }

    @Override
    public String toString() {
        return this.from;
    }
}

final class To extends TaskComponent {
    protected String to;

    public To(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return this.to;
    }
}
