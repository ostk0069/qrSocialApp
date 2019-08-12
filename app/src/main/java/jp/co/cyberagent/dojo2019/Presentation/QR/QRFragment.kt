package jp.co.cyberagent.dojo2019.Presentation.QR

import android.content.Intent
import android.graphics.drawable.Drawable
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
import jp.co.cyberagent.dojo2019.Presentation.UserShow.UserShowActivity
import jp.co.cyberagent.dojo2019.R
import java.lang.Exception

class QRFragment : Fragment() {

    private lateinit var qrImageView: ImageView
    private lateinit var cameraButton: Button
    private lateinit var viewModel: QRViewModel
    private var iam: String = ""
    private var githubID: String = ""
    private var twitterID: String = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qr_image)
        cameraButton = view.findViewById(R.id.camera)
        viewModel = QRViewModel(view.context)

        iam = viewModel.fetchEncodedUserIam()
        githubID = viewModel.fetchEncodedUserGitHubID()
        twitterID = viewModel.fetchEncodedUserTwitterID()

        if (githubID.isNotEmpty()) {
            createQR()
        } else {
            setFailedImage()
        }

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

    private fun createQR() {
        val url  = "ca-tech://dojo/share?iam=$iam&gh=$githubID&tw=$twitterID"
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
            qrImageView.setImageBitmap(bitmap)
            Toast.makeText(
                view?.context,
                "QRコードの作成に成功しました",
                Toast.LENGTH_LONG).show()
        } catch(error: Exception) {
            throw Exception("QRコードの作成に失敗しました")
        }
    }

    private fun setFailedImage() {
        val drawable: Drawable? = this.context?.getDrawable(R.drawable.failed)
        drawable?: return
        qrImageView.setImageDrawable(drawable)
        Toast.makeText(
            view?.context,
            "プロフィールの作成を事前に行ってください",
            Toast.LENGTH_LONG).show()
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