# Tasket
Tasket is a desktop app for managing your tasks. It utilizes the use of CLI (Command Line Interface)
while adhering to the benefits of GUI (Graphical User Interface). 

## Getting Started
1. Ensure you have Java `11` or above installed in your Computer.

2. Download the latest `Tasket.jar` from [here](https://github.com/Ken-Lai/ip/releases).

3. Copy the file to the folder you want to use as the home folder for your Tasket.

4. Double click `Tasket.jar` to run the application. A GUI will appear in a few seconds.

## Features 

> [!NOTE]
> - Words in uppercase are supplied by user<br>e.g. `todo DESCRIPTION`, DESCRIPTION is a parameter which can be used as `todo read book`.
> - Items in square brackets are optional<br>e.g. `todo DESCRIPTION [#TAGS]...` can be used as `todo read book #study` or `todo read book`.
> - Items with `...` in them can be used multiple times<br>e.g. `[#TAG]` can be used as ``, `#happy`, `#happy #sad`, etc.
> - Parameters must be in **exact order**<br>e.g `event DESCRIPTION /from START_TIME /to END_TIME` is not the same as<br>`event DESCRIPTION /to END_TIME /from START_TIME`

### Adding tasks: `todo` `deadline` `event`

### Adding a todo: `todo`

Adds a todo task.

Format: `todo DESCRIPTION [#TAGS]...`

Example: 

`todo read book` 

`todo bake cake #dessert`


<br>


### Adding a deadline: `deadline`

Adds a deadline task.

Format: `deadline DESCRIPTION /by DEADLINE [#TAGS]...`

Example: 

`deadline return book /by 25-09-2023` 

`deadline cs2103t assignment /by 22-09-2023 #urgent`


<br>


### Adding an event: `event`

Adds an event task.

Format: `event DESCRIPTION /from START_TIME /to END_TIME [#TAGS]...`

Example:

`event project meeting /from Sun 8pm /to 10pm`

`event orientation camp /from 04-08-2023 /to 06-08-2023 #freshmen`


<br>


### Listing all tasks: `list`

Lists all available tasks.

Format: `list`

`[T]` `[D]` `[E]` represents the type of tasks, while the second `[ ]` indicates if 
the task is done or not.

A done task is represented as `[X]`, otherwise `[ ]`


<br>


### Marking a task: `mark`

Marks a task as done.

Format: `mark INDEX`

Example: `mark 1`

The INDEX must be a **positive integer** 1, 2, 3, ...


<br>


### Unmarking a task: `unmark`

Marks a task as undone.

Format: `unmark INDEX`

Example: `unmark 1`

The INDEX must be a **positive integer** 1, 2, 3, ...


<br>


### Deleting a task: `delete`

Deletes a task from the list.

Format: `unmark INDEX`

Example: `unmark 1`

The INDEX must be a **positive integer** 1, 2, 3, ...


<br>


### Finding a task: `find`

Finds tasks matching keyword.

Format: `find KEYWORD`

Example: `find book`


<br>


### Exiting the program: `bye`

Exits the program.

Format: `unmark INDEX`

Example: `unmark 1`

The INDEX must be a **positive integer** 1, 2, 3, ...


<br>


## Saving
Tasket will automatically save the tasks into the hard drive after any command that will make changes to the task.
There's no need to save manually.

## Error handling
Whenever an error occurs, such as missing task description, Tasket will display the error message instead of result.

## Command summary

| Command  | Format                                            | Example                                                                                                                                 |
|----------|---------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------|
| todo     | `todo DESCRIPTION`                                | `todo read book`<br>`todo bake cake #dessert`                                                                                           |
| deadline | `deadline DESCRIPTION /by DEADLINE`               | `deadline return book /by 2023-09-01`<br>`deadline cs2103t assignment /by 2023-09-22 #urgent`                                           |
| event    | `event DESCRIPTION /from START_TIME /to END_TIME` | `event group meeting /from 2023-09-01 09:00 /to 2023-09-01 11:00`<br>`event orientation camp /from 04-08-2023 /to 06-08-2023 #freshmen` |
| list     | `list`                                            | `list`                                                                                                                                  |
| delete   | `delete INDEX`                                    | `delete 1`                                                                                                                              |
| mark     | `mark INDEX`                                      | `mark 2`                                                                                                                                |
| unmark   | `unmark INDEX`                                    | `unmark 2`                                                                                                                              |
| find     | `find KEYWORD`                                    | `find book`                                                                                                                             |
| bye      | `bye`                                             | `bye`                                                                                                                                   |
