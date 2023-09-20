# 📃 ChatALot User Guide

### Create and manage different types of tasks with ease!

## Summary 

1. [Initial setup](#initial-setup)
2. [list - List out all of your current tasks](#list---list-out-all-of-your-current-tasks)
3. [stats - Get some statistics of your tasks](#stats---get-some-statistics-of-your-tasks)
4. [find - Find a particular task by a keyword](#find---find-a-particular-task-by-a-keyword)
5. [mark - Mark a task as completed](#mark---mark-a-task-as-completed)
6. [unmark - Mark a task as incomplete](#unmark---mark-a-task-as-incomplete)
7. [delete - Delete a task](#delete---delete-a-task)
8. [todo - Create a todo task](#todo---create-a-todo-task)
9. [deadline - Create a deadline task](#deadline---create-a-deadline-task)
10. [event - Create an event task](#event---create-an-event-task)
11. [bye - End the program](#bye---end-the-program)

## Usage and Features

### Initial setup

1. Ensure Java 11 is installed in your computer.
2. Download the latest jar file from [here](https://github.com/Chandan8186/ip/releases) and move it to your preferred location.
   1. Your created tasks will be stored in `data/duke.txt` in this location.
   2. Every update made to the task list will be saved immediately.
3. Open a terminal/console and move to the preferred directory.
4. Run `java -jar duke.jar`.
5. Start chatting!

⚠️ WARNING: 

Please do not edit `duke.txt` yourself. If ChatALot cannot parse its contents, it will clear all existing data.

After the data is cleared, a warning message will be displayed on your terminal/console.


### `list` - List out all of your current tasks
Back to [Summary](#summary)
#### Input:

`list`

#### Sample Response: 

```
Here are the tasks in your list:
1. [T][ ] Assignment 1
2. [E][ ] Lecture (from: 01-Jan-2023 1200 to: 01-Jan-2023 1400)
```


### `stats` - Get some statistics of your tasks
Back to [Summary](#summary)
#### Description:

Shows some statistics of your tasks, namely the number of completed and incomplete tasks as well as lists of the incomplete deadline and event tasks due on the current day.

#### Input:

`stats`

#### Sample Response:

```
Number of completed tasks: 1

Number of incomplete tasks: 3

Deadline tasks due today:
[D][ ] Assignment (by: 17-Sep-2023 1800)


Event tasks due today:
[E][ ] Lecture (from: 17-Sep-2023 1200 to:
17-Sep-2023 1400)
```


### `find` - Find a particular task by a keyword
Back to [Summary](#summary)
#### Description:

Shows the list of tasks with description containing the specified keyword (case-sensitive).

#### Input:

`find <keyword>`

#### Sample Input:

`find Lect`

#### Sample Response:

```
Here are the matching tasks in your list:
1. [E][X] CS2103T Lecture (from:
15-Sep-2023 1600 to: 15-Sep-2023 1800)
2. [E][ ] Lecture (from: 17-Sep-2023 1200 to:
17-Sep-2023 1400)
```


### `mark` - Mark a task as completed
Back to [Summary](#summary)
#### Input:

`mark <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`mark 1`

#### Sample Response:

```
Nice! I've marked this task as done:
  [E][X] CS2103T Lecture (from:
15-Sep-2023 1600 to: 15-Sep-2023 1800)
```


### `unmark` - Mark a task as incomplete
Back to [Summary](#summary)
#### Input:

`unmark <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`unmark 1`

#### Sample Response:

```
OK, I've marked this task as not done yet:
  [E][ ] CS2103T Lecture (from:
15-Sep-2023 1600 to: 15-Sep-2023 1800)
```


### `delete` - Delete a task
Back to [Summary](#summary)
#### Input:

`delete <index of task>`

#### Requirements:

- Task list must be non-empty
- Index must range from 1 to the size of the task list

#### Sample Input:

`delete 1`

#### Sample Response:

```
Noted. I've removed this task:
  [E][ ] CS2103T Lecture (from:
15-Sep-2023 1600 to: 15-Sep-2023 1800)
Now you have 3 tasks in the list.
```


### `todo` - Create a todo task
Back to [Summary](#summary)
#### Description:

Creates a todo task, containing a task description.

#### Input:

`todo <task description>`

#### Requirements:

- Task description must be non-empty

#### Sample Input:

`todo Tutorial`

#### Sample Response:

```
Got it. I've added this task:
  [T][ ] Tutorial
Now you have 4 tasks in the list.
```


### `deadline` - Create a deadline task
Back to [Summary](#summary)
#### Description:

Creates a deadline task, containing a task description and a deadline date/time.

#### Input:

`deadline <task description> /by <deadline date/time>`

#### Requirements:

- Task description must be non-empty
- Deadline date/time must be non-empty, with format: `yyyy-MM-dd HHmm`

#### Sample Input:

`deadline Assignment /by 2023-09-20 1900`

#### Sample Response:

```
Got it. I've added this task:
  [D][ ] Assignment (by: 20-Sep-2023 1900)
Now you have 5 tasks in the list.
```


### `event` - Create an event task
Back to [Summary](#summary)
#### Description:

Creates an event task, containing a task description, a start and end date/time.

#### Input:

`event <task description> /from <start date/time> /to <end date/time>`

#### Requirements:

- Task description must be non-empty
- Start and end date/time must be non-empty, with format: `yyyy-MM-dd HHmm`

#### Sample Input:

`event Lecture /from 2023-09-20 1900 /to 2023-09-20 2100`

#### Sample Response:

```
Got it. I've added this task:
  [E][ ] Lecture (from: 20-Sep-2023 1900 to:
20-Sep-2023 2100)
Now you have 6 tasks in the list.
```


### `bye` - End the program
Back to [Summary](#summary)
#### Description:

Signs off and closes the chatbot window automatically.

#### Input:

`bye`

#### Response:

```
Bye. Hope to see you again soon!
This window will close shortly.
```