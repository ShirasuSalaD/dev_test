package app.shirasusalad.dev_test

//import sun.jvm.hotspot.utilities.IntArray
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import java.util.*


class MainActivity : AppCompatActivity() {

    // デフォルトの時間
    private val START_TIME: Long = 10000

    // Viewのやつ
    private var mTextViewCountDown: TextView? = null
    private var mButtonStartPause: Button? = null
    private var getmButtonReset: Button? = null

    // CDT制御
    private var mCountDownTimer: CountDownTimer? = null
    private var mTimerRunning: Boolean = false

    //いじる時間の変数
    private var mTimeLeftInMillis = START_TIME

    //メイン
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 初期設定 ボタン
        mTextViewCountDown = findViewById(R.id.text_view_countdown)
        mButtonStartPause = findViewById(R.id.button_start_pause)
        getmButtonReset = findViewById(R.id.buttonreset)



        // スタートボタンの挙動
        mButtonStartPause?.setOnClickListener{
            println("mTimerRunningの値は？ $mTimerRunning")
            if (mTimerRunning) {
                pauseTimer()
            } else {
                startTimer()
            }
        }

        // リセットボタンの挙動
        getmButtonReset?.setOnClickListener{
            resetTimer()
        }

        //時間更新
        updateCountDownText()

        // 初期設定 音楽
        val mP1 = MediaPlayer.create(this, R.raw.kibounohana_sabi)
        mP1.seekTo(0)

        //タイマーに合わせて音をならす
        if(mTextViewCountDown?.text == "00:00"){
            mP1.start()
        }else{
            mP1.stop()
        }
    }

    private fun startTimer() {
        mCountDownTimer = object : CountDownTimer(mTimeLeftInMillis, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                mTimeLeftInMillis = millisUntilFinished
                updateCountDownText()
            }

            override fun onFinish() {
                mTimerRunning = false
                mButtonStartPause?.text = "スタート"
                getmButtonReset?.visibility = View.INVISIBLE
            }
        }.start()

        mTimerRunning = true
        mButtonStartPause?.text = "一時停止"
        getmButtonReset?.visibility = View.INVISIBLE
    }

    private fun pauseTimer() {
        println("一時停止処理前のmTimerRunningは？ $mTimerRunning")
        mCountDownTimer?.cancel()
        mTimerRunning = false
        println("一時停止処理後のmTimerRunningは？ $mTimerRunning")
        mButtonStartPause?.text = "スタート"
        getmButtonReset?.visibility = View.VISIBLE
    }

    private fun resetTimer() {
        mTimeLeftInMillis = START_TIME
        updateCountDownText()
        mButtonStartPause?.visibility = View.VISIBLE
        getmButtonReset?.visibility = View.INVISIBLE
    }

    private fun updateCountDownText() {
        val minutes = (mTimeLeftInMillis / 1000).toInt() / 60
        val seconds = (mTimeLeftInMillis / 1000).toInt() % 60
        val timerLeftFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds)
        mTextViewCountDown?.text = timerLeftFormatted
    }
}
