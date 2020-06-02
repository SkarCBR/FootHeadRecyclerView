package com.mrskar.samples.recyclerview.presentation.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.RecyclerView
import com.mrskar.samples.R
import com.mrskar.samples.recyclerview.presentation.model.AnimationModel
import com.mrskar.samples.recyclerview.presentation.model.ContentModel
import com.mrskar.samples.recyclerview.presentation.model.FootHeadItemContract
import com.mrskar.samples.recyclerview.presentation.model.FooterModel
import com.mrskar.samples.recyclerview.presentation.model.HeaderModel
import kotlinx.android.synthetic.main.bounce_view.view.*
import kotlinx.android.synthetic.main.content_view.view.*
import kotlinx.android.synthetic.main.footer_view.view.*
import kotlinx.android.synthetic.main.header_view.view.*

class FootHeadAdapter(
    private var itemList: List<FootHeadItemContract>,
    private val contentListener: ((ContentModel) -> Unit)? = null,
    private val headerListener: ((HeaderModel) -> Unit)? = null,
    private val footerListener: ((FooterModel) -> Unit)? = null
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is HeaderModel -> HEADER_TYPE
            is ContentModel -> CONTENT_TYPE
            is FooterModel -> FOOTER_TYPE
            is AnimationModel -> BOUNCE_TYPE
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
            BOUNCE_TYPE -> {
                val bounce = LayoutInflater.from(parent.context).inflate(R.layout.bounce_view, parent, false)
                BounceViewHolder(bounceView = bounce)
            }
            else -> TODO("Type $viewType not implemented")
        }
    }

    override fun getItemCount(): Int = itemList.size

    fun setData(items: List<FootHeadItemContract>) {
        itemList = items
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int): Unit = with(holder) {
        when (this) {
            is HeaderViewHolder -> bind(itemList[position] as HeaderModel, headerListener)
            is ContentViewHolder -> bind(itemList[position] as ContentModel, contentListener)
            is FooterViewHolder -> bind(itemList[position] as FooterModel, footerListener)
            is BounceViewHolder -> bind(itemList[position] as AnimationModel, null)
        }
    }

    companion object {
        const val HEADER_TYPE = 0
        const val CONTENT_TYPE = 1
        const val FOOTER_TYPE = 2
        const val BOUNCE_TYPE = 3
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

    inner class BounceViewHolder(val bounceView: View) : RecyclerView.ViewHolder(bounceView) {
        fun bind(item: AnimationModel, listener: ((AnimationModel) -> Unit)?) = with(bounceView) {
            bounce_title_textview.text = item.text

            bounce_button.setOnClickListener {
                val bounceAnimation = AnimationUtils.loadAnimation(context,R.anim.interpolator_overshoot)
                    bounceAnimation.fillAfter = true
                bounce_imageview.startAnimation(bounceAnimation)
            }

            item.background?.let { bounce_parent.background = resources.getDrawable(it) }
            setOnClickListener { listener?.invoke(item) }
        }
    }
}