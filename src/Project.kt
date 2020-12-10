import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


data class Project( // data class to hold the attributes for each project
    var name: String = "",
    var teamName: String = "",
    var startDate: String = "",
){}
    class ProjectHandler() { // handler class

        private val projects = mutableListOf<Project>()

        fun createProject(name: String, teamName: String, startDate: String): List<Project> { // create project in list
            projects.add(Project(name = name, teamName = teamName, startDate = startDate))
            return projects
        }

        fun createProject2(name: String, teamName: String, startDate: String, ) = Project( // create project for persistence (file and console)
                name = if(name.isEmpty()) "ProjectA" else name,
                teamName = if(teamName.isEmpty()) "TeamA" else teamName,
                startDate = if(startDate.isEmpty()) "30/11/2020" else startDate,
        )

        fun saveToConsole(project: Project) { // save project and output to console
            Persistence.createFilePersistence().save(project.toString())
        }

        fun saveToFile(project: Project){ // save project to file
            val path = System.getProperty("user.dir") + "\\database.txt"
            val text = project.toString()
                try {
                    Files.write(Paths.get(path), text.toByteArray(), StandardOpenOption.APPEND)
                    println("Project successfully saved to database.txt")
                } catch (e: IOException) {
                    println(e.stackTrace)
                }

        }

        fun createFile() { // create new file or check for existing
            val fileName = "database.txt"
            val file = File(fileName)
            val isNewFileCreated: Boolean = file.createNewFile()
            if (isNewFileCreated) {
                println("$fileName is created successfully.")
            } else {
                println("$fileName already exists.")
            }
        }

        fun getDate(): String { // get the current date in chosen format
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
            return current.format(formatter)
        }
    }







