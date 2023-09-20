# ___Birdy User Guide___

Birdy is a **desktop chat-bot application for managing your tasks optimised for use on a Command Line Interface (CLI)**
or on a **Graphical User Interface (GUI)**.

- [Quick Start](#quick-start)
- [Features](#features)
  - [Viewing help: `help`](#viewing-help-help)
  - [Adding a task: `todo`/`deadline`/`event`](#adding-a-task-todo-deadline-event)
  - [Marking a task: `mark`/`unmark`](#marking-a-task-mark-unmark)
  - [Listing tasks: `list`](#listing-tasks-list)
  - [Deleting a task: `delete`](#deleting-a-task-delete)
  - [Setting a reminder: `remind`](#setting-a-reminder-remind)
  - [Listing reminders: `reminder`](#listing-reminders-reminder)
  - [Finding a task by keyword: `find`](#finding-a-task-by-keyword-find)
- [Command summary](#command-summary)

<br/> 

## Quick Start
1. Ensure you have Java `11` or above installed on your computer.
2. Download the latest `Duke.jar` from [here]()
3. Copy the file to the folder of your choice as the _home folder_ for your Birdy chat-bot.
4. Open your command terminal, `cd` into the folder with your .jar file, then run the command to start the application
    ```
    java -jar ip.jar
    ```
5. Type a command in the text box and press Enter to execute it. e.g. typing `help` and pressing Enter will display help
and feature information.
   - `list`: List all existing tasks.
   - `todo Learn how to use Birdy`: Adds a todo task of `Learn how to use Birdy` to the tasks list.
   - `delete 1`: Delete the first task shown in the current tasks list.
   - `mark 2`: Marks the second task shown in the current tasks list as completed.
6. Refer to the [Features](#features) below for more features and details.

___
## Features 
> **Note**
> 
> Notes about the command format:
> - Commands that do not take in parameters: `help`, `list`, `reminder`
> 
> 
> - Words in `UPPER_CASE` are the parameters to be supplied by the user.
>
>    e.g. in `todo TODO_TASK`, `TODO_TASK` is a parameter and describes the task of the todo. 
> 
> 
> - Datetime are strictly in the format `dd/MM/yyyy hh:mm`. 
> 
>   e.g. in `deadline /by 01/01/2000 11:00` creates a deadline 
task with due date of `01/01/01 11:00`.

### Viewing help: `help`
Shows a message with the help and features instructions.

Format: `help`

<br/> 

### Adding a task: `todo`/`deadline`/`event`
1. **Adding a todo task:** `todo TODO`
    
    Add a todo task of `TODO` to the tasks list.
 
    Format: `todo TODO`

2. **Adding a deadline task:** `deadline DEADLINE /by DUE_DATE`

    Add a deadline of `DEADLINE` with the due date `DUE_DATE`.

    Format: `deadline DEADLINE /by DUE_DATE`

   > **Note**
   > 
   > `DEADLINE` is strictly in the format `dd/MM/yyyy HH:mm`.
   
3. **Adding a event task:** `event EVENT /from START /to END`

    Add an event of `EVENT` starting at `START` and ending at `END`.

    Format: `event EVENT /from START /to END`
    
    > **Note**
   > 
   > `START` and `END` are strictly in the format `dd/MM/yyyy HH:mm`. 
   
Examples:
- `todo Buy groceries`
- `deadline Lecture quiz /by 11/11/2020 23:59`
- `event Summer camp /from 06/06/2021 08:00 /to 08/06/2021 15:00`

<br/> 

### Marking a task: `mark`/`unmark`
Marks or unmarks a task in the tasks list at `INDEX`.

Format: `mark INDEX`, `unmark INDEX`

<br/> 

### Listing tasks: `list`
Shows a list of all tasks in the tasks list.

Format: `list`

<br/> 

### Deleting a task: `delete`
Deletes a task in the tasks list at `INDEX`.

Format: `delete INDEX`

Examples:
- `list` followed by `delete 3` deletes the 3rd task 
in the tasks list.

<br/> 

### Setting a reminder: `remind`
Sets a task in the tasks list at `INDEX` as a reminder.

Format: `remind INDEX`

<br/> 

### Listing reminders: `reminder`
Shows a list of all reminders in the tasks list.

Format: `remind`

<br/> 

### Finding a task by keyword: `find`
Finds the tasks with description matching the `KEYWORD`

Format: `find KEYWORD`

Example:
- `find CS2100` returns `CS2100 Quiz 5` and `CS2100 Lecture`

<br/> 

## Command Summary
|      Action       |                                        Format                                         |
|:-----------------:|:-------------------------------------------------------------------------------------:|
|      **Add**      | `todo TODO`<br/>`deadline DEADLINE /by DUEDATE`<br/>`event EVENT /from START /to END` |
|    **Delete**     |                                    `delete INDEX`                                     |
|     **Find**      |                                    `find KEYWORD`                                     |
|     **Help**      |                                        `help`                                         |
|     **List**      |                                        `list`                                         |
| **Mark / Unmark** |                           `mark INDEX`<br/> `unmark INDEX`                            |
|   **Reminder**    |                                      `reminder`                                       |
|    **Remind**     |                                    `remind INDEX`                                     |
