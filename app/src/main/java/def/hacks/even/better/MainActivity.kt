package def.hacks.even.better

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Window
import com.google.gson.Gson
import def.hacks.even.coreapi.EvenRequest

class MainActivity : AppCompatActivity() {

    private val evenRequest = EvenRequest.temp(Gson())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        setContentView(R.layout.activity_main)

        val transaction = supportFragmentManager.beginTransaction()
        transaction.add(R.id.frameLayout, LoginFragment(), LoginFragment.TAG)
        transaction.commit()
    }

    fun setLoanType(loanType: String) {
        evenRequest.loanInformation.purpose = loanType
    }

    override fun onBackPressed() {
        supportFragmentManager.popBackStack()
    }

}
