package vn.tk.test.util.base

interface BaseAdapterInt<M> {

    interface ItemClickListener<M> {
        fun onItemClick(model: M, position: Int)
    }

    fun data(): MutableList<M>

    fun size(): Int

    fun dataIsEmpty(): Boolean

    fun dataNotEmpty(): Boolean

    fun set(collection: Collection<M>?)

    fun set(array: Array<M>?)

    fun add(collection: Collection<M>?)

    fun add(array: Array<M>?)

    fun add(model: M?)

    fun addFirst(model: M?)

    fun get(index: Int): M?

    fun edit(index: Int, model: M?)

    fun remove(index: Int)

    fun remove(model: M?)

    fun clear()

    fun unBind()

    fun lastPosition(): Int

}