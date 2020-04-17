package command


import java.util.ArrayList
import java.util.function.Consumer

internal class CommandManager private constructor() {
    private val queueStackNormal: QueueStack<List<Command>> = QueueStack()
    private val queueStackReverse: QueueStack<List<Command>> = QueueStack()

    private val actionHistory: MutableList<String>

    init {
        actionHistory = ArrayList()
    }

    fun execute(commandList: List<Command>) {
        commandList.forEach(Consumer<Command> { it.execute() })
        queueStackNormal.push(commandList)
        commandList.forEach { a -> actionHistory.add(a.name) }
    }

    fun undo() {
        val optionalActions = queueStackNormal.pop()
        optionalActions.ifPresent { list ->
            list.forEach(Consumer<Command> { it.undo() })
            queueStackReverse.push(list)
            list.forEach { a -> actionHistory.add(a.name + " - отмена") }
        }
    }

    fun redo() {
        val optionalActions = queueStackReverse.pop()
        optionalActions.ifPresent { list ->
            list.forEach(Consumer<Command> { it.execute() })
            queueStackNormal.push(list)
            list.forEach { a -> actionHistory.add(a.name + " - повтор") }
        }
    }

    fun clearNormal() {
        queueStackNormal.clear()
    }

    fun clearReverse() {
        queueStackReverse.clear()
    }

    fun getActionHistory(): List<String> {
        return actionHistory
    }

    companion object {
        private val instance: CommandManager? = null

        private fun getInstance(): CommandManager {
            return instance ?: CommandManager()
        }

        @JvmStatic
        fun main(args: Array<String>) {
            val manager = getInstance()

            // список текущих команд к исполнению
            val actionList = ArrayList<Command>()
            actionList.add(NextPodcastCommand("NextPodcastCommand"))
            actionList.add(LikePodcast("LikePodcast"))

            println("Исполнение команд:")
            manager.execute(actionList)

            manager.undo()
            manager.redo()

            manager.clearNormal()
            manager.undo()
            manager.redo()

            println()
            println("История команд:")
            println(manager.getActionHistory().toString())
        }
    }
}
