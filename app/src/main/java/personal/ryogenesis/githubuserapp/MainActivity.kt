package personal.ryogenesis.githubuserapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Adapter
import androidx.recyclerview.widget.*
import personal.ryogenesis.githubuserapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var list: ArrayList<User> = arrayListOf()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Github User"

        binding.rvUsers.apply {
            setHasFixedSize(true)
            addItemDecoration(
                DividerItemDecoration(
                    binding.rvUsers.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        prepareUserData()
        showRecyclerList()
    }

    private fun prepareUserData() {
        val dataUsername = resources.getStringArray(R.array.username)
        val dataName = resources.getStringArray(R.array.name)
        val dataLocation = resources.getStringArray(R.array.location)
        val dataRepository = resources.getStringArray(R.array.repository)
        val dataCompany = resources.getStringArray(R.array.company)
        val dataFollowers = resources.getStringArray(R.array.followers)
        val dataFollowing = resources.getStringArray(R.array.following)
        val dataAvatar = resources.obtainTypedArray(R.array.avatar)

        for (position in dataUsername.indices) {
            val user = User(
                dataUsername[position],
                dataName[position],
                dataLocation[position],
                dataRepository[position],
                dataCompany[position],
                dataFollowers[position],
                dataFollowing[position],
                dataAvatar.getResourceId(position,-1)
            )
            list.add(user)
            dataAvatar.recycle()
        }
    }

    private fun activityTransition(intent: Intent){
        startActivity(intent)
        overridePendingTransition(R.anim.slide_in_right, android.R.anim.fade_out)
    }

    private fun showRecyclerList(){
        val listUserAdapter = ListUserAdapter(list)
        binding.rvUsers.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = listUserAdapter
        }

        listUserAdapter.setOnItemClickCallback(object: ListUserAdapter.OnItemClickCallback{
            override fun onItemClicked(data: User) {
                val userDataIntent = Intent(this@MainActivity, UserDetailsActivity::class.java)
                userDataIntent.putExtra(UserDetailsActivity.USER_OBJ, data)
                activityTransition(userDataIntent)
            }
        })
    }
}