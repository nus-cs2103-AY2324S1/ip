# Kora User Guide
> Kora is an English speaking Korean bot in Singapore! It can help you keep track of todos, deadlines, events and many more!

## Table of Content
1. [Getting Started](https://dlathyun.github.io/ip/#getting-started)
2. [Commands](https://dlathyun.github.io/ip/#commands)
    - [Task Related Command](https://dlathyun.github.io/ip/#task-related-command)
      - [ToDo](https://dlathyun.github.io/ip/#adding-todo-todo)
      - [Deadline](https://dlathyun.github.io/ip/#adding-deadline-deadline)
      - [Event](https://dlathyun.github.io/ip/#adding-event-event)
      - [Mark](https://dlathyun.github.io/ip/#marking-task-mark)
      - [Unmark](https://dlathyun.github.io/ip/#unmarking-task-unmark)
      - [Delete](https://dlathyun.github.io/ip/#deleting-task-delete)
      - [List](https://dlathyun.github.io/ip/#listing-task-list)
      - [Find](https://dlathyun.github.io/ip/#finding-task-find)
    - [File Related Command](https://dlathyun.github.io/ip/#file-related-command)
      - [Load](https://dlathyun.github.io/ip/#loading-file-load)
      - [Change](https://dlathyun.github.io/ip/#changing-file-change)
    - [Command Related Command](https://dlathyun.github.io/ip/#command-related-command)
      - [Display](https://dlathyun.github.io/ip/#displaying-command-list-display)
      - [Set](https://dlathyun.github.io/ip/#setting-command-name-set)
      - [Unset](https://dlathyun.github.io/ip/#unsetting-command-name-unset)
    - [General Command](https://dlathyun.github.io/ip/#general-command)
      - [Help](https://dlathyun.github.io/ip/#viewing-help-help)
      - [Bye](https://dlathyun.github.io/ip/#exiting-bye)
3. [Command Summary](https://dlathyun.github.io/ip/#command-summary)

## Getting Started
1. Download Kora JAR file from [this page](https://github.com/dlathyun/ip/releases)
2. Start using!

## Commands
### Task Related Command
#### Adding ToDo: `todo`
Adds a todo task.

Format: `todo [description]`

Example: `todo assignment`

![todo](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/todo.png)
#### Adding Deadline: `deadline`
Adds a deadline task.

Format: `deadline [description] /by [due date YYYY-MM-DD HH:mm]`

Example: `deadline ip /by 2023-09-22 23:59`

![deadline](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/deadline.png)
#### Adding Event: `event`
Adds an event task.

Format: `event [description] /from [start YYYY-MM-DD HH:mm] /to [start YYYY-MM-DD HH:mm]`

Example: `event cs2103t exam /from 2023-12-01 09:00 /to 2023-12-01 11:00`

![event](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/event.png)
#### Marking task: `mark`
Marks a task to be done.

Format: `mark [task index]`

Example: `mark 2`

![mark](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/mark.png)
#### Unmarking task: `unmark`
Unmarks a task to be undone.

Format: `unmark [task index]`

Example: `unmark 2`

![unmark](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/unmark.png)
#### Deleting task: `delete`
Deletes a task.

Format: `delete [task index]`

Example: `delete 1`

![delete](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/delete.png)
#### Listing task: `list`
Lists all tasks from task list.

Format: `list`

Example: `list`

![list](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/list.png)
#### Finding task: `find`
Finds tasks by keyword.

Format: `find [keyword]`

Example: `find cs`

![find](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/find.png)
### File Related Command
#### Loading file: `load`
Loads content from specified file.

Format: `load [file name]`

Example: `load y2s1`

![load](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/load.png)
#### Changing file: `change`
Changes current working file.

Format: `change [file name]`

Example: `change y2`

![change](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/change.png)
### Command Related Command
#### Displaying command list: `display`
Displays all commands from command list.

Format: `display`

Example: `display`

![display](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/display.png)
#### Setting command name: `set`
Set new command name for specified command type.

Format: `set [command type] [command name]`

Example: `set todo td`

![set](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/set.png)
#### Unsetting command name: `unset`
Deletes specified command name for specified command type.

Format: `unset [command type] [command name]`

Example: `unset todo td`

![unset](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/unset.png)
### General Command
#### Viewing help: `help`
Shows all available commands.

Format: `help`

Example: `help`

![help](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/help.png)
#### Exiting: `bye`
Exits the KoraBot.

Format: `bye`

Example: `bye`

![bye](https://github.com/dlathyun/ip/raw/master/src/main/resources/images/bye.png)

## Command Summary

| Command    |                                      Format                                       |
|------------|:---------------------------------------------------------------------------------:|
| `todo`     |                               `todo [description]`                                |
| `deadline` |             `deadline [description] /by [due date YYYY-MM-DD HH:mm]`              |
| `event`    | `event [description] /from [start YYYY-MM-DD HH:mm] /to [start YYYY-MM-DD HH:mm]` |
| `mark`     |                                `mark [task index]`                                |
| `unmark`   |                               `unmark [task index]`                               |
| `delete`   |                               `delete [task index]`                               |
| `list`     |                                      `list`                                       |
| `find`     |                                 `find [keyword]`                                  |
| `load`     |                                `load [file name]`                                 |
| `change`   |                               `change [file name]`                                |
| `display`  |                                     `display`                                     |
| `set`      |                        `set [command type] [command name]`                        |
| `unset`    |                       `unset [command type] [command name]`                       |
| `help`     |                                      `help`                                       |
| `bye`      |                                      `bye`                                        |


![Have fun using Kora!](https://img.freepik.com/premium-vector/tiger-character-hanbok_279539-11.jpg)
