package company.inside.sangue.util

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.util.Log
import company.inside.sangue.R

/**
 * Created by glevandowski on 30/06/18.
 */
open class OpenManageClass {

    companion object {

        fun openFragment(calledFragment: Fragment, manager: FragmentManager,noAddToBackStack: Boolean) {
            if (noAddToBackStack)
                manager.beginTransaction().replace(R.id.container, calledFragment).commitAllowingStateLoss()
            else
            manager.beginTransaction().replace(R.id.container, calledFragment).addToBackStack(calledFragment::class.java.getName()).commitAllowingStateLoss()
        }
        fun openFragment(calledFragment: Fragment, manager: FragmentManager,bundle: Bundle,noAddToBackStack: Boolean) {
            calledFragment.arguments = bundle
            if (noAddToBackStack)
                manager.beginTransaction().replace(R.id.container, calledFragment).commitAllowingStateLoss()
            else
                manager.beginTransaction().replace(R.id.container, calledFragment).addToBackStack(calledFragment::class.java.getName()).commitAllowingStateLoss()
        }
    }
}
