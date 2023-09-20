# User Guide for ChatBot

Welcome back to your personal chat bot! It can help you manage a task list with CLI-like commands.

The project name is **"ChatBot"** and the name of the bot is **"WX-78"**.



## Features 

### Create tasks - `todo`, `deadline`, `event`

You can create three different types of tasks, specifying task description, and optionally, task deadline or period. 

**Format:**

`todo <description>`

`deadline <description> /by <deadline>`

`event <description> /from <from> /to <to>`

**Example usage:**

`event group meeting /from 1pm /to 4pm`

**Expected output:**

```
Got it. I've added this task:
[E][ ] [L] group meeting (from: 1pm to: 4pm)
Now you have 3 tasks in the list
```



### List tasks - `list`

You can list everything in your task list.

**Format:**

`list`

**Expected output:**

```
1.[T][ ] (H) 2103T tP user guide
2.[D][ ] (H) 2109S problem set (by: Saturday)
3.[E][ ] (L) group meeting (from: 1pm to: 4pm)
```



### Mark tasks - `mark`, `unmark`

You can mark your tasks as complete or incomplete.

**Format:**

`mark <index>`

`unmark <index>`

**Example usage:**

`mark 2`

**Expected output:**

```
Nice! I've marked this task as done:
[D][X] (H) 2109S problem set (by: Saturday)
```



### Delete tasks - `delete`

Unwanted tasks can be removed from the task list.

**Format:**

`delete <index>`

**Example usage:**

`delete 2`

**Expected output:**

```
Noted. I've removed this task:
[D][X] (H) 2109S problem set (by: Saturday)
Now you have 2 tasks in the list.
```



### Find tasks - `find`

You can search your tasks based on task description.

**Format**:

`find <search string>`

**Example usage:**

`find 2103T`

**Expected output:**

```
Here are the matching tasks in your list:
1.[T][ ] (H) 2103T tP user guide
```



### Prioritise tasks - `priority`

You can assign the priority of each task to be High, Medium or Low.

**Format:**

`priority <index> <priority>`, where `<priority>` is one of `{H, M, L}`

**Example usage**:

`priority 2 M`

**Expected output:**

```
Got it. I've set the priority of this task:
[E][ ] (M) group meeting (from: 1pm to: 4pm)
```
