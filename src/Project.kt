import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.io.File


data class Project(
    var name: String = "",
    var team: String = "",
    var startDate: String = "",
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


        fun createProject(name: String, team: String, startDate: String): List<Project> {
            projects.add(Project(name = name, team = team, startDate = startDate))
            // , expectedFinishTime = expectedFinishTime add later on - MB
            // expectedFinishTime: String
            return projects
        }

        fun createProject2(
                name: String,
                team: String,
                startDate: String,
        ) = Project(
                name = if(name.isEmpty()) "Mario Bratanov" else name,
                team = if(team.isEmpty()) "Team1" else team,
                startDate = if(startDate.isEmpty()) "01/02/2020" else startDate,
        )

        fun save(project: Project){
                Persistence.createFilePersistence().save(project.toString())
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

    }

    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted
    }





