package com.mrskar.samples.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mrskar.samples.R
import com.mrskar.samples.model.ContentModel
import com.mrskar.samples.model.FootHeadItemContract
import com.mrskar.samples.model.FooterModel
import com.mrskar.samples.model.HeaderModel
import kotlinx.android.synthetic.main.content_view.view.*
import kotlinx.android.synthetic.main.footer_view.view.*
import kotlinx.android.synthetic.main.header_view.view.*

class FootHeadAdapter(
    private val itemList: List<FootHeadItemContract>,
    private val contentListener: ((ContentModel) -> Unit)? = null,
    private val headerListener: ((HeaderModel) -> Unit)? = null,
    private val footerListener: ((FooterModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is HeaderModel -> HEADER_TYPE
            is ContentModel -> CONTENT_TYPE
            is FooterModel -> FOOTER_TYPE
            else -> super.getItemViewType(position)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            HEADER_TYPE -> {
                val header = LayoutInflater.from(parent.context).inflate(R.layout.header_view, parent, false)
                HeaderViewHolder(headerView = header)
            }
            CONTENT_TYPE -> {
                val content = LayoutInflater.from(parent.context).inflate(R.layout.content_view, parent, false)
                ContentViewHolder(contentView = content)
            }
            FOOTER_TYPE -> {
                val footer = LayoutInflater.from(parent.context).inflate(R.layout.footer_view, parent, false)
                FooterViewHolder(footerView = footer)
            }
            else -> TODO("Type $viewType not implemented")
        }
    }

    override fun getItemCount(): Int = itemList.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit = with(holder) {
        when (this) {
            is HeaderViewHolder -> bind(itemList[position] as HeaderModel, headerListener)
            is ContentViewHolder -> bind(itemList[position] as ContentModel, contentListener)
            is FooterViewHolder -> bind(itemList[position] as FooterModel, footerListener)
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val CONTENT_TYPE = 1
        const val FOOTER_TYPE = 2
    }


    inner class HeaderViewHolder(val headerView: View) : RecyclerView.ViewHolder(headerView) {
        fun bind(item: HeaderModel, listener: ((HeaderModel) -> Unit)?) = with(headerView) {
            header_textview.text = item.text
            item.background?.let { header_textview.background = resources.getDrawable(it) }
            setOnClickListener { listener?.invoke(item) }
        }
    }

    inner class ContentViewHolder(val contentView: View) : RecyclerView.ViewHolder(contentView) {
        fun bind(item: ContentModel, listener: ((ContentModel) -> Unit)?) = with(contentView) {
            content_title_textview.text = item.text
            content_description_textview.text = item.description
            item.background?.let { content_parent.background = resources.getDrawable(it) }
            setOnClickListener { listener?.invoke(item) }
        }
    }

    inner class FooterViewHolder(val footerView: View) : RecyclerView.ViewHolder(footerView) {
        fun bind(item: FooterModel, listener: ((FooterModel) -> Unit)?) = with(footerView) {
            footer_textview.text = item.text
            item.background?.let { footer_textview.background = resources.getDrawable(it) }
            setOnClickListener { listener?.invoke(item) }
        }
    }
}