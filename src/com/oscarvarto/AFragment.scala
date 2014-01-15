package com.oscarvarto

import android.app.{Activity, Fragment}
import android.view.{ViewGroup, LayoutInflater, View}
import android.os.Bundle
import android.widget.{TextView, Button}
import android.content.Intent
import scalaz.std.option._
import scalaz.syntax.std.option._
import BFragment.{EXTRA_MONEY_AMOUNT, MONEY_REQUEST_CODE}
import android.util.Log

/**
 * Created by oscarvarto on 1/15/14.
 */
object AFragment {
  val DEBUG_TAG = "RetrievingResults"

  def apply(money: Option[Double] = none): AFragment = {
    val args = new Bundle()
    money foreach {
      amount =>
        args.putDouble(EXTRA_MONEY_AMOUNT, amount)
    }
    val aFrag = new AFragment()
    aFrag.setArguments(args)
    aFrag
  }
}

class AFragment extends Fragment {
  var mStartBButton: Button = _
  var mResultTextView: TextView = _

  var mMoney: Option[Double] = none

  override def onCreate(savedInstanceState: Bundle) {
    super.onCreate(savedInstanceState)
    mMoney = if (getArguments.containsKey(EXTRA_MONEY_AMOUNT)) {
      getArguments.getDouble(EXTRA_MONEY_AMOUNT).some
    } else none
  }

  override def onCreateView(inflater: LayoutInflater, parent: ViewGroup, savedInstance: Bundle): View = {
    val v = inflater.inflate(R.layout.a_layout, parent, false)
    mStartBButton = v.findViewById(R.id.start_B_Button).asInstanceOf[Button]
    mResultTextView = v.findViewById(R.id.result_TextView).asInstanceOf[TextView]

    import Conversions._
    mStartBButton.setOnClickListener {
      btn: View =>
      // Start B Activity
        val i = new Intent(getActivity, classOf[B])
        startActivityForResult(i, MONEY_REQUEST_CODE)
    }

    mMoney foreach {
      amount =>
        mResultTextView.setText(amount.toString)
    }

    v
  }

  override def onActivityResult(requestCode: Int, resultCode: Int, data: Intent) {
    requestCode match {
      case MONEY_REQUEST_CODE => resultCode match {
        case Activity.RESULT_OK => // Get the money amount from B
          val amount = data.getDoubleExtra(EXTRA_MONEY_AMOUNT, 0.0)
          Log.d(AFragment.DEBUG_TAG, s"Happy Path Succeded, amount = $amount")
          mResultTextView.setText(amount.toString)
        case Activity.RESULT_CANCELED =>
          Log.d(AFragment.DEBUG_TAG, "RESULT_CANCELED")
      }

    }
  }
}
