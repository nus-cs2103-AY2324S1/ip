# Lati

Lati is a desktop chat app for managing tasks and trivia, optimized for use via a Command Line Interface (CLI) while still having the benefits of a Graphical User Interface (GUI). 

## **How to install:**

1. Ensure you have Java 11 or above installed in your computer. You can check the version of your Java installation by opening the command prompt of your terminal and typing `java -version`

2. Download the latest `duke.jar` from here.

3. Copy the file to the folder you want to use as the home folder for your Duke.

4. Open a command terminal, cd into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.
A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.

5. Type the command in the command box and press Enter to execute it. e.g. typing list and pressing Enter will open all tasks.

6. Refer to the Features below for details of each command

## **Features:**

### Adding a todo task: `todo`

Adds a todo task to the list of tasks. A todo task is a task with no set deadline.

Format: `todo t/NAME_OF_TASK`

Examples
- `todo wash the dishes`
- `todo get a haircut`

### Adding a deadline task: deadline

Adds a deadline task to the list of tasks. A deadline task is a task with a deadline

Format: `deadline t/NAME_OF_TASK /by d/DEADLINE`

Examples
- `deadline iP /by 2023-09-22`
- `deadline tP /by 2023-10-02`

Note: `DEADLINE` must be in "YYYY-MM-DD" format

Adding an event task: `event`
Adds an event task to the list of tasks. An event task is a task with a start and end date.

Format: `event t/NAME_OF_TASK /from f/START_DATE /to e/END_DATE`

Examples
- `event Play Team Fortress 2 /from 2023-09-22 /to 2023-11-22`

Note: All dates (`START_DATE` and `END_DATE` must be in "YYYY-MM-DD" format).
In addition, `START_DATE must` be before or equal to the `END_DATE`

### Marking a task: `mark`
Marks a task as "done".

Format: `mark i/INDEX`

Note: Marks the task at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index MUST be a positive integer.

### Unmarking a task: `unmark`
Unmarks a completed task.

Format: `unmark i/INDEX`

Note: Unmarks the task at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index MUST be a positive integer.

### Deleting a task: `delete`
Deletes a task.

Format: `delete i/INDEX`

Notes: 

- Deletes the task at the specified `INDEX`. The index refers to the index number shown in the displayed list. The index MUST be a positive integer.
- Once the task is deleted, all tasks with an index higher than it is shifted back by one.

Showing all tasks: `list`
Shows the current tasks stored

### Searching a task: `search`

Searches a task with a given keyword

Format: `search k/KEYWORD`

### Showing all tasks: `list`

Format: `list`


### Adding trivia: `addtrivia`
Adds a trivia question and corresponding answer to the Dukebot.

Format: `addtrivia q/QUESTION /answer a/ANSWER`

Examples
- `addtrivia Who wrote the Aeneid /answer Virgil`
- `addtrivia Who owns the Yamato /answer Vergil`


### Editing trivia: `edittrivia1
Edits the answer of a particular trivia question

Format: `edittrivia q/QUESTION /answer a/NEW_ANSWER1`

Examples
- editTrivia Who owns the Yamato /answer Japan Edits the `Who owns the Yamato` trivia answer to be `Japan` instead of `Vergil`

### Answering trivia: `ask`
Incites a response if Lati knows the answer to said trivia

Format: `ask q/QUESTION`

Examples
- `ask Who owns the Yamato` returns `Japan`
- `ask Who is the Hobgoblin` returns `I don't know...`

### Deleting trivia: `deletetrivia`
Removes a trivia question

Format: `deletetrivia q/QUESTION`

### Closing the chatbot: `bye`

Format: `bye`



