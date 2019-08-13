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
import androidx.lifecycle.ViewModelProviders
import com.google.zxing.BarcodeFormat
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.Presentation.UserList.UserListViewModel
import jp.co.cyberagent.dojo2019.Presentation.UserShow.UserShowActivity
import jp.co.cyberagent.dojo2019.R
import java.lang.Exception

class QRFragment : Fragment() {

    private lateinit var qrImageView: ImageView
    private lateinit var cameraButton: Button
    private var iam: String = ""
    private var githubID: String = ""
    private var twitterID: String = ""
    private lateinit var viewModel: QRViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qr_image)
        cameraButton = view.findViewById(R.id.camera)
        viewModel = ViewModelProviders.of(this)[QRViewModel::class.java]

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
}