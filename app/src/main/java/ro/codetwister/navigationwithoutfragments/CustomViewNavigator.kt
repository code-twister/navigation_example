package ro.codetwister.navigationwithoutfragments

import android.content.Context
import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.navigation.NavDestination
import androidx.navigation.NavOptions
import androidx.navigation.Navigator
import java.util.*

@Navigator.Name("custom_view")
class CustomViewNavigator(private val container: ViewGroup) : Navigator<CustomViewNavigator.Destination>() {

    private val stack: Deque<Pair<Int, Int>> = LinkedList()

    override fun navigate(destination: Destination, args: Bundle?, navOptions: NavOptions?, navigatorExtras: Extras?): NavDestination? {
        val layoutId = destination.layoutId
        stack.push(Pair(destination.id, layoutId))
        replaceView(layoutId)
        return destination
    }

    private fun replaceView(layoutId: Int) {
        container.removeAllViews()
        container.addView(LayoutInflater.from(container.context)
            .inflate(layoutId, container, false))
    }

    override fun createDestination(): Destination {
        return Destination(this)
    }

    override fun popBackStack(): Boolean {
        return if (stack.isNotEmpty()) {
            stack.pop()
            replaceView(stack.peek().second)
            true
        } else {
            false
        }
    }

    class Destination(navigator: Navigator<out NavDestination>) : NavDestination(navigator) {
        @LayoutRes
        var layoutId: Int = 0

        override fun onInflate(context: Context, attrs: AttributeSet) {
            super.onInflate(context, attrs)
            layoutId = context.obtainStyledAttributes(attrs, R.styleable.CustomViewNavigator).getResourceId(R.styleable.CustomViewNavigator_layoutId, -1)
        }
    }
}