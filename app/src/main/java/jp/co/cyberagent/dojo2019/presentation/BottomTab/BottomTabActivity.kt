package jp.co.cyberagent.dojo2019.presentation.BottomTab

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import com.google.zxing.integration.android.IntentIntegrator
import jp.co.cyberagent.dojo2019.presentation.profile.ProfileFragment
import jp.co.cyberagent.dojo2019.presentation.QR.QRFragment
import jp.co.cyberagent.dojo2019.presentation.UserList.UserListFragment
import jp.co.cyberagent.dojo2019.presentation.UserShow.UserShowActivity
import jp.co.cyberagent.dojo2019.R

class BottomTabActivity : AppCompatActivity() {

    private var captureURL: String? = null

    private val onNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, ProfileFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, QRFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout, UserListFragment())
                    .commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bottom_tab)
        val navView: BottomNavigationView = findViewById(R.id.nav_view)

        navView.setOnNavigationItemSelectedListener(onNavigationItemSelectedListener)
        val isList: String? = intent.getStringExtra("list")
        isList?.let {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, UserListFragment())
                .commit()

        } ?: run {
            supportFragmentManager.beginTransaction()
                .replace(R.id.frameLayout, ProfileFragment())
                .commit()
        }
    }

    public override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        navigateUserShowBy(captureURL)
    }

    private fun navigateUserShowBy(url: String?) {
        val intent = Intent(this, UserShowActivity::class.java)
        intent.putExtra("url", url)
        startActivity(intent)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        var result = IntentIntegrator.parseActivityResult(requestCode,resultCode, data)
        if(result != null) {
            if(result.getContents() == null) {
                Toast.makeText(this, "キャンセルされました", Toast.LENGTH_LONG).show()
            } else {
                captureURL = result.contents
                Toast.makeText(this, "スキャンに成功しました", Toast.LENGTH_LONG).show()
                navigateUserShowBy(captureURL)
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}
