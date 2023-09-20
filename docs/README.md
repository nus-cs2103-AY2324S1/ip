<!-- @@author {neyapraveen}-reused -->
<!-- Adapted from {hsiaotingluv} -->
# User Guide

This repo contains the code for Bot chatbot.

🤖Bot🤖 is a desktop chatbot application for managing and storing tasks,
with a Graphical User Interface (GUI).

Start chatting with Bot to achieve peak organization

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `ip2103-v3.4.0.jar` from [here]().

3. Double-click the file to start the app.

4. Try entering `help` and `list` commands. You should see the GUI as below.
   ![Image of ](Ui.png)

6. Type the command in the command box and press Enter to execute it.
   e.g. typing **`help`** and pressing Enter will show you the list of commands.<br>
   Some example commands you can try:

   🤖 **`help`**: Shows all available command lines.<br>

   🤖 **`todo`**`complete assignment`: Adds a todo task `complete assignment` to the list.<br>

   🤖 **`list`**: Lists all the tasks in the list.<br>

   🤖 **`bye`**: Exits the program.<br>

7. Refer to the [Features](#features) below for more details on each command.

--------------------------------------------------------------------------------------------------------------------

## Features
<div markdown="block" class="alert alert-info">

**:quick note: Notes about the command format:**<br>

* Words in [ ] are the parameters the user needs to enter. <br>
  e.g. in `todo [task]`, `task` is a parameter e.g. `todo quiz`.

</div>

### 🤖 Add a todo task: `todo`

Adds a todo task to the list.<br>
Format: `todo [task]`

### 🤖 Add a deadline task: `deadline`

Adds a deadline task to the list.<br>
Format: `deadline [task] /by [dd-mm-yyyy] HHmm`

### 🤖 Add an event task: `event`

Adds an event task to the list.<br>
Format: `event [task] /from [dd-mm-yyyy] HHmm /to [dd-mm-yyyy] HHmm`

--> For deadline and event, if time is not specified, 
it will be stored as default 2359

### 🤖 List all tasks: `list`

Lists all the tasks in the list.<br>
Format: `list`

### 🤖 Mark task: `mark`

Marks a task with the specified index in the list as done.<br>
Format: `mark [index]` 

* The index refers to the index number of task in the list.
* The index must be valid and positive

### 🤖 Delete task: `delete`

Deletes a task with the specified index in the list.<br>
Format: `delete [index]`

* The index refers to the index number of task in the list.
* The index must be valid and positive

### 🤖 Find tasks: `find`

Finds all tasks with matching keyword.<br>
Format: `find [keyword]`

* Finds all tasks containing the `keyword` String.
* Case insensitive

### 🤖 View help: `help`

Shows a list of the available commands of Bot.<br>
Format: `help`

### 🤖 Exit program: `bye`

Displays farewell message.<br>
Format: `bye`

### 🤖 Save data

--------------------------------------------------------------------------------------------------------------------
### FAQ

**Q:** Do I have to save my data manually each time I use Bot? <br>

**A:** Bot saves data automatically in the local storage each time your
data is modified. There is no need to do this manually!

--------------------------------------------------------------------------------------------------------------------
## Known issues
1. Two tasks of the same type cannot have identical [task] descriptions. 
The duplicate task will not be reloaded when you rerun Bot. The remedy
is to ensure that tasks of the same type e.g., two events, do not have
the same name

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Todo** | `todo [task]` <br> e.g., `todo complete quiz`
**Deadline** | `deadline [task] /by [dd-mm-yyyy] HHmm` <br> e.g., `deadline submit iP /by 22/09/2023`
**Event** | `event [task] /from [dd-mm-yyyy] HHmm /to [dd-mm-yyyy] HHmm` <br> e.g., `event zoom call /from 22/09/2023 1600 /to 22/09/2023 1800`
**List** | `list`
**Mark** | `mark [index]`<br> e.g., `mark 3`
**Unmark** | `unmark [index]`<br> e.g., `unmark 1`
**Delete** | `delete [index]`<br> e.g., `delete 2`
**Find** | `find [keyword]`<br> e.g., `find th`
**Help** | `help`
**Exit** | `bye`
