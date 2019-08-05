package jp.co.cyberagent.dojo2019

import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception
import java.util.*

class MainActivity : AppCompatActivity() {

    public var bitmap: Bitmap? = null
    private val iamEditText = findViewById<EditText>(R.id.user_iam)
    private val githubEditText = findViewById<EditText>(R.id.user_github)
    private val twitterEditText = findViewById<EditText>(R.id.user_twitter)
    private val submitButton = findViewById<Button>(R.id.user_submit)
    private val showQRButton = findViewById<Button>(R.id.showQR)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        submitButton.setOnClickListener {
            val iam: String = iamEditText.text.toString()
            val githubID: String = githubEditText.text.toString()
            val twitterID: String = twitterEditText.text.toString()
            if (iam.isNotEmpty() && githubID.isNotEmpty()) {
                createQRImage(iam, githubID, twitterID)
            }
            // else: return popup warnings "You Should write your name & githubID"
        }

        showQRButton.setOnClickListener {
            if (bitmap != null) {
                // pop up modal to show the QR image
            }
            // else return popup warnings "You should make QR image"

        }
    }

    private fun createQRImage(iam: String, githubID: String, twitterID: String) {
        val url = "ca-tech://dojo/share?iam=$iam&gh=$githubID&tw=$twitterID"

        try {
            val barcodeEncoder = BarcodeEncoder()
            bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
        } catch(error: Exception) {
            throw Exception("failed to create QR")
        }
    }

}
