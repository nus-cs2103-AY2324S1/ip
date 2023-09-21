# BanterBot User Guide
>"Banter is often a proof of want of intelligence." - Jean de la Bruyere [(source)](https://www.azquotes.com/quotes/topics/banter.html)

Banterbot is a command line based task manager for users to free their mind of having to remember things they need to do. 
It combines the advantage of fast typing speeds of users via the Command Line Interface (CLI) and the benefits of a Graphical
Unit Interface (GUI).

## Features 

- [Create and Delete Tasks (Todos, Deadlines, and Events)](https://weeweh.github.io/ip/#create-and-delete-tasks)
- [Mark and Unmark Tasks (Todos, Deadlines, and Events)](https://weeweh.github.io/ip/#mark-and-unmark-tasks)
- [Search for specific Tasks](https://weeweh.github.io/ip/#search-for-specific-tasks)
- [Find what Deadlines are due](https://weeweh.github.io/ip/#find-what-deadlines-are-due)
- [Get reminders on upcoming Deadlines](https://weeweh.github.io/ip/#get-reminders-on-upcoming-deadlines)

### Create and Delete Tasks

Users can add specific Task into their TaskList or choose to remove them.

### Mark and Unmark Tasks

Users can mark Tasks as completed or undo them by unmarking them.

### Search for specific Tasks

Users can find Tasks containing their specified keyword.

### Find what Deadlines are due

Users can list out what Deadlines are due on their specified day.

### Get reminders on upcoming Deadlines

Users can remind themselves on deadlines due in the next three days.

## Usage

### `todo` - Adding a Todo

Adds a Todo to the TaskList.

Format: `todo [description]`

Example:
```
todo return book to library
```

### `deadline` - Adding a Deadline

Adds a Deadline to the TaskList.

Format: `deadline [description] /by [date] (YYYY-MM-DD)`

Example:
```
deadline borrow book /by 2022-12-01
deadline return book to library /by 2023-09-17
```
### `event` - Adding an Event

Adds an Event to the TaskList.

Format: `event [description] /from [date] (YYYY-MM-DD) /to [date]
(YYYY-MM-DD)`

Example:
```
event Music Festival /from 2023-11-09 /to 2023-11-11
```

### `list` - Viewing all Tasks

Lists out all Events in the TaskList.

Format: `list`

Example:
```
list
```

### `remind` - Viewing all Deadlines due soon

Lists out all deadlines due in the next 3 days.

Format: `remind`

Example:
```
remind
```

### `mark` - Marking Tasks as completed

Marks a Task at the given index as completed

Format: `mark [index]`

Example:
```
mark 4
```

### `unmark` - Unmarking Tasks as completed

Unmarks a Task at the given index as completed

Format: `unmark [index]`

Example:
```
unmark 4
```

### `delete` - Deleting Tasks

Deletes a Task at the given index

Format: `delete [index]`

Example:
```
delete 4
```

### `due` - Finding due Deadlines

Lists all Deadline due on a given date.

Format: `due [date] (YYYY-MM-DD)`

Example:
```
due 2023-09-17
```

### `find` - Finding Tasks containing keyword

Lists all Tasks whose description contains the keyword.

Format: `find [keyword]`

Example:
```
find book
```