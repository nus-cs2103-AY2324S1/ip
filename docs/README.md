# User Guide

Dude is a desktop app for managing contacts, optimized for use via a Command Line Interface (CLI) 
while still having the benefits of a Graphical User Interface (GUI). 

- [Quick start](https://vivienherq.github.io/ip/#quick-start)
- [Features](https://vivienherq.github.io/ip/#features)
    - [Adding a todo: `todo`](https://vivienherq.github.io/ip/#adding-a-todo-todo)
    - [Adding a deadline: `deadline`](https://vivienherq.github.io/ip/#adding-a-deadline-deadline)
    - [Adding a event: `event`](https://vivienherq.github.io/ip/#adding-a-event-event)
    - [Adding a note: `note`](https://vivienherq.github.io/ip/#adding-a-event-event)
    - [Listing all notes and tasks: `list`](https://vivienherq.github.io/ip/#listing-all-tasks-list)
    - [Deleting a task: `delete`](https://vivienherq.github.io/ip/#deleting-a-task-delete)
    - [Deleting a note: `delete`](https://vivienherq.github.io/ip/#deleting-a-note-delete)
    - [Finding tasks and notes: `find`](https://vivienherq.github.io/ip/#finding-tasks-find)
    - [Marking a task: `mark`](https://vivienherq.io/ip/#marking-a-task-mark)
    - [Unmarking a task: `unmark`](https://vivienherq.github.io/ip/#unmarking-a-task-unmark)
    - [Exiting the program: `bye`](https://vivienherq.github.io/ip/#exiting-the-program-bye)
- [Command summary](https://vivienherq.github.io/ip/#command-summary)

## Features 

Words in UPPER_CASE are the parameters to be supplied by the user.
e.g. in `todo DESCRIPTION`, `DESCRIPTION` is a parameter which can be used as `todo submit project`.

Extraneous parameters for commands that do not take in parameters 
(such as `list` and `bye`) will be ignored.

### Adding a todo: `todo`

Adds a todo to the list of tasks.

Format: `todo DESCRIPTION`

Examples:
- `todo watch lecture`
- `todo attempt tutorial`

### Adding a deadline: `deadline`

Adds a deadline to the list of tasks.

Format: `deadline DESCRIPTION /by DD-MM-YYYY HH:mm`

Examples:
- `deadline quiz 6 /by 22-09-2023 16:00`
- `deadline individual project /by 22-09-2023 23:59`

### Adding an event: `event`

Adds an event to the list of tasks.

Format: `event DESCRIPTION /from DD-MM-YYYY HH:mm /to DD-MM-YYYY HH:mm`

Examples:
- `event practical exam /from 17-11-2023 16:00 /to 17-11-2023 18:00`
- `event final exam /from 01-12-2023 09:00 /to 01-12-2023 11:00`

### Adding a note: `note`

Adds a note to the list of notes.

Format: `note DESCRIPTION`

Examples:
- `note individual project deadline extended by 3 days`
- `note second deadline extension is possible`

### Listing all notes and tasks: `list`

Lists all notes and tasks.

Format: `list`

### Deleting a task: `delete`

Deletes a task (todo, deadline or event ) from the list of tasks.

Format: `delete /t INDEX`

Examples:
- `delete /t 1`
- `delete /t 2`

### Deleting a note: `delete`

Deletes a note from the list of notes.

Format: `delete /n INDEX`

Examples:
- `delete /n 1`
- `delete /n 2`

### Finding tasks and notes: `find`

Finds tasks or notes by keyword(s) from list of tasks and list of notes.

Format: `find KEYWORD(S)`

Examples:
- `find assignment`
- `find individual project`

### Marking a task: `mark`

Marks a task as done.

Format: `mark INDEX`

Examples:
- `mark 1`
- `mark 2`

### Unmarking a task: `unmark`

Marks a task as not done.

Format: `unmark INDEX`

Examples:
- `unmark 1`
- `unmark 2`

### Exiting the program: `bye`

Exits the programme

Format: `list`

## Command summary

| **Action** | **Format, Examples**                                                                                                                     |
|------------|------------------------------------------------------------------------------------------------------------------------------------------|
| todo       | `todo DESCRIPTION` <br> e.g. `todo watch lecture`                                                                                        |
| deadline   | `deadline DESCRIPTION /by DD-MM-YYYY HH:mm` <br> e.g. `deadline individual project /by 22-09-2023 23:59`                                 |
| event      | `event DESCRIPTION /from DD-MM-YYYY HH:mm /to DD-MM-YYYY HH:mm` <br> e.g. `event final exam /from 01-12-2023 09:00 /to 01-12-2023 11:00` |
| note       | `note DESCRIPTION` <br> e.g. `note individual project deadline extended by 3 days`                                                       |
| list       | `list`                                                                                                                                   |
| delete     | `delete [/t INDEX] [/n INDEX]` <br> e.g. `delete /t 1` `delete /n 2`                                                                     |
| find       | `find KEYWORD` <br> e.g. `find project`                                                                                                  |
| mark       | `mark INDEX` <br> e.g. `mark 1`                                                                                                          |
| unmark     | `unmark INDEX` <br> e.g. `unmark 2`                                                                                                      |
| bye        | `bye`                                                                                                                                    |
