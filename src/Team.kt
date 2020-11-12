data class Team(
        var id: String = "",
        var members: List<People>
){  // TO DO - MARIO
    class People(
            var fname: String = "",
            var sname: String = "",
            var position: String = ""
    ) {

    }


}
