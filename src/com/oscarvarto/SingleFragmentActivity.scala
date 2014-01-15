package com.oscarvarto

import android.app.{FragmentManager, Fragment, Activity}
import android.os.Bundle

/**
 * Created by oscarvarto on 9/27/13.
 */
trait SingleFragmentActivity extends Activity {

  def createFragment(): Fragment

  def getLayoutResId: Int = R.layout.main

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    setContentView(getLayoutResId)

    val fm: FragmentManager = getFragmentManager
    var fragment: Fragment = fm.findFragmentById(R.id.fragmentContainer)
    if (fragment == null) {
      fragment = createFragment()
      fm.beginTransaction().add(R.id.fragmentContainer, fragment).commit()
    }
  }
}