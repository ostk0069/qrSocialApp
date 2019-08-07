package jp.co.cyberagent.dojo2019

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import java.lang.Exception
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {

    private var bitmap: Bitmap? = null
    private var database: AppDatabase? = null
    private var captureURL = ""
    private lateinit var iamEditText: EditText
    private lateinit var githubEditText: EditText
    private lateinit var twitterEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var showQRButton: Button
    private lateinit var userListButton: Button
    private lateinit var cameraButton: Button
    
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val context = applicationContext
        database = AppDatabase.getDatabase(context)

        iamEditText = findViewById(R.id.user_iam)
        githubEditText = findViewById(R.id.user_github)
        twitterEditText = findViewById(R.id.user_twitter)
        submitButton = findViewById(R.id.user_submit)
        showQRButton = findViewById(R.id.showQR)
        userListButton = findViewById(R.id.btn_user_list)
        cameraButton = findViewById(R.id.btn_camera)

        submitButton.setOnClickListener {
            val iam: String = iamEditText.text.toString()
            val githubID: String = githubEditText.text.toString()
            val twitterID: String = twitterEditText.text.toString()
            createUser(iam, githubID, twitterID)
            createQRImage(iam, githubID, twitterID)
        }

        showQRButton.setOnClickListener {
            showQRImage()
        }

        userListButton.setOnClickListener {
            navigateUserList()
        }

        cameraButton.setOnClickListener {
            IntentIntegrator(this).initiateScan()
        }
    }

    private fun createQRImage(iam: String, githubID: String, twitterID: String) {
        if (iam.isNotEmpty() && githubID.isNotEmpty()) {
            val url = "ca-tech://dojo/share?iam=$iam&gh=$githubID&tw=$twitterID"
            try {
                val barcodeEncoder = BarcodeEncoder()
                bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
            } catch(error: Exception) {
                throw Exception("failed to create QR")
            }
        } else {
            Toast.makeText(
                this,
                "QRコード生成には名前とGitHubIDが必要です",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun showQRImage() {
        if (bitmap != null) {
            val qrCodeImage: ImageView = findViewById<View>(R.id.qr_image) as ImageView
            qrCodeImage.setImageBitmap(bitmap)
        } else {
            Toast.makeText(
                this,
                "QRコードの生成を事前に行ってください",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun createUser(iam: String, githubID: String, twitterID: String) {
        val user = User.create(iam, githubID, twitterID)
        thread {
            database?.userDao()?.insert(user)
        }
    }

    private fun insertUserData() {
        var captureData = captureURL.split("?")
        var splitData = captureData[1].split("&", "=")
        val user = User.create(splitData[1],splitData[3],splitData[5])
        thread {
            database?.userDao()?.insert(user)
        }
    }

    private fun navigateUserList() {
//        val intent = Intent(this, UserListActivity::class.java)
//        startActivity(intent)
        val intent = Intent(this, UserIndexActivity::class.java)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                captureURL = result.contents.toString()
                Toast.makeText(this, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                insertUserData()
                navigateUserList()
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
