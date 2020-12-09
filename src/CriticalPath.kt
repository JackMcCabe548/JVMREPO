import java.util.*


object CriticalPath {
    var maxCost = 0
    var format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n"
    @JvmStatic
    fun main(args: Array<String>) {
        // The example dependency graph
        val allTasks = HashSet<TaskHandler>()

        val A = TaskHandler("A", 3)
        val B = TaskHandler("B", 1, A)
        val C = TaskHandler("C", 2, A, B)
        val D = TaskHandler("D", 4, C)
        val E = TaskHandler("E", 4, B, D)
        val F = TaskHandler("F", 3)
        val G = TaskHandler("G", 5, B)

        allTasks.add(A)
        allTasks.add(B)
        allTasks.add(C)
        allTasks.add(D)
        allTasks.add(E)
        allTasks.add(F)
        allTasks.add(G)
        val result = criticalPath(allTasks)
        print(result)
        // System.out.println("Critical Path: " + Arrays.toString(result));
    }

    fun criticalPath(tasks: Set<TaskHandler>): Array<TaskHandler> {
        // tasks whose critical cost has been calculated
        val completed = HashSet<TaskHandler>()
        // tasks whose critical cost needs to be calculated
        val remaining = HashSet(tasks)

        // Backflow algorithm
        // while there are tasks whose critical cost isn't calculated.
        while (!remaining.isEmpty()) {
            var progress = false

            // find a new task to calculate
            val it = remaining.iterator()
            while (it.hasNext()) {
                val task = it.next()
                if (completed.containsAll(task.dependencies)) {
                    // all dependencies calculated, critical cost is max
                    // dependency
                    // critical cost, plus our cost
                    var critical = 0
                    for (t in task.dependencies) {
                        if (t.criticalCost > critical) {
                            critical = t.criticalCost
                        }
                    }
                    task.criticalCost = critical + task.cost
                    // set task as calculated an remove
                    completed.add(task)
                    it.remove()
                    // note we are making progress
                    progress = true
                }
            }
            // If we haven't made any progress then a cycle must exist in
            // the graph and we wont be able to calculate the critical path
            if (!progress) throw RuntimeException("Cyclic dependency, algorithm stopped!")
        }

        // get the cost
        maxCost(tasks)
        val initialNodes = initials(tasks)
        calculateEarly(initialNodes)

        // get the tasks
        val ret = completed.toTypedArray()
        // create a priority list
        Arrays.sort(ret) { o1, o2 -> o1.name.compareTo(o2.name) }
        return ret
    }

    fun calculateEarly(initials: HashSet<TaskHandler>) {
        for (initial in initials) {
            initial.earlyStart = 0
            initial.earlyFinish = initial.cost
            setEarly(initial)
        }
    }

    fun setEarly(initial: TaskHandler) {
        val completionTime = initial.earlyFinish
        for (t in initial.dependencies) {
            if (completionTime >= t.earlyStart) {
                t.earlyStart = completionTime
                t.earlyFinish = completionTime + t.cost
            }
            setEarly(t)
        }
    }

    fun initials(tasks: Set<TaskHandler>): HashSet<TaskHandler> {
        val remaining = HashSet(tasks)
        for (t in tasks) {
            for (td in t.dependencies) {
                remaining.remove(td)
            }
        }
        print("Initial nodes: ")
        for (t in remaining) print(t.name + " ")
        print("\n\n")
        return remaining
    }

    fun maxCost(tasks: Set<TaskHandler>) {
        var max = -1
        for (t in tasks) {
            if (t.criticalCost > max) max = t.criticalCost
        }
        maxCost = max
        println("Critical path length (cost): " + maxCost)
        for (t in tasks) {
            t.setLatest()
        }
    }

    fun print(tasks: Array<TaskHandler>) {
        System.out.format(format, "Task", "ES", "EF", "LS", "LF", "Slack", "Critical?")
        for (t in tasks) System.out.format(format, *t.toStringArray())
    }

    // A wrapper class to hold the tasks during the calculation
    class TaskHandler(// a name for the task for printing
            var name: String, // the actual cost of the task
            var cost: Int, vararg dependencies: TaskHandler) {
        // the cost of the task along the critical path
        var criticalCost = 0

        // the earliest start
        var earlyStart = 0

        // the earliest finish
        var earlyFinish: Int

        // the latest start
        var latestStart = 0

        // the latest finish
        var latestFinish = 0

        fun createTask(name: String, cost: Int ) = TaskHandler( // create project fun for persistence
                name = if(name.isEmpty()) "Task1" else name,
                cost = if(cost < 0) 0 else cost
        )

        // the tasks on which this task is dependant
        var dependencies = HashSet<TaskHandler>()
        fun setLatest() {
            latestStart = maxCost - criticalCost
            latestFinish = latestStart + cost
        }

        fun toStringArray(): Array<String?> {
            val criticalCond = if (earlyStart == latestStart) "Yes" else "No"
            return arrayOf(name, earlyStart.toString() + "", earlyFinish.toString() + "", latestStart.toString() + "", latestFinish.toString() + "", (latestStart - earlyStart).toString() + "", criticalCond)
        }

        fun isDependent(t: TaskHandler): Boolean {
            // is t a direct dependency?
            if (dependencies.contains(t)) {
                return true
            }
            // is t an indirect dependency
            for (dep in dependencies) {
                if (dep.isDependent(t)) {
                    return true
                }
            }
            return false
        }

        init {
            for (t in dependencies) {
                this.dependencies.add(t)
            }
            earlyFinish = -1
        }
    }
}