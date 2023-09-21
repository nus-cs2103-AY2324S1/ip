# Sori :)

Sori is an app with a tasklist of your tasks to do. To start using Duke, you can:

- Download the `.jar` file under `Releases`,
- Go to the directory in terminal,
- run `java -jar duke.jar`.

## Features 

Sori has the following functions:

* Add and Delete 3 types of tasks
    * Todo
    * Events
    * Deadline
* Mark/Unmark tasks
* Finding tasks with given key

## User Guide 

### - `list`

Display a list of tasks at that moment.

#### Example

`list`

```
> list
Here are the tasks in your list:
    1. [T][ ] Study for midterms
	2. [D][ ] IP deadline (by 22 09 2023 23:59)
	3. [E][ ] Codeforces Div2 (from 22 09 2023 22:35 to 23 11 2023 00:35)
```

### - `todo`

Create a todo task.

#### Example

`todo <description>`

```
> todo Study for midterms
added:
	[T][ ] Study for midterms
You have 1 tasks in the list.
```

### - `deadline`

Create a deadline task,

#### Usage

`deadline <description> /by <deadline>`

`<deadline>` should be in the form of `dd/MM/yyyy HHmm`.

```
> deadline IP deadline /by 22/09/2023 2359
added:
	[D][ ] IP deadline (by 22 09 2023 2359)
You have 2 tasks in the list.
```

### - `event`

Create an event task.

#### Usage

`event <description> /from <start> /to <end>`

`<start>` and `<end>` should be `dd/MM/yyyy HHmm`.

```
> event Codeforces Div2 /from 22/09/2023 2235 /to 23/11/2023 0035
added:
	[E][ ] Codeforces Div2 (from 22 09 2023 2235 to 23 11 2023 0035)
You have 3 tasks in the list.
```


### - `mark`

Mark a task as completed.

#### Example

`mark <index>`

```
> mark 3
Nice! I've marked this task as done!
	[E][X] Codeforces Div2 (from 22 09 2023 2235 to 23 11 2023 0035)
```


### - `unmark`

Mark a task as undone.

#### Example

`unmark <index>`

```
> done 3
OK, I've marked this task as not done yet!
	[E][ ] Codeforces Div2 (from 22 09 2023 2235 to 23 11 2023 0035)
```


### - `delete`

Delete task at given index.

#### Example

`delete <index>`

```
> delete 2
Noted. I've removed this task:
	[D][ ] IP deadline (by 22 09 2023 23:59)
Now you have 2 tasks in the list.
```

### - `find`

Search a task with input key.

#### Example

`find <key>`

```
> find Study
Here are the tasks in your list:
	1. [T][ ] Study for midterms

```

### - `bye`

Quit.

#### Example

`bye`

```
> bye
Bye, hope to see you again soon!
```