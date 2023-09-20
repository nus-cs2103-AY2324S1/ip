# Ekud User Guide
(Reference taken from the [AB-3 User Guide](https://se-education.org/addressbook-level3/UserGuide.html#features))

## Quick Start
1. Ensure Java version 11 (* for macOS, Azul 11.0.20) is used.
2. Download `Ekud.jar` from [here](https://github.com/J-hta-n/ip/releases).
3. Run `java -jar Ekud.jar` on the terminal in the same directory as the jar file.

<img src="https://j-hta-n.github.io/ip/Ui.png" alt="Ekud chatbot" width=180>

## Features 

### Listing all tasks: `list`

Shows a list of all added tasks.

Format: `list`


### Marking a task as done: `mark`

Marks a specific task by its number (starting from 1) as completed.

Format: `mark <number>`

Example: `mark 3`

### Unmarking a task: `unmark`

Marks a specific task by its number (starting from 1) as not completed.

Format: `unmark <number>`

Example: `mark 2`


### Adding a todo task: `todo`

Adds a todo task with a description to the task list.

Format: `todo <description>`

Example: `todo get new pencil case`


### Adding a task with deadline: `deadline`

Adds a task with a description and its deadline to the task list.

Format: `deadline <description> /by <datetime>`
* datetime follows the format specifier `d MMM HHmm`, eg `20 Sep 1730`

Example: `deadline finish quiz /by 1 Oct 2359`

### Adding an event: `event`

Adds an event to be attended with a description and its start + end times to the task list.

Format: `event <description /from <datetime> /to <datetime>`
* datetime follows the format specifier `d MMM HHmm`, eg `20 Sep 1730`

Example: `event hackathon at expo /from 9 Oct 0700 /to 12 Oct 1830`

### Deleting a task: `delete`

Deletes an existing task by its number (starting from 1) from the task list.

Format: `delete <number>`

Example: `delete 1`


### Finding tasks: `find`

Finds existing tasks matching a given keyword and shows them as a list.

Format: `find <keyword>`

Example: `find groceries`


### Clearing all tasks: `clear`

Deletes all existing tasks from the task list.

Format: `clear`

### Restoring cleared tasks: `undoclear`

Restores all tasks deleted from the most recent `clear` command.

Format: `undoclear`


### Changing a task's priority: `priority`

Changes the priority of a specific task by its number (starting from 1).
Priority of tasks are set to medium by default upon creation.

Format: `priority <number> <priority_level>`
* There are three different priority_levels: `high`, `medium` or `low`

Example: `priority 3 high`

## Command summary

| Action | Format (+ Example)                                                                                          |
|--------|-------------------------------------------------------------------------------------------------------------|
| `list` | `list`                                                                                                      |
|`mark`| `mark <number>`                                                                                             |
|`unmark`| `unmark <number>`                                                                                           |
|`todo`| `todo <description>`<br/>eg `todo buy bread`                                                                |
|`deadline`| `deadline <description> /by <datetime>`<br/>eg `deadline essay draft /by 20 Jun 2359`                       |
|`event`| `event <description> /from <datetime> /to <datetime>`<br/>eg `event festival /from 3 Mar 1000 /to 5 Mar 1200` |
|`find`| `find <keyword>`<br/>eg `find quiz`                                                                         |
|`clear`| `clear`                                                                                                     |
|`undoclear`| `undoclear`                                                                                                 |
|`priority`| `priroity <number> <priority_level>`<br/>eg `priority 2 low`                                                |




[//]: # (## Usage)

[//]: # ()
[//]: # (### `Keyword` - Describe action)

[//]: # ()
[//]: # (Describe the action and its outcome.)

[//]: # ()
[//]: # (Example of usage: )

[//]: # ()
[//]: # (`keyword &#40;optional arguments&#41;`)

[//]: # ()
[//]: # (Expected outcome:)

[//]: # ()
[//]: # (Description of the outcome.)

[//]: # ()
[//]: # (```)

[//]: # (expected output)

[//]: # (```)
