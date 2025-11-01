import org.junit.jupiter.api.Assertions.*
import kotlin.test.Test
import NoteList
import java.io.File
import java.time.LocalDate
import kotlin.test.AfterTest
import kotlin.test.BeforeTest
import kotlin.test.asserter

/*
This is the test class where we test our different functions of Notelist. I wanted to learn testing better because it was a weakness for me before this project.
It mainly tests the logic within NoteList.
 */
class NoteListTest {
    private lateinit var tempFile: File


    @BeforeTest
    fun setUp() {
        tempFile = kotlin.io.path.createTempFile().toFile()
    }
    @AfterTest
    fun cleanup() {
        if (tempFile.exists()) tempFile.delete()
    }
    /*
    I used chatGPT to help with the private lateinit var tempFile @BeforeTest and @AfterTest.
    The Lateinit variable is what we are going to use for our temporary file for our tests. What lateinit does is that we basically are telling the program that we will assign the value of the file later.
    We do this so that we can use it with the setup or whenever we would like to set up a specific file setup. It gives us the flexibility we need do the different tests.
    The @BeforeTest and @AfterTest tests is code that is run before and after the tests are completed. So for the before we set up the temp file with test code to test our readers functions
    The @AfterTest is deleting the temp file so that we can start with a blank slate for each test we run.
     */

    @Test
    fun testNoteListRead() {
        tempFile.writeText("""
        [
            {"id": 1, "title": "Test Note", "body": "Testing file reading", "date": "2025-10-27"}
        ]
    """.trimIndent())
        val testNoteList: NoteList = NoteList()
        testNoteList.readNoteList(tempFile)
        assertEquals(1, testNoteList.getSize())
    }

    @Test
    fun testNoteListReadAndWriteLargeFile() {

        val testNoteList: NoteList = NoteList()
        for (i in 0 until 50){
            val addNote = Note(i, "test note title $i", "note body text $i", LocalDate.now().toString())
            testNoteList.addNoteToNoteList(addNote)
        }
        testNoteList.writeNoteList(tempFile)
        testNoteList.readNoteList(tempFile)
        assertEquals(50, testNoteList.getSize())
    }
}