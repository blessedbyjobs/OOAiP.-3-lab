package command

/**
 * Команда "перейти к следующему подкасту"
 */
class NextPodcastCommand(override val name: String) : Command {

    override fun execute() {
        println("Исполняется команда `$name`, Проигрывается следующий подкаст")
    }

    override fun undo() {
        println("Отменяется команда `$name`, Возврат к предыдущему подкасту")
    }
}