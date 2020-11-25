import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File
import java.io.IOException
import java.nio.file.Files
import java.nio.file.Paths
import java.nio.file.StandardOpenOption


data class Project(
    var name: String = "",
    var teamName: String = "",
    var startDate: String = "",
    //var teamMembers: String = "",
//    var teamLeader: String = "",
//    var projectTasks: String = ""
    //var expectedFinishTime: String = ""
) {
    // TO DO - MARIO
//    constructor() {
//    }

//    override fun equals(other: Any?): Boolean {
//        if (this === other) return true
//        if (javaClass != other?.javaClass) return false
//
//        other as Project
//
//        if (id != other.id) return false
//
//        return true
//    }
//
//    override fun hashCode(): Int {
//        return id
//    }
//
//    override fun toString(): String {
//        return "Person(id=$id, name='$name', age=$age, address=$address)"
//    }
}
    class ProjectHandler() {

        private val projects = mutableListOf<Project>()


        fun createProject(name: String, teamName: String, startDate: String): List<Project> {
            projects.add(Project(name = name, teamName = teamName, startDate = startDate))
            // , expectedFinishTime = expectedFinishTime add later on - MB
            // expectedFinishTime: String
            return projects
        }

        fun createProject2(
                name: String,
                teamName: String,
                startDate: String,
                //teamMembers: String,
//                teamLeader: String,
//                projectTasks: String
        ) = Project(
                name = if(name.isEmpty()) "ProjectA" else name,
                teamName = if(teamName.isEmpty()) "TeamA" else teamName,
                startDate = if(startDate.isEmpty()) "30/11/2020" else startDate,
                //teamMembers = if(teamMembers.isEmpty()) "Empty" else teamMembers,
//                teamLeader = if(teamLeader.isEmpty()) "None" else teamLeader,
//                projectTasks = if(projectTasks.isEmpty()) "None" else projectTasks,
        )

        fun saveToConsole(project: Project){
                Persistence.createFilePersistence().save(project.toString())
        }

        fun saveToFile(project: Project){
            val path = System.getProperty("user.dir") + "\\database.txt"
            val text = project.toString()
                try {
                    Files.write(Paths.get(path), text.toByteArray(), StandardOpenOption.APPEND)
                    println("Project successfully saved to database.txt file")
                } catch (e: IOException) {
                }

        }

        fun createFile() {
            val fileName = "database.txt"
            var file = File(fileName)
            val isNewFileCreated: Boolean = file.createNewFile()
            if (isNewFileCreated) {
                println("$fileName is created successfully.")
            } else {
                println("$fileName already exists.")
            }
        }

        fun getDate(): String {
            val current = LocalDateTime.now()
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
            val formatted = current.format(formatter)
            return formatted
        }
    }







