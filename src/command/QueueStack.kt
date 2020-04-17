package command

import java.util.Optional
import java.util.LinkedList


internal class QueueStack<T> {

    private val dataCollection: MutableList<T>

    init {
        dataCollection = LinkedList()
    }

    fun push(item: T) {
        dataCollection.add(0, item)
    }

    fun pop(): Optional<T> {
        return if (dataCollection.size > 0)
            Optional.of(dataCollection.removeAt(dataCollection.size - 1))
        else
            Optional.empty()
    }

    fun clear() {
        dataCollection.clear()
    }
}