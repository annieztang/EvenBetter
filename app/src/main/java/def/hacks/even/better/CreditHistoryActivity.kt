package def.hacks.even.better

import android.content.pm.ActivityInfo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView

/**
 * Created by William Zulueta on 4/14/19.
 */
class CreditHistoryActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val TAG = CreditHistoryActivity::class.java.simpleName
    }

    private lateinit var button1: Button
    private lateinit var button2: Button
    private lateinit var button3: Button
    private lateinit var button4: Button

    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        window.requestFeature(Window.FEATURE_ACTION_BAR)
        supportActionBar?.hide()

        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        setContentView(R.layout.credit_history)

        imageView = findViewById(R.id.creditHistoryImage)

        button1 = findViewById(R.id.three_m)
        button1.setOnClickListener(this)

        button2 = findViewById(R.id.six_m)
        button2.setOnClickListener(this)

        button3 = findViewById(R.id.year)
        button3.setOnClickListener(this)

        button4 = findViewById(R.id.all)
        button4.setOnClickListener(this)
    }

    override fun onClick(v: View) {
        button1.setBackgroundResource(R.drawable.roundshapebtn)
        button2.setBackgroundResource(R.drawable.roundshapebtn)
        button3.setBackgroundResource(R.drawable.roundshapebtn)
        button4.setBackgroundResource(R.drawable.roundshapebtn)
        if (v.id == button1.id) {
            imageView.setImageResource(R.drawable.threemnth)
            button1.setBackgroundResource(R.drawable.round_button_selected)
        } else if (v.id == button2.id) {
            imageView.setImageResource(R.drawable.sixmnth)
            button2.setBackgroundResource(R.drawable.round_button_selected)
        } else if (v.id == button3.id) {
            imageView.setImageResource(R.drawable.year)
            button3.setBackgroundResource(R.drawable.round_button_selected)
        } else if (v.id == button4.id) {
            imageView.setImageResource(R.drawable.alltime)
            button4.setBackgroundResource(R.drawable.round_button_selected)
        }
    }

}
