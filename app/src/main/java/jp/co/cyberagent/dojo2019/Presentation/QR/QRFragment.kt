package jp.co.cyberagent.dojo2019.Presentation.QR

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.R
import java.lang.Exception

class QRFragment : Fragment() {

    private lateinit var qrImageView: ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        qrImageView = view.findViewById(R.id.qr_image)
        setQRUrl()
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
        } else {
            val url  = "ca-tech://dojo/share?iam=$encodedIam&gh=$encodedGithubId&tw=$encodedTwitterId"
            createQR(url)
        }
    }

    private fun createQR(url: String) {
        try {
            val barcodeEncoder = BarcodeEncoder()
            val bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)
            showQR(bitmap)
        } catch(error: Exception) {
            throw Exception("failed to create QR")
        }
    }

    private fun showQR(bitmap: Bitmap) {
        if (bitmap != null) {
            qrImageView.setImageBitmap(bitmap)
        } else {
            Toast.makeText(
                view?.context,
                "プロフィールの作成を事前に行ってください",
                Toast.LENGTH_LONG).show()
        }
    }
}