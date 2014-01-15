package com.oscarvarto

import android.app.{Activity, Fragment}
import android.widget.EditText
import android.view.{MenuItem, View, ViewGroup, LayoutInflater}
import android.os.Bundle
import android.content.Intent
import android.support.v4.app.NavUtils
import android.util.Log

/**
 * Created by oscarvarto on 1/15/14.
 */
object BFragment {
  val EXTRA_MONEY_AMOUNT = "com.oscarvarto.moneyAmount"
  val MONEY_REQUEST_CODE = 0

  def apply(): BFragment = {
    new BFragment()
  }
}

class BFragment extends Fragment {
  var mMoneyAmountEditText: EditText = _

  override def onCreate(savedInstance: Bundle) {
    super.onCreate(savedInstance)
    setHasOptionsMenu(true) // <--- This line is important for navigation
  }

  override def onCreateView(inflater: LayoutInflater, parent: ViewGroup, savedInstance: Bundle): View = {
    val v = inflater.inflate(R.layout.b_layout, parent, false)
    mMoneyAmountEditText = v.findViewById(R.id.money_amount_editText).asInstanceOf[EditText]
    getActivity.getActionBar.setDisplayHomeAsUpEnabled(true) // <--- This line is important for navigation
    v
  }

  override def onOptionsItemSelected(item: MenuItem): Boolean = {
    item.getItemId match {
      case android.R.id.home =>
        // Parse money amount and return it as a result
        import scalaz.syntax.std.string._
        val money: Option[Double] = mMoneyAmountEditText.getText.toString.parseDouble.toOption
        money foreach {
          amount =>
            val amountData = new Intent()
            amountData.putExtra(BFragment.EXTRA_MONEY_AMOUNT, amount)
            getActivity.setResult(Activity.RESULT_OK, amountData)
            Log.d(AFragment.DEBUG_TAG, "After setting the result inside BFragment.onOptionsItemSelected")
        }
        NavUtils.navigateUpFromSameTask(getActivity)
        true
      case _ => super.onOptionsItemSelected(item)
    }
  }

}
