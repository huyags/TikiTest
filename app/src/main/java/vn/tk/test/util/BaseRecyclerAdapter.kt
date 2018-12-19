package vn.tk.test.util.base

import android.support.annotation.LayoutRes
import android.support.annotation.NonNull
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseRecyclerAdapter<M> :
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    BaseAdapterInt<M> {

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v)

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        val model = get(position) ?: return 0
        return model.onCustomLayout(position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(viewType, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val type = getItemViewType(position)

        if (type == 0) return

        val model = get(position) ?: return

        holder.itemView.onBindModel(model, position, type)

        itemClickListener?.run {
            holder.itemView.setOnClickListener {
                onItemClick(model, position)
            }
        }

    }


    /**
     * [BaseRecyclerAdapter] OVERRIDABLE FOR INITIALIZE RECYCLER VIEW TYPE:
     * HEADER ITEM VIEW, LOAD MORE ITEM VIEW, RESERVED DATA LIST
     */
    @LayoutRes
    protected abstract fun defaultLayoutResource(): Int

    protected abstract fun View.onBindModel(model: M, position: Int, @LayoutRes layout: Int)


    @LayoutRes
    @NonNull
    protected open fun M.onCustomLayout(position: Int): Int = defaultLayoutResource()


    /**
     * [BaseAdapterInt] implement:
     * data list handle
     */
    @Volatile
    var loadMorePosition: Boolean = false

    var itemClickListener: BaseAdapterInt.ItemClickListener<M>? = null

    private var data: MutableList<M> = mutableListOf()

    private fun indexInBound(position: Int): Boolean {
        return position > -1 && position < size()
    }

    override fun data(): MutableList<M> {
        return data
    }

    override fun size(): Int {
        return data.size
    }

    override fun dataIsEmpty(): Boolean {
        return data.isEmpty()
    }

    override fun dataNotEmpty(): Boolean {
        return data.isNotEmpty()
    }

    override fun set(collection: Collection<M>?) {
        collection ?: return
        data = collection as? MutableList ?: collection.toMutableList()
        notifyDataSetChanged()
    }

    override fun set(array: Array<M>?) {
        array ?: return
        data = array.toMutableList()
        notifyDataSetChanged()
    }

    override fun add(collection: Collection<M>?) {
        collection ?: return
        if (collection.isEmpty()) return
        val s = size()
        data.addAll(collection)
        notifyItemRangeInserted(s, size())
    }

    override fun add(array: Array<M>?) {
        array ?: return
        if (array.isEmpty()) return
        val s = size()
        data.addAll(array)
        notifyItemRangeInserted(s, size())
    }

    override fun add(model: M?) {
        model ?: return
        data.add(model)
        notifyItemInserted(data.size)
    }

    override fun addFirst(model: M?) {
        model ?: return
        data.add(0, model)
        notifyDataSetChanged()
    }

    override fun get(index: Int): M? {
        if (indexInBound(index)) return data[index]
        return null
    }

    override fun edit(index: Int, model: M?) {
        model ?: return
        if (indexInBound(index)) {
            data[index] = model
            notifyItemChanged(index)
        }
    }

    override fun remove(index: Int) {
        data.removeAt(index)
        notifyItemRemoved(index)
    }

    override fun remove(model: M?) {
        model ?: return
        data.remove(model)
        val index = data.indexOf(model)
        if (index != -1) {
            notifyItemRemoved(index)
        }
    }

    override fun clear() {
        data.clear()
        notifyDataSetChanged()
    }

    override fun unBind() {
        data = mutableListOf()
        notifyDataSetChanged()
    }

    override fun lastPosition(): Int {
        return if (data.isEmpty()) -1 else (data.size - 1)
    }

}



