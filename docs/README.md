# User Guide

Dukduk🦆 is a desktop chatbot application for managing and storing tasks,
with a Graphical User Interface (GUI).

Dukduk🦆 is your personal assistant! Start getting organised by talking to dukduk!

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `dukduk.jar` from [here](https://github.com/wnchan/ip/releases/latest).

3. Copy the file to the folder you want to use as the home folder for your dukduk chatbot.

4. Open a command terminal, `cd` into the folder you put the jar file in, and use the `java -jar dukduk.jar` command to run the dukduk chatbot.

5. Dukduk can help to find any deadlines or events. Try entering `find` command. You should see the GUI as below.

<div style="text-align:center;">
  <img src="Ui.png" alt="Image of Ui">
</div>

6. Type the command in the command box and press `Enter` or the `Send` button to execute it.

   Some example commands you can try:

    **`any command`**: Shows help message that explains what commands there are and how to use it.<br>

    **`todo`**`finish report`: Adds a todo task `finish report` to the list.<br>

    **`list`**: Lists all the tasks in the list.<br>

    **`find`**`report`: Finds and lists all the tasks in the list that matches with the keyword, `report`.<br>

    **`bye`**: Exits the program.<br>

7. Refer to the [Features](#features) below for more details on each command.

## Features 

<div class="alert alert-info">

**Note:**<br>

* Words in [ ] are the parameters that the user needs to enter. <br>
  e.g. in `todo [task]`, `task` is a parameter e.g. `todo homework`.

</div>

### 🦆 Add a todo task: `todo`

Adds a todo task to the list.<br>
Format: `todo [task]`

### 🦆 Add a deadline task: `deadline`

Adds a deadline task to the list.<br>
Format: `deadline [task] /by yyyy-mm-dd hhmm`

### 🦆 Add an event task: `event`

Adds an event task to the list.<br>
Format: `event [task] /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm`

### 🦆 List all tasks: `list`

Lists all the tasks in the list.<br>
Format: `list`

### 🦆 Mark task: `mark`

Marks a task with the specified task number in the list as done.<br>
Format: `mark [task number]`

* The task number must be valid and positive

### 🦆 Mark task: `unmark`

Marks a task with the specified task number in the list as not done.<br>
Format: `unmark [task number]`

* The task number must be valid and positive

### 🦆 Delete task: `delete`

Deletes a task with the specified task number in the list.<br>
Format: `delete [task number]`

* The task number must be valid and positive

### 🦆 Find tasks: `find`

Finds all tasks that matches the keyword.<br>
Format: `find [keyword]`

Finds all tasks containing the `keyword`. Possible searches includes:
* Case-insensitive search
* Partial word search
* Date / timing search

### 🦆 Exit program: `bye`

Displays farewell message.<br>
Format: `bye`

## FAQ

**Q:** How do I save my data? <br>

**A:** There is no need to save data manually. Your data will be automatically saved when you perform any command.

## Command summary

| Action       | Format, Examples                                                                                                              |
|--------------|-------------------------------------------------------------------------------------------------------------------------------|
| **Todo**     | `todo [task]` <br> e.g., `todo complete quiz`                                                                                 |
| **Deadline** | `deadline [task] /by yyyy-mm-dd hhmm` <br> e.g., `deadline submit report /by 2023-09-21 1800`                                 |
| **Event**    | `event [task] /from yyyy-mm-dd hhmm /to yyyy-mm-dd hhmm` <br> e.g., `event meeting /from 2023-09-21 1400 /to 2023-09-21 1600` |
| **List**     | `list`                                                                                                                        |
| **Mark**     | `mark [task number]` <br> e.g., `mark 1`                                                                                      |
| **Unmark**   | `unmark [task number]` <br> e.g., `unmark 2`                                                                                  |
| **Delete**   | `delete [task number]` <br> e.g., `delete 3`                                                                                  |
| **Find**     | `find [keyword]` <br> e.g., `find report`                                                                                     |
| **Exit**     | `bye`                                                                                                                         |`
