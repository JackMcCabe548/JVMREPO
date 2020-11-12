import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Project(
    var name: String = "",
    var team: String = "",
    var deadline: String = "",
    var expectedFinishTime: String = ""
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

    class ProjectHandler() {

        val projects = mutableListOf<Project>()

        fun createProject(name: String, team: String, deadline: String, expectedFinishTime: String): List<Project> {
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
}
