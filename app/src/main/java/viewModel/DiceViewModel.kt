package viewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.android.yahtzee.LOG_TAG
import com.example.android.yahtzee.R
import util.DiceHelper

// Acquires data from Dice-Helper and publishes it, doesn't know how it's displayed
// Gives access to application context. Ex. get resource id's
class DiceViewModel(app: Application) : AndroidViewModel(app) {

    val headline = MutableLiveData<String>()
    val dice = MutableLiveData<IntArray>()
    private val context = app

    init {
        Log.i(LOG_TAG, "View model created")
        headline.value = context.getString(R.string.welcome)
        dice.value = intArrayOf(6, 6, 6, 6, 6)
    }

    fun rollDice() {
        dice.value = DiceHelper.rollDice()
        headline.value = DiceHelper.evaluateDice(context, dice.value)
    }
}

// Once view model is attached to activity, set data in viewModel, each time data changes it will be
// broadcast to the activity, the activity subscribes to that data whenever it changes =
// published subscribe path.  View model publishes data, activity subscribes