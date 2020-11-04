import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

data class Project(
    var name: String = "",
    var team: String = "",
    //var deadline: String =
    //var expectedFinishTime: String =
) {
    constructor(){

    }

}

    fun getDate(): String {
        val current = LocalDateTime.now()
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss.SSS")
        val formatted = current.format(formatter)
        return formatted
    }
