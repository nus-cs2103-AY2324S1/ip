# User Guide

Monke is a chatbot which also functions as a todo list to help you
remember tasks. It has a graphical user interface but is optimized for those familair with command line interfaces.
Interaction with the chatbot is through typed commands.

## Features

### Listing all tasks: `list`
Shows a list of all the tasks saved. Simply type `list` without any parameters to show the list.



### Adding a todo: `todo`
Adds a todo task to the list.


Format: `todo TASK`

Examples:
- `todo do project` Adds a task to the list with "do project" as the task description.
- `todo work` Adds a task to the list with "work" as the task description.

### Adding a deadline to the list: `deadline`

Adds a deadline task to the list.

Format: `deadline TASK /by yyyy-mm-dd hhmm`

Examples:
- `deadline finish ip /by 2024-12-02 1200` Adds a 
deadline to the list with a task description of "finish ip" and deadline of Dec 2 2024 12pm.
- `deadline tp /by 2025-06-23 0900` Adds a
  deadline to the list with a task description of "tp" and deadline of June 23 2025 9am.

### Adding an event to the list: `event`

Adds an event task to the list.

Format: `event TASK /from START /to END`

Examples:
- `event project meeting /from Aug 2 2pm /to 4pm` Adds an event with the task
description "project meeting" on Aug 2 from 2pm to 4pm.
- `event tutorial /from Monday 3pm /to 5pm` Adds an event with the task
description "tutorial" on Monday from 3pm to 5pm. 

### Marking a task as done: `mark`

Marks a task as done which is indicated by [x] next to the task.

Format: `mark INDEX`

- Marks the task at `INDEX` as done. Index refers to the index number
displayed on the task list. The index must be a positive number and must 
be on the list.

Examples:
- `mark 2` marks the task at index 2 as done.
- `mark 5` marks the task at index 5 as done.

### Marking a task as not done: `unmark`

Unmarks a task previously marked as done. An undone task is ndicated by [ ] 
shown next to the task.

Format: `unmark INDEX`
- Unmarks the task at `INDEX`. Index refers to the index number displayed on the
 task list. The index must be a positive number and must
  be on the list.

Examples:
- `unmark 2` marks the task at index 2 as done.
- `unmark 5` marks the task at index 5 as done.


### Deleting a task from the list: `delete`

Format: `delete INDEX`
- Deletes the task at `INDEX`. Index refers to the index number displayed on the
  task list. The index must be a positive number and must
  be on the list.

Examples:
- `delete 2` marks the task at index 2 as done.
- `delete 5` marks the task at index 5 as done.

### Finding a task in the list: `find`

Format: `find KEYWORD`

Finds tasks containing the keyword.

- The search is case-sensitive.
- Accepts keyphrase as well.
- Partial words can be matched.
- A list of the tasks found will be returned.
- Nothing will be displayed.

Examples:
- `find do` returns `do project` and `do tutorial`
- `find proj` returns `do project` and `project meeting`


### Postponing a deadline in the list: `snooze`
Postpones a deadline to a later date.

Format: `snooze INDEX yyyy-mm-dd hhmm`

- Can only be used on deadlines.
- Postpones the deadline at `INDEX`. Index refers to the index number displayed on the
  task list. The index must be a positive number and must
  be on the list.
- New date given must be in yyyy-mm-dd hhmm format.

Examples:
- `snooze 2 2026-11-03 1500` postpones the deadline at index 3 to Nov 3 2026 3pm.
- `snooze 5 2028-02-15 0800` postpones the deadline at index 5 to Feb 15 2028 8am.

### Saying bye to the bot: `bye`

Displays a bye message from Monke.

Format: `bye`

> Note: Does not exit the application.
