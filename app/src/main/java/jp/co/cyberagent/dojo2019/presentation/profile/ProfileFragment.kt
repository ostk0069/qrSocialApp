package jp.co.cyberagent.dojo2019.presentation.profile

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProviders
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private lateinit var iamEditText: EditText
    private lateinit var githubEditText: EditText
    private lateinit var twitterEditText: EditText
    private lateinit var submitButton: Button
    private lateinit var validationMessage: TextView
    private lateinit var binding: FragmentProfileBinding
    private val viewModel: ProfileViewModel by lazy {
        ViewModelProviders.of(this)[ProfileViewModel::class.java]
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iamEditText = view.findViewById(R.id.user_iam)
        githubEditText = view.findViewById(R.id.user_github)
        validationMessage = view.findViewById(R.id.validation_message)
        twitterEditText = view.findViewById(R.id.user_twitter)
        submitButton = view.findViewById(R.id.user_submit)

        iamEditText.setText(viewModel.fetchIam())
        githubEditText.setText(viewModel.fetchGithubID())
        twitterEditText.setText(viewModel.fetchTwitterID())

        submitButton.setOnClickListener {
            createAdminUser()
            hideKeyboard()
        }

        githubEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                viewModel.fetchValidationMessage(p0?.length)
                viewModel.fetchValidationMessageColor(p0?.length)
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.fetchValidationMessage(p0?.length)
                viewModel.fetchValidationMessageColor(p0?.length)
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                viewModel.fetchValidationMessage(p0?.length)
                viewModel.fetchValidationMessageColor(p0?.length)
            }
        })
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_profile, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel
        return  binding.root
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

    private fun hideKeyboard() {
        val view = activity?.currentFocus
        if (view != null) {
            val manager = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            manager.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }
}