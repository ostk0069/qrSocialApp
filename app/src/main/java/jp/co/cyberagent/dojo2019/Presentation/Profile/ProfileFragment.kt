package jp.co.cyberagent.dojo2019.Presentation.Profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import jp.co.cyberagent.dojo2019.R

class ProfileFragment : Fragment() {

    private lateinit var iamEditText: EditText
    private lateinit var githubEditText: EditText
    private lateinit var twitterEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var viewModel: ProfileViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iamEditText = view.findViewById(R.id.user_iam)
        githubEditText = view.findViewById(R.id.user_github)
        twitterEditText = view.findViewById(R.id.user_twitter)
        submitButton = view.findViewById(R.id.user_submit)
        viewModel = ProfileViewModel(view.context)

        iamEditText.setText(viewModel.fetchIam())
        githubEditText.setText(viewModel.fetchGithubID())
        twitterEditText.setText(viewModel.fetchTwitterID())

        submitButton.setOnClickListener {
            createAdminUser()
            // TODO: navigate to qr tab
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return  inflater.inflate(R.layout.fragment_profile, container, false)
    }

    private fun createAdminUser() {
        val iam: String = iamEditText.text.toString()
        val githubID: String = githubEditText.text.toString()
        val twitterID: String = twitterEditText.text.toString()
        if (githubID.isNotEmpty()) {
            val adminUser = this.activity?.getSharedPreferences("ca_dojo", Context.MODE_PRIVATE)
            val editor = adminUser?.edit()
            editor?.putString("iam", iam)
            editor?.putString("GithubID", githubID)
            editor?.putString("twitterID", twitterID)
            editor?.apply()
            Toast.makeText(
                view?.context,
                "プロフィールの作成に成功しました",
                Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(
                view?.context,
                "GitHubIDが正しくありません",
                Toast.LENGTH_LONG).show()
        }
    }
}