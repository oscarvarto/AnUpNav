package com.oscarvarto

import android.widget.{RadioGroup, CompoundButton}
import android.view.View

/**
 * Created by oscarvarto on 1/15/14.
 */

object Conversions {
  implicit def toOnCheckedChangeListener(f: (CompoundButton, Boolean) => Unit) =
    new CompoundButton.OnCheckedChangeListener {
      def onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        f(buttonView, isChecked)
      }
    }

  implicit def toRadioGroupOnChangeListener(f: (RadioGroup, Int) => Unit) = new RadioGroup.OnCheckedChangeListener {
    def onCheckedChanged(group: RadioGroup, checkedId: Int): Unit = {
      f(group, checkedId)
    }
  }

  implicit def toOnClickListener(f: View => Unit) = new View.OnClickListener {
    def onClick(v: View): Unit = f(v)
  }
}