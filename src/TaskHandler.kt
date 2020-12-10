class TaskHandler( //handler class for each task to be calculated
        var name: String,
        var cost: Int,
        vararg dependencies: TaskHandler) {

    var criticalCost = 0 //cost of each task during the calculations

    var eS = 0 // Early Start
    var eF = -1 //Early Finish

    var lS = 0 //Latest Start
    var lF = 0 //Latest Finish

    var dependencies = hashSetOf(*dependencies) //set of tasks on which the particular task is dependent on

    //calculate LS and LF
    fun setLatest(totalCost: Int) {
        lS = totalCost - criticalCost
        lF = lS + cost
    }

    //calculate ES and EF for dependency list
    fun setEarlyForDependencies() {
        val completionTime = eF
        dependencies.forEach {
            if (completionTime >= it.eS) {
                it.eS = completionTime
                it.eF = completionTime + it.cost
            }
            it.setEarlyForDependencies()
        }
    }

    // checks if a task is part of the critical path
    fun isCritical() = eS == lS
}
