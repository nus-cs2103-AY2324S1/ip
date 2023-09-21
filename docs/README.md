
# EchoBot's User Guide

## Features 

### Managing Tasks

- Add tasks, including todo, deadline, and event
- Delete any unwanted task
- Mark any completed task
- Unmark task that accidentally got marked
- Find task
- View task list

### Managing Notes
- Add notes
- Delete unwanted note
- View note list

## Usage

### `todo` - Adding todo task

EchoBot will add your todo task into the task list.

Format: `todo DESCRIPTION`

Example of usage:

`todo Buy Groceries`

Expected outcome:

EchoBot will response as below,

```
Got it. I've added this task:
  [T] [] Buy Groceries
Now you have 1 tasks.
```

### `deadline` - Adding deadline task

EchoBot will add your deadline task with due date into the task list.

Format: `deadline DESCRIPTION /by DUE_DATE`
- The DESCRIPTION and DUE_DATE can't be empty.
- DUE_DATE need to follow this format, yyyy-MM-dd.

Example of usage: 

`deadline Peer Review /by 2023-09-25`

Expected outcome:

EchoBot will response as below,

```
Got it. I've added this task:
  [D] [] Peer Review (by: Sep 25 2023)
Now you have 2 tasks.
```
### `event` - Adding event task

EchoBot will add your event task with start date-time and end date-time into the task list.

Format: `event DESCRIPTION /from START_DATETIME /to END_DATETIME`
- The DESCRIPTION, START_DATETIME, and END_DATETIME can't be empty.
- START_DATETIME and END_DATETIME need to follow this format, yyyy-MM-dd HHmm.

Example of usage:

`event Meeting /from 2023-10-10 1100 /to 2023-10-10 1300`

Expected outcome:

EchoBot will response as below,

```
Got it. I've added this task:
  [E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
Now you have 3 tasks.
```
### `delete` - Deleting task

EchoBot will delete the specified task.

Format: `delete INDEX`
- Deletes the task at the specified INDEX.
- The INDEX refers to the index number shown in the displayed task list.
- The INDEX can't be empty.

Example of usage:

`delete 1`

Expected outcome:

EchoBot will response as below,

```
Noted. I've removed this task:
  [T] [] Buy Groceries
Now you have 2 tasks in the list.
```
### `mark` - Marking task

EchoBot will mark the specified task.

Format: `mark INDEX`
- Marks the task at the specified INDEX.
- The INDEX refers to the index number shown in the displayed task list.
- The INDEX can't be empty.

Example of usage:

`mark 1`

Expected outcome:

EchoBot will response as below,

```
Nice! I've marked this task as done:
[X] Peer Review (by: 2023-09-25)
```
### `unmark` - Unmarking task

EchoBot will unmark the specified task.

Format: `unmark INDEX`
- Unmarks the task at the specified INDEX.
- The INDEX refers to the index number shown in the displayed task list.
- The INDEX can't be empty.

Example of usage:

`unmark 1`

Expected outcome:

EchoBot will response as below,

```
OK, I've marked this task as not done yet:
[] Peer Review (by: 2023-09-25)
```
### `find` - Listing task by keyword

EchoBot will display the matching task from your task list.

Format: `find KEYWORD`
- The search is case-insensitive. e.g. meeting will match Meeting
- Only full words will be matched e.g. meet will not match meeting

Example of usage:

`find Meeting`

Expected outcome:

EchoBot will response as below,

```
Here are the matching tasks in your list:
    1. [E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
```
### `list task` - Listing all tasks

EchoBot will display all of your tasks.

Example of usage:

`list task`

Expected outcome:

EchoBot will response as below,

```
Here are the tasks in your list:
    1. [D] [] Peer Review (by: Sep 25 2023)
    2. [E] [] Meeting (from: Oct 10 2023 11:00 AM to: Oct 10 2023 13:00 PM)
```
### `note` - Adding note
EchoBot will add your note with title and content into the note list.

Format: `note TITLE::CONTENT`

Example of usage:

`note Dinner Menu::Grilled Fish + Mashed Potato + Veggies`

Expected outcome:

EchoBot will response as below,

```
Note added: Dinner Menu
Now you have 1 notes.
```
### `remove` - Deleting note

EchoBot will delete the specified note.

Format: `remove INDEX`
- Deletes the note at the specified INDEX.
- The INDEX refers to the index number shown in the displayed note list.
- The INDEX can't be empty.

Example of usage:

`remove 1`

Expected outcome:

EchoBot will response as below,

```
Noted. I've removed this note:
  Title: Dinner Menu
  Content: Grilled Fish + Mashed Potato + Veggies
Now you have 0 notes in the list.
```
### `list note` - Listing all notes

EchoBot will display all of your notes.

Example of usage:

`list note`

Expected outcome:

EchoBot will response as below,

```
Here are your saved notes:
    1. Title: Dinner Menu
       Content: Grilled Fish + Mashed Potato + Veggies
```
### `bye` - Ending the interaction with EchoBot

EchoBot will display farewell message and close the application.

Example of usage:

`bye`

Expected outcome:

EchoBot will response as below,

```
Bye. Hope to see you again soon!
```
