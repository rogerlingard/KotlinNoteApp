import java.io.File
import java.time.LocalDate
import kotlinx.serialization.json.Json

//serialization library came from https://kotlinlang.org/docs/serialization.html#serialize-and-deserialize-json


/*
This class is the main driver of our note functions.
 */
class NoteList {
    private var notes = mutableListOf<Note>()
    //Got the private val notes from chatGPT. We call a private variable so we can only use it within our note class. For security. We make it a val as the list of notes will not change. The MutableListOf<Note>() allows us to add notes or subtracts notes from the list as we would like to.

    fun removeNote() {
        println("Which note would you like to remove (use the ID)?")
        val userRemoveChoice = readln().toIntOrNull()
        if (userRemoveChoice != null){

            val noteIndex = notes.indexOfFirst { it.id == userRemoveChoice }

            if(noteIndex != -1){
                notes.removeAt(noteIndex)
                println("Successful removed note at id $userRemoveChoice")
            } else {
                println("No note found with ID $userRemoveChoice")
            }
        } else {
            println("What you entered was not an integer. Please use a number only.")
        }
    }
    /*
    ChatGPT helped me with this part of the code. So we take in an int that we are going to use to remove an entry from the Notelist.
    We then return a Note or a null value back to the main program where we call this. We do this just in case that the user does not give us a valid int, and helps with the program not crashing.
    The rest of the function is checking to see if the id that was given, matches to an id within the notelist. We do this by searching the notelist for notes that have the id that the user gave, and we will get the list id back. If it does we will remove it at the given id.
    If we can't find an id within the notelist that the user gave us we will tell them we couldn't find it.
    If it does not we will return a null that we can use in the rest of the program.
     */

    fun printNoteList(){
        notes.forEach { note -> note.displayNoteHead() }
    }
    fun getSize(): Int{
        return notes.size
    }

    fun printIndividualNote(){
        println("Which note would you like to look at fully? (Use the ID)")
        val userRemoveChoice = readln().toIntOrNull()
        if (userRemoveChoice != null){

            val noteIndex = notes.indexOfFirst { it.id == userRemoveChoice }

            if(noteIndex != -1){
                notes[noteIndex].printNote()
            } else {
                println("No note found with ID $userRemoveChoice")
            }
        } else {
            println("What you entered was not an integer. Please use a number only.")
        }
    }

    fun addNoteToNoteList(){
        val noteID = notes.size + 1
        println("What is the title of this note?")
        val userTitle = readln()
        println("Write the body of the note here")
        val userBody = readln()

        val timeWritten = LocalDate.now().toString()
        val newNote = Note(noteID, userTitle, userBody, timeWritten)
        notes.add(newNote)
    }
    fun addNoteToNoteList(inputNote: Note){
        notes.add(inputNote)
    }

    fun readNoteList(userFile: File = File("notes.json")): Boolean {

        return try {
            val readJson = userFile.readText()
            val obj = Json.decodeFromString<List<Note>>(readJson)
            notes.clear()
            notes.addAll(obj)
            true
        } catch (e: Exception) {
            println("Error reading the note: ${e.message}")
            false
        }
    /*
    I used the help of ChatGPT to help me with the Json file writing because it is my first time using Json
    We are making the readJson file, which is basically a string from what we read from notes.json
    then we make the obj variable which we use the json serializer to basically decode from a json string to a object within Kotlin, specifically a List of notes
    Which we got from the readJson string we made earlier.

    The last two lines of code are just clearing the mutable note list that we have and then adding all the notes that we have from the objs variable into our notes list so we can have access.
    Also, the reason why I am using the clear function makes it so the user could choose what note files they would like to load. (This will be implemented later)

    For the try and catch for this function, Basically it's to help with starting the program and reading if the notes.json file is empty or not. So we are going to try to read the file and addAll to notelist
    but if we don't then we are going to return false. This makes it so we can write an if statement in main to tell the user what is happening.
    Also, how this works is that we update the internal list of the class so we can return a true or false statement.
     */
    }
    fun writeNoteList(userFile: File = File("notes.json")){
            val json = Json.encodeToString((notes))
            userFile.writeText(json)
            /*
            The File(notes.json) was written by chatgpt.
            We are using File to write to the notes.json file, using the json string that we made earlier so we can write to the file in a json formate.
            With the read function we will read the way that we formatted the encodeToString function.
             */

    }

    fun createDefaultNoteFile(){
        if(!File("notes.json").exists()){
            val notefile = File("notes.json")
            notefile.createNewFile()
        }
    }


}