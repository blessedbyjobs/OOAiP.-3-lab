package command

/**
 * Команда "отметить лайком подкаст"
 */
class LikePodcast(override val name: String) : Command {

    override fun execute() {
        println("Исполняется команда `$name`, Вы поставили этому подкасту лайк")
    }

    override fun undo() {
        println("Отменяется команда `$name`, Вы убрали лайк с этого подкаста")
    }
}