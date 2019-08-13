package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.R
import kotlinx.coroutines.launch

class UserListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter
    private lateinit var viewModel: UserListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.user_list)
        viewModel = ViewModelProviders.of(this)[UserListViewModel::class.java]

        recyclerView.setHasFixedSize(true)
        adapter = UserListAdapter(view.context)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        insertUserData()
        val swipeToDismissTouchHelper = adapter.getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    private fun insertUserData() {
        viewModel.getLiveUsers().observe(this, Observer<List<User>> {
            users -> adapter.updateUserList(users.toMutableList())
        })
    }

}
