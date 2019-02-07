package company.inside.sangue.ui.core

import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.app.AppCompatActivity
import android.widget.TextView
import company.inside.sangue.R
import company.inside.sangue.ui.homepage.MainStreamFragment
import company.inside.sangue.util.OpenManageClass

class MainActivity : AppCompatActivity() {

    lateinit var fragment: BaseFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.content_main)

        fragment = MainStreamFragment()
        OpenManageClass.openFragment(fragment,supportFragmentManager,true)

    }

}
