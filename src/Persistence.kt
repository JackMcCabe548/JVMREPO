abstract class Persistence {

    companion object{
        fun createFilePersistence() = FilePersistence()
    }

    abstract infix fun save(data: String)
}

class FilePersistence(): Persistence(){

    override infix fun save(data: String) {
        println("Save $data to database.txt")
    }
}