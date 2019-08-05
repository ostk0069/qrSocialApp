package jp.co.cyberagent.dojo2019

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    private var bitmap: Bitmap? = null
    private lateinit var iamEditText: EditText
    private lateinit var githubEditText: EditText
    private lateinit var twitterEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var showQRButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        iamEditText = findViewById(R.id.user_iam)
        githubEditText = findViewById(R.id.user_github)
        twitterEditText = findViewById(R.id.user_twitter)
        submitButton = findViewById(R.id.user_submit)
        showQRButton = findViewById(R.id.showQR)

        submitButton.setOnClickListener {
            createQRImage()
        }

        showQRButton.setOnClickListener {
            showQRImage()
        }
    }

    private fun createQRImage() {
        val iam: String = iamEditText.text.toString()
        val githubID: String = githubEditText.text.toString()
        val twitterID: String = twitterEditText.text.toString()
        if (iam.isNotEmpty() && githubID.isNotEmpty()) {
            val url = "ca-tech://dojo/share?iam=$iam&gh=$githubID&tw=$twitterID"
            try {
                val barcodeEncoder = BarcodeEncoder()
                bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
            } catch(error: Exception) {
                throw Exception("failed to create QR")
            }
        } else {
//                AlertDialog.Builder(this)
////                    .setTitle("QRコード生成には名前とGitHubIDが必要です")
////                    .setPositiveButton("OK"){ _, _ ->
////                    }.show()

        }
    }

    private fun showQRImage() {
        if (bitmap != null) {
            val qrCodeImage: ImageView = findViewById<View>(R.id.qr_image) as ImageView
            qrCodeImage.setImageBitmap(bitmap)
        } else {
//                AlertDialog.Builder(this)
////                    .setTitle("QRコードの生成を事前に行ってください")
////                    .setPositiveButton("OK"){ _, _ ->
////                    }.show()
        }
    }
}
