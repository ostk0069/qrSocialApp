package jp.co.cyberagent.dojo2019.presentation.UserList

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.R

class UserListFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var mAdapter: UserListAdapter
    private lateinit var viewModel: UserListViewModel

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.user_list)
        viewModel = ViewModelProviders.of(this)[UserListViewModel::class.java]

        recyclerView.setHasFixedSize(true)
        mAdapter = UserListAdapter(view.context)
        recyclerView.adapter = mAdapter
        val decor = DividerItemDecoration(view.context, DividerItemDecoration.VERTICAL)
        recyclerView.addItemDecoration(decor)
        recyclerView.layoutManager = LinearLayoutManager(view.context)
        insertUserData()
        val swipeToDismissTouchHelper = getSwipeToDismissTouchHelper(mAdapter)
        swipeToDismissTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_list, container, false)
    }

    private fun insertUserData() {
        viewModel.getLiveUsers().observe(this, Observer<List<User>> { users ->
            mAdapter.updateUserList(users.toMutableList())
        })
    }

    fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<UserListViewHolder>) =
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                if (direction == ItemTouchHelper.LEFT) {
                    val position = viewHolder.adapterPosition
                    val uid = mAdapter.userList[position].uid
                    viewModel.deleteUser(uid)
                    adapter.notifyItemRemoved(position)
                } else if (direction == ItemTouchHelper.RIGHT) {
                    // TODO: add navigation to user detail
                }
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(
                    c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive
                )
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                if (dX < 0) {
                    background.color = Color.parseColor("#d0184c")
                    background.setBounds(
                        itemView.right + dX.toInt(),
                        itemView.top, itemView.right, itemView.bottom
                    )
                } else {
                    background.color = Color.parseColor("#17af98")
                    background.setBounds(
                        itemView.left, itemView.top,
                        itemView.left + dX.toInt(), itemView.bottom
                    )
                }
                background.draw(c)
            }
        })
}
