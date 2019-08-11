package jp.co.cyberagent.dojo2019.Presentation.QR

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.Presentation.UserShowActivity
import jp.co.cyberagent.dojo2019.R
import java.lang.Exception

class QRFragment : Fragment() {

    private lateinit var qrImageView: ImageView
    private lateinit var cameraButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qr_image)
        cameraButton = view.findViewById(R.id.camera)
        setQRUrl()

        cameraButton.setOnClickListener {
            IntentIntegrator(this.activity).initiateScan()
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_qr, container, false)
    }

    private fun setQRUrl() {
        val setData = this.activity?.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)
        val iam = setData?.getString("iam", "")
        val githubID = setData?.getString("GithubID", "")
        val twitterID = setData?.getString("twitterID", "")
        encodeSetData(iam, githubID, twitterID)
    }

    private fun encodeSetData(iam: String?, githubID: String?, twitterID: String?) {
        val encodedIam: String = Uri.encode(iam)
        val encodedGithubId: String = Uri.encode(githubID)
        val encodedTwitterId: String = Uri.encode(twitterID)
        if (encodedGithubId.isEmpty()) {
            val drawable: Drawable? = this.context?.getDrawable(R.drawable.failed)
            drawable?: return
            qrImageView.setImageDrawable(drawable)
            Toast.makeText(
                view?.context,
                "プロフィールの作成を事前に行ってください",
                Toast.LENGTH_LONG).show()
        } else {
            val url  = "ca-tech://dojo/share?iam=$encodedIam&gh=$encodedGithubId&tw=$encodedTwitterId"
            createQR(url)
        }
    }

    private fun createQR(url: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
            qrImageView.setImageBitmap(bitmap)
        } catch(error: Exception) {
            throw Exception("failed to create QR")
        }
    }

    private fun navigateUserShow(url: String) {
        val intent = Intent(view?.context, UserShowActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(view?.context, "Cancelled", Toast.LENGTH_LONG).show()
            } else {
                val captureURL = result.contents
                Toast.makeText(view?.context, "Scanned: " + result.contents, Toast.LENGTH_LONG).show()
                navigateUserShow(captureURL)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}