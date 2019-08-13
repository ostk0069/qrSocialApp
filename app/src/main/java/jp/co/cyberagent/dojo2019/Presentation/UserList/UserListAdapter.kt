package jp.co.cyberagent.dojo2019.Presentation.UserList

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import jp.co.cyberagent.dojo2019.Database.AppDatabase
import jp.co.cyberagent.dojo2019.R
import jp.co.cyberagent.dojo2019.Entity.User
import jp.co.cyberagent.dojo2019.Presentation.Common.WebViewActivity
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class UserListAdapter(private val context: Context) :
    RecyclerView.Adapter<UserListViewHolder>(), CoroutineScope {

    var userList: MutableList<User> = mutableListOf()
    private var job: Job = Job()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListViewHolder {
        val inflater = LayoutInflater.from(context)
        val view = inflater.inflate(R.layout.user_cell, parent, false)
        return UserListViewHolder(view)
    }

    override fun getItemCount(): Int = userList.size

    override fun onBindViewHolder(holder: UserListViewHolder, position: Int) {
        val user = userList[position]
        holder.iamTextView.text = user.iam
        holder.githubTextView.text = user.githubID
        holder.twitterTextView.text = user.twitterID

        val context = holder.itemView.context
        holder.githubButton.setOnClickListener {
            val githubURL = "https://github.com/${user.githubID}"
            navigateWebView(context, githubURL)
        }
        holder.twitterButton.setOnClickListener {
            val twitterURL = "https://twitter.com/${user.twitterID}"
            navigateWebView(context, twitterURL)
        }
    }

    fun updateUserList(users: MutableList<User>) {
        userList = users
        notifyDataSetChanged()
    }

    private fun navigateWebView(context: Context, url: String) {
        val intent = Intent(context, WebViewActivity::class.java)
        intent.putExtra("url", url)
        context.startActivity(intent)
    }

    fun getSwipeToDismissTouchHelper(adapter: RecyclerView.Adapter<UserListViewHolder>) =
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
                launch {
                    val uid = userList[position].uid
                    userList.removeAt(position)
                    notifyItemRemoved(position)
                    val database = AppDatabase.getDatabase(context)
                    database.userDao().deleteByUid(uid)
                    notifyDataSetChanged()
                }
            }

            override fun onChildDraw(
                c: Canvas, recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                dX: Float, dY: Float, actionState: Int, isCurrentlyActive: Boolean
            ) {
                super.onChildDraw(c, recyclerView, viewHolder,
                    dX, dY, actionState, isCurrentlyActive)
                val itemView = viewHolder.itemView
                val background = ColorDrawable()
                background.color = Color.parseColor("#f44336")
                if (dX < 0)
                    background.setBounds(itemView.right + dX.toInt(),
                        itemView.top, itemView.right, itemView.bottom)
                else
                    background.setBounds(itemView.left, itemView.top,
                        itemView.left + dX.toInt(), itemView.bottom)
                background.draw(c)
            }
        })
}