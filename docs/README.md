# Sidtacphi User Guide
>    “The small ditetrahedronary cubiprismatohecatonicosachoron, or sidtacphi, is a nonconvex [uniform polychoron](https://polytope.miraheze.org/wiki/Uniform_polytope#4D) that consists of 600 [cubes](https://polytope.miraheze.org/wiki/Cube) (lying in the same hyperplanes, forming 120 [compounds of five cubes](https://polytope.miraheze.org/wiki/Rhombihedron)), 120 [dodecahedra](https://polytope.miraheze.org/wiki/Dodecahedron), and 720 [pentagonal prisms](https://polytope.miraheze.org/wiki/Pentagonal_prism). Eight cubes (or four compounds), four dodecahedra, and twelve pentagonal prisms join at each vertex.” – [Miraheze](https://polytope.miraheze.org/wiki/Small_ditetrahedronary_cubiprismatohecatonicosachoron)

Sidtacphi is an app for managing contacts and tasks lists.

## Set-up
1. Download the latest release.
2. Move it into an empty folder.
3. Open a terminal emulator instance at the folder and run `java -jar sidtacphi.jar`

_Note that Java 11 is required to be installed to run Sidtacphi._

## Features
These are the current implemented commands that can be used with Sidtacphi.

* ### List tasks
  * `list`
  *  Shows a list of tasks Sidtacphi has saved.

* ### Add tasks
  * #### Add Todo task
    * `todo <Task name>`
    * Adds a Todo task with the input name.

  * #### Add Deadline task
    * `deadline <Task name> /by <YYYY-MM-DD>`
    * Adds a Deadline task with the input name and deadline date.

  * #### Add Event task
    * `event <Task name> /from <YYYY-MM-DD> /to <YYYY-MM-DD>`
    * Adds an Event task with the input name and a start and end date.

* ### Delete task
  * `delete <ID>`
  * Deletes the task of the corresponding ID number. Each task's ID number can be listed with `list`.

* ### Find task
  * `find <Name>`
  * Shows all tasks containing the name input.

* ### List contacts
  * `listcontacts` or `listcontact`
  * Shows a list of contacts Sidtacphi has saved.

* ### Add contact
  * `addcontact <Contact name> /desc <Contact description>`
  * Adds a contact with the specified name and description into the contact list.

* ### Delete contact
  * `deletecontact <ID>`
  * Deletes the contact of the corresponding ID number. Each contact's ID number can be listed with `listcontacts`.

## Acknowledgements
Built using [Jackson](https://github.com/FasterXML/jackson) as part of the [Duke](https://github.com/se-edu/duke) greenfield project.
