package ro.codetwister.navigationwithoutfragments.screens

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import android.widget.TextView

class DashboardScreen @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0) : FrameLayout(context, attrs, defStyleAttr) {

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        addView(TextView(context).apply { text = "DashBoard Content" })
    }
}