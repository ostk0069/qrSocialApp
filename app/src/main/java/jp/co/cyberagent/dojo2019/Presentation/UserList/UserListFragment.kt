package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.Entity.User

import jp.co.cyberagent.dojo2019.R
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserListFragment : Fragment() {

    private var database: AppDatabase? = null
    private lateinit var viewModel: UserListViewModel
    private var userList = mutableListOf<User>()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: UserListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        database = AppDatabase.getDatabase(view.context)
        viewModel = UserListViewModel(view.context)
        recyclerView = view.findViewById(R.id.user_list)

        recyclerView.setHasFixedSize(true)
        adapter = UserListAdapter(view.context, userList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(adapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
        insertUserData()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    private fun insertUserData() {
        lifecycleScope.launch {
            withContext(Dispatchers.Main) { 
                val userListData = database?.userDao()?.getAll()?: return@withContext
                userList = userListData.toMutableList()
                adapter.updateUserList(userList)
            }
        }
    }

    private fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<UserListViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition

                lifecycleScope.launch {
                    val uid = userList[position].uid
                    userList.removeAt(position)
                    adapter.notifyItemRemoved(position)
                    database?.userDao()?.deleteByUid(uid)
                    adapter.notifyDataSetChanged()
                }
//                adapter.notifyItemRemoved(position)
            }

            override fun onChildDraw(
                c: Canvas,
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float,
                dY: Float,
                actionState: Int,
                isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c,
                    recyclerView,
                    viewHolder,
                    dX,
                    dY,
                    actionState,
                    isCurrentlyActive)
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top,
                        itemView.right,
                        itemView.bottom
                    )
                else
                    background.setBounds(
                        itemView.left,
                        itemView.top,
                        itemView.left + dX.toInt(),
                        itemView.bottom
                    )
                background.draw(c)
            }
        })
}
