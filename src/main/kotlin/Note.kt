import kotlinx.serialization.Serializable

//This is a data class that we are using to store the note information. We have some custom print functions to work better within the console. We are also Serializing it so we can write to a json file
@Serializable
data class Note (val id: Int, val title: String, val body: String, val date: String){
    fun printNote(){
        println("ID:$id Title:$title \nNote:$body \nWritten On:$date")
    }
    fun displayNoteHead(){
        println("ID: $id Title: $title - Written On: ${date}\n")
    }

}