package command

/**
 * Базовый интерфейс всех команд
 */
interface Command {

    val name: String

    fun execute()

    fun undo()
}