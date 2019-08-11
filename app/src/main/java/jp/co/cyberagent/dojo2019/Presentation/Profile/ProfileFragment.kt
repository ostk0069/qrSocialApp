package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.content.Context
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.google.zxing.BarcodeFormat
import com.journeyapps.barcodescanner.BarcodeEncoder
import jp.co.cyberagent.dojo2019.R
import java.lang.Exception

class ProfileFragment : Fragment() {

    private var bitmap: Bitmap? = null
    private lateinit var viewModel: ProfileViewModel

    private lateinit var iamEditText: EditText
    private lateinit var githubEditText: EditText
    private lateinit var twitterEditText: EditText
    private lateinit var submitButton: Button

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iamEditText = view.findViewById(R.id.user_iam)
        githubEditText = view.findViewById(R.id.user_github)
        twitterEditText = view.findViewById(R.id.user_twitter)
        submitButton = view.findViewById(R.id.user_submit)
        setUser()

        submitButton.setOnClickListener {
            val iam: String = iamEditText.text.toString()
            val githubID: String = githubEditText.text.toString()
            val twitterID: String = twitterEditText.text.toString()
            createQRImage(iam, githubID, twitterID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return  inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun createQRImage(iam: String, githubID: String, twitterID: String) {
        if (githubID.isNotEmpty()) {
            createUser(iam, githubID, twitterID)
            val encodedIam: String = Uri.encode(iam)
            val encodedGithubId: String = Uri.encode(githubID)
            val encodedTwitterId: String = Uri.encode(twitterID)
            val url  = "ca-tech://dojo/share?iam=$encodedIam&gh=$encodedGithubId&tw=$encodedTwitterId"
            try {
                val barcodeEncoder = BarcodeEncoder()
                bitmap = barcodeEncoder.encodeBitmap(url, BarcodeFormat.QR_CODE, 500, 500)

                // TODO: navigate to qr tab
            } catch(error: Exception) {
                throw Exception("failed to create QR")
            }
        } else {
            Toast.makeText(
                view?.context,
                "QRコード生成にはGitHubIDが必要です",
                Toast.LENGTH_LONG).show()
        }
    }

    private fun createUser(iam: String, githubID: String, twitterID: String) {
        val adminUser = this.activity?.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)
        val editor = adminUser?.edit()
        editor?.putString("iam", iam)
        editor?.putString("GithubID", githubID)
        editor?.putString("twitterID", twitterID)
        editor?.apply()
    }

    private fun setUser() {
        val setData = this.activity?.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)
        val iam = setData?.getString("iam", "")
        val githubID = setData?.getString("GithubID", "")
        val twitterID = setData?.getString("twitterID", "")
        iamEditText.setText(iam)
        githubEditText.setText(githubID)
        twitterEditText.setText(twitterID)
    }
}