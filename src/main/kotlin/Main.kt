//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.

/*
The main function is where we run our main code. We basically are setting up our file and making a menu for the user
to use. Most of the logic is contained within class files
 */
fun main() {
    var running = true
    var noteList = NoteList()
    noteList.createDefaultNoteFile()
    if(noteList.readNoteList()) {
        println("Loaded notes.json successfully")
    } else {
        println("Could note load notes.json\n")
    }



    while (running) {
        println("1.Write a note \n2.Delete a Note \n3.Show Note List \n4.Show A Note \n5.Save A Note \n0.Exit the Program")
        val userInput = readln().toIntOrNull()

        when (userInput) {
            null -> {
                println("Please use a number")
            }
            1 -> noteList.addNoteToNoteList()
            2 -> noteList.removeNote()
            3 -> noteList.printNoteList()
            4 -> noteList.printIndividualNote()
            5 -> noteList.writeNoteList()
            0 -> running = false

            else -> {
                println("Please enter a valid input")
            }
        }
    }

}