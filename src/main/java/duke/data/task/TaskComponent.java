package duke.data.task;

import java.time.LocalDate;
import java.util.Collections;
import java.util.HashSet;

import duke.exception.InvalidInputException;

/**
 * Some unit for storing and presenting information to compose a task.
 */
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

final class Tags extends TaskComponent {
    protected HashSet<String> tags;

    public Tags() {
        this.tags = new HashSet<String>();
    }
    public boolean hasTag(String tag) {
        return this.tags.contains(tag);
    }
    public void addTag(String tag) throws InvalidInputException {
        if (tag.contains(",")) {
            throw new InvalidInputException("Invalid Input: Tag should not contain \",\"");
        }
        if (this.tags.contains(tag)) {
            throw new InvalidInputException("Invalid Input: Tag already exists");
        }
        this.tags.add(tag);
    }
    public void removeTag(String tag) throws InvalidInputException {
        if (!this.tags.contains(tag)) {
            throw new InvalidInputException("Invalid Input: Tag does not exist");
        }
        this.tags.remove(tag);
    }
    @Override
    public String toString() {
        String tagsString = "";
        for (String tag : this.tags) {
            tagsString += tag + ",";
        }
        // Remove the last comma
        return tagsString.substring(0, tagsString.length() - 1);
    }
}

