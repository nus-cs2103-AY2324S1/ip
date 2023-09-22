# B055man User guide!

## Information on the chatbot functions

### Feature list

- priority
- bye
- todo
- delete
- event
- deadline
- list
- mark
- unmark
- find

## Usage

### `priority` - changes the priority of a task

Takes a priority level from 1-3 and changes the selected task's priority and writes data into file.
Priority is set as follows: 

1 : LOW 

2 : MEDIUM

3 : HIGH

Format:

`priority TASK_NUMBER PRIORITY_LEVEL`

Example of usage: 

`priority 1 3`

Expected outcome:

Task selected has its order changed to the desired level and the following message is displayed

```
Noted. I've updated the priority of this task:[T] [X] study HIGH
```
____________
### `bye` - exits the chatbot

Exit command to tell the chatbot goodbye

Example of usage:

`bye`

Expected outcome:

closes the chatbot

```
Bye. Hope to See you again soon!
```
____________
### `todo` - adds a new ToDo task

Enter a description to add a new todo to the task list.

Format:

`todo TASK_DESCRIPTION`

Example of usage:

`todo homework`

Expected outcome:

A new todo task is added into the tasklist and saved in a text file.

```
Got it. I've added this task: [T] [] homework LOW. Now you have 1 tasks in the list.
```
____________
### `delete` - Deletes a task from the list

Enter a task number in the list and it will be deleted from the task list and file.

Format:

`delete TASK_NUMBER`

Example of usage:

`delete 1`

Expected outcome:

The task in the list with task number is deleted.

```
Noted. I've removed this task:[T][] homework LOW. Now you have 2 task(s) in the list
```
____________
### `event` - Adds a new event task

Enter an event description with its start and end time to add a new event to the task list.

Format:

`event DESCRIPTION /from YYYY-MM-DD (HHHH) /to YYYY-MM-DD (HHHH)`

Example of usage:

`event PARTY /from 2023-05-05 1600 /to 2023-05-05 1800`

Expected outcome:

A new event task is added into the tasklist and saved in a text file.

```
Got it. I've added this task:[E] [] party LOW (from: 05 May 2023 1600 to: 05 May 2023 1800). Now you have 4 tasks in the list.
```
____________
### `deadline` - Adds a new deadline task

Enter a deadline description with its due date to add a new deadline to the task list.

Format:

`deadline DESCRIPTION /by YYYY-MM-DD (HHHH)`

Example of usage:

`deadline quiz /by 2023-05-05`

Expected outcome:

A new deadline task is added into the tasklist and saved in a text file.

Description of the outcome.

```
Got it. I've added this task: [D] [] quiz LOW (by: 05 May 2023). Now you have 5 tasks in the list
```
____________
### `list` - Lists the task in the tasklist

This command helps to display all the tasks in currently stored.

Format:

`list`

Example of usage:

`list`

Expected outcome:

A list of all current tasks is shown

```
1. [T] [ ] study MEDIUM
2. [T] [ ] homework LOW
3. [T] [ ] borrow book LOW
4. [E] [ ] party LOW (from: 05 May 2023 1600 to: 05 May 2023 1800)
```
____________
### `mark` - Marks a task as complete

Enter a task number to mark that task as completed

Format:

`mark TASK_NUMBER`

Example of usage:

`mark 1`

Expected outcome:

Task chosen is marked as complete and the following message is displayed

```
Nice! I've Marked this task as done:
[T] [X] study MEDIUM
```
____________
### `unmark` - Unmarks a task as complete

Enter a task number ot mark that task as incomplete

Format:

`unmark TASK_NUMBER`

Example of usage:

`unmark 2`

Expected outcome:

Task chosen is unmarked as complete and the following message is displayed

```
OK, I've marked this task as not done yet:
[T] [] homework LOW
```
____________
### `find` - Finds tasks with a keyword

Displays all tasks that contain the keyword whether whole or partial

Format:

`find KEYWORD`

Example of usage:

`find home`

Expected outcome:

A list containing tasks that have description containing the keyword is displayed

```
1.[T] [] homework LOW
```

