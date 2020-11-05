import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Project(
    var name: String = "",
    var team: String = "",
    var deadline: String = "",
    var expectedFinishTime: String = ""
) {
    constructor(){

    }

}

class ProjectHandler(){

    val projects = mutableListOf<Project>()

    fun createProject(name: String, team: String, deadline: String, expectedFinishTime: String): List<Project>{
        projects.add(Project(name = name, team = team, deadline = deadline, expectedFinishTime = expectedFinishTime))
        return projects
    }

}

    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted
    }
