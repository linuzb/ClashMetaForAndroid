package com.github.android.updater.design.adapter

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.android.updater.core.model.Proxy
import com.github.android.updater.design.R
import com.github.android.updater.design.component.ProxyPageFactory
import com.github.android.updater.design.component.ProxyViewConfig
import com.github.android.updater.design.component.ProxyViewState
import com.github.android.updater.design.model.ProxyPageState
import com.github.android.updater.design.model.ProxyState
import com.github.android.updater.design.ui.Surface
import com.github.android.updater.design.util.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ProxyPageAdapter(
    private val surface: Surface,
    private val config: ProxyViewConfig,
    private val adapters: List<ProxyAdapter>,
    private val stateChanged: (Int) -> Unit,
) : RecyclerView.Adapter<ProxyPageFactory.Holder>() {
    private val factory = ProxyPageFactory(config)
    private var parent: RecyclerView? = null

    val states = List(adapters.size) { ProxyPageState() }

    suspend fun updateAdapter(
        position: Int,
        proxies: List<Proxy>,
        selectable: Boolean,
        parent: ProxyState,
        links: Map<String, ProxyState>
    ) {
        val states = withContext(Dispatchers.Default) {
            proxies.map {
                val link = if (it.type.group) links[it.name] else null

                ProxyViewState(config, it, parent, link)
            }
        }

        withContext(Dispatchers.Main) {
            adapters[position].apply {
                this.selectable = selectable
                this.swapDataSet(this::states, states, false)
            }

            requestRedrawVisible()
        }
    }

    fun requestRedrawVisible() {
        factory.fromRoot(parent?.firstVisibleView ?: return)
            .recyclerView.invalidateChildren()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProxyPageFactory.Holder {
        val holder = factory.newInstance()

        val toolbarHeight = config.context.getPixels(R.dimen.toolbar_height)
        val tabHeight = config.context.getPixels(R.dimen.tab_layout_height)

        holder.recyclerView.bindInsets(surface, toolbarHeight + tabHeight)
        holder.recyclerView.addScrolledToBottomObserver { view, bottom ->
            val position = view.position
            val state = states[position]

            if (state.bottom != bottom) {
                state.bottom = bottom

                stateChanged(position)
            }
        }

        return holder
    }

    override fun onBindViewHolder(holder: ProxyPageFactory.Holder, position: Int) {
        val adapter = adapters[position]

        states[position].bottom = false

        holder.recyclerView.apply {
            this.position = position
            this.swapAdapter(adapter, false)
        }
    }

    override fun getItemCount(): Int {
        return adapters.size
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        this.parent = recyclerView

        recyclerView.isFocusable = false
    }

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        this.parent = null
    }


    private var RecyclerView.position: Int
        get() {
            return tag as? Int ?: -1
        }
        set(value) {
            tag = value
        }
}