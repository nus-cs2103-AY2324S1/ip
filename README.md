# Duke project template

This is a chatbot project named Kniaz, patterned after the [Duke Project template](https://github.com/se-edu/duke).

Kniaz being a romanisation of a Eastern European title analogous to the title of Duke, hence the name.

## How to use Kniaz

Download the latest release from the github repo of Kniaz

Ensure you have Java 11 [installed.](https://www.oracle.com/sg/java/technologies/javase/jdk11-archive-downloads.html)

Run the .jar file as per these [instructions.](https://docs.oracle.com/javase/tutorial/deployment/jar/run.html)

You should see Kniaz open!

The list of commands, and their descriptions are :

* `todo {task}` : Adds a todo (a task without a date) with name `task`

* `deadline {task} /by {time}` Adds a deadline with name  `task` with deadline `time`.
Parses according to the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) time format. 
Just stores your time as a string if not in this format.

* `event {task} /from {time_from} /to {time_to}` Adds an event with name `task` that starts from `time_from` and lasts until `time_to`.
  Parses according to the [ISO 8601](https://en.wikipedia.org/wiki/ISO_8601) time format.
  Just stores your time as a string if not in this format.
* `list` Lists all the added tasks in index order. (1-indexed, meaning the first task has an index of 1.)
* `mark {index}` Marks the task at `index` as done.
* `unmark {index}` Un-marks the task at `index`.
* `delete {index}` Deletes the task at `index`.
* `tag {index} {tag}` Tags the task at `index` with tag `tag`. Tasks can have an arbitrary number of tags.
* `find {search}` Searches all task names and task tags for your search term, `search`. Prints a list containing all found tasks.

Do note most commands will give you immediate feedback when you key them in, so you always know when a command works or fails.