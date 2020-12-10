/*Ideas for the critical path algorithm and the task handler class:
https://www.youtube.com/watch?v=4oDLMs11Exs
https://www.youtube.com/watch?v=jmCc5VIMOro
https://stackoverflow.com/questions/2985317/critical-path-method-algorithm
https://www.workamajig.com/blog/critical-path-method#:~:text=The%20Critical%20Path%20Algorithm%20Explained,critical%22%20for%20the%20project).
*/

object CriticalPath {
     @JvmStatic
     fun main(args: Array<String>) {
         // Given dependency graph
         val last = TaskHandler("ZLast", 0)
         val D = TaskHandler("D", 2, last)
         val B = TaskHandler("B", 3, last)
         val C = TaskHandler("C", 4, D, B)
         val E = TaskHandler("E", 2, B, C)
         val F = TaskHandler("F", 3, D)
         val first = TaskHandler("AFirst", 0, E)
         val allTasks = hashSetOf(last, D, B, C, E, F, first)

         calculateCP(allTasks)
         printFormatted(allTasks)
     }

     fun calculateCP(taskHandlers: Collection<TaskHandler>) {
         val calculatedTasks = hashSetOf<TaskHandler>() //set of calculated tasks

         val remainingTasks = taskHandlers.toHashSet() //remaining tasks to calculate


         // while there are remaining tasks to be calculated:
         while (remainingTasks.isNotEmpty()) {
             var progress = false

             // find a new task to calculate
             val it = remainingTasks.iterator()
             while (it.hasNext()) { // while there are tasks iterating
                 val task = it.next() //set task to the next remaining

                 if (calculatedTasks.containsAll(task.dependencies)) { //if all are calculated
                     /*get the largest element from the map of dependencies and its critical
                    cost and add the current cost of this task*/
                     val critical = task.dependencies.map { it.criticalCost }.maxOrNull() ?: 0
                     task.criticalCost = critical + task.cost

                     // add task to calculatedtasks and remove from iteration
                     calculatedTasks.add(task)
                     it.remove()

                     // track progress
                     progress = true
                 }
             }
             // catch error if progress hasn't been made (cycle still exists)
             if (!progress) throw RuntimeException("The algorithm has been interrupted. Check the progress!")
         }

         // get the largest cost from the list and print it out to console
         val totalCost = taskHandlers.map { it.criticalCost }.maxOrNull() ?: -1
         println("Critical path's total duration (cost): $totalCost")

         calculateLatest(taskHandlers, totalCost)
         calculateEarly(taskHandlers)
     }


     //calculate LS and LF for each
     fun calculateLatest(taskHandlers: Collection<TaskHandler>, maxCost: Int) = taskHandlers.forEach {
         it.setLatest(maxCost)
     }

     //calculate ES and EF for each
     fun calculateEarly(taskHandlers: Collection<TaskHandler>) = initialNodes(taskHandlers).forEach {
         it.eS = 0
         it.eF = it.cost
         it.setEarlyForDependencies()
     }

     //get the initial node/s of the algorithm
     fun initialNodes(taskHandlers: Collection<TaskHandler>): Collection<TaskHandler> {
         val dependencies = taskHandlers.flatMap { it.dependencies }.toSet()
         // return filtered list only with the tasks which are in dependencies list also
         return taskHandlers.filter { it !in dependencies }.also {
             println("Initial nodes: ${it.joinToString { node -> node.name }}\n")
         }
     }

     fun printFormatted(taskHandlers: Collection<TaskHandler>) {
         val format = "%1$-10s %2$-5s %3$-5s %4$-5s %5$-5s %6$-5s %7$-10s\n"
         System.out.format(format, "Task", "ES", "EF", "LS", "LF", "Slack", "Part of the critical path?")
         taskHandlers.sortedWith { o1, o2 -> o1.name.compareTo(o2.name) }.forEach {
             System.out.format(format, it.name, it.eS, it.eF, it.lS, it.lF, (it.lS - it.eS),
                     if (it.isCritical()) "Yes" else "No" // check if task is part of the critical path
             )
         }
     }
 }