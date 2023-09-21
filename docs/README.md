---
# Duke

---
# User Guide
Duke is a *Personal Assistant Chatbot that helps a person to keep track of various things., optimized for use via a Command Line Interface* (CLI) while still having the benefits of a Graphical User Interface (GUI).

* Table of Contents
  {:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `duke.jar` from [here](https://github.com/wqseemingly/ip/releases).

3. Copy the file to the folder you want to use as the _home folder_ for your AddressBook.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar duke.jar` command to run the application.<br>
   A GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](Ui.png)

5. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

    * `list` : Lists all tasks.

    * `deadline CS2103T Assignment /by 22/9/2023 2359` : Adds a deadline task named `CS2103T Assignment` to the Task list with the stated deadline.

    * `mark 2` : Marks the 2nd task shown in the current list as done.

    * `delete 3` : Deletes the 3rd task shown in the current list.

    * `find todo` : Finds all todo tasks in your list.

    * `bye` : Exits the app.

6. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `todo n/NAME`, `NAME` is a parameter which can be used as `add n/homework`.

* Extraneous parameters for commands that do not take in parameters (such as `list`, `bye`) will  not be ignored.<br>
  e.g. if the command specifies `list 123`, it will not be interpreted as `list`.

* If you are using a PDF version of this document, be careful when copying and pasting commands that span multiple lines as space characters surrounding line-breaks may be omitted when copied over to the application.
</div>

### Adding a todo task: `todo`

Adds a todo task to the task list.

Format: `add NAME`

Examples:
* `todo homework`
* `todo wash the dishes`

### Adding a deadline task : `deadline`

Adds a deadline task to the task list.

Format: `deadline NAME /by DDMMYYYY HHMM`

Examples:
*  `deadline CS2103T Assignment /by 27/9/2023 2359`
*  `deadline group project /by 1/11/2023 0123`

### Adding an event task : `event`

Adds an event task to the task list.

Format: `event NAME /from DDMMYYYY HHMM /to DDMMYYYY HHMM`

Examples:
*  `event Dad's birthday /from 17/8/2023 0000 /to 17/8/2023 2359`
*  `event Graduation Ceremony /from 12/12/2023 0000 /to 13/12/2023 2359`

### Listing all persons : `list`

Shows a list of all tasks in the task list.

Format: `list`

### Locating persons by name: `find`

Finds persons whose names contain any of the given keywords.

Format: `find KEYWORD`

* The search is case-insensitive. e.g. `cycling` will match `Cycling`
* The order of the keywords matters. e.g. `wash dishes` will not match `dishes wash`
* Only the name is searched.
* Finds task even if the keyword matches the item only partially e.g. `cyc` will match `cycling`

Examples:
* `find homework` returns `homework` and `Math Homework`

### Deleting a person : `delete`

Deletes the specified task from the task list.

Format: `delete INDEX`

* Deletes the task at the specified `INDEX`.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

Examples:
* `list` followed by `delete 2` deletes the 2nd task in the task list.

### Marking a task as done : `mark`

Marks the specified task as done from the task list

Format: `mark INDEX`

* Marks the test at the specified `INDEX` as done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Marking a task as not done : `unmark`

Marks the specified task as not done from the task list

Format: `unmark INDEX`

* Marks the test at the specified `INDEX` as not done.
* The index refers to the index number shown in the displayed task list.
* The index **must be a positive integer** 1, 2, 3, …​

### Exiting the program : `bye`

Exits the program.

Format: `bye`

### Saving the data

AddressBook data are saved in the hard disk automatically after any command that changes the data. There is no need to save manually.

--------------------------------------------------------------------------------------------------------------------
## Command summary

| Action       | Format, Examples                                                                                                           |
|--------------|----------------------------------------------------------------------------------------------------------------------------|
| **Todo**     | `todo NAME` <br> e.g., `add n/James Ho p/22224444 e/jamesho@example.com a/123, Clementi Rd, 1234665 t/friend t/colleague`  |
| **Deadline** | `deadline NAME /by DDMMYYYY HHMM`<br> e.g., `deadline group project /by 1/11/2023 0123`                                    |
| **Event**    | `event NAME /from DDMMYYYY HHMM /to DDMMYYYY HHMM`<br> e.g.,`event Dad's birthday /from 17/8/2023 0000 /to 17/8/2023 2359` |
| **Delete**   | `delete INDEX`<br> e.g., `delete 3`                                                                                        |
| **Mark**     | `mark INDEX`<br> e.g., `mark 2`                                                                                            |
| **Unmark**   | `unmark INDEX`<br> e.g., `unmark 2`                                                                                        |
| **Find**     | `find KEYWORD`<br> e.g., `find homework`                                                                                   |
| **List**     | `list`                                                                                                                     |
| **Bye**      | `bye`                                                                                                                      |