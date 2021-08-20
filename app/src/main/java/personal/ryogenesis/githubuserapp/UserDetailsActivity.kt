package personal.ryogenesis.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import personal.ryogenesis.githubuserapp.databinding.ActivityMainBinding
import personal.ryogenesis.githubuserapp.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    companion object{
        const val USER_OBJ = "selected_user"
    }
    private lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        insertAndSetupValues()
    }

    private fun insertAndSetupValues(){
        val user = intent.getParcelableExtra<User>(USER_OBJ) as User

        supportActionBar?.title = "${user.username} Profile"

        Glide.with(this)
            .load(user.avatarImg)
            .into(binding.imgAvatar)

        binding.tvUsername.text = user.username
        binding.tvName.text = user.name
        binding.tvLocation.text = user.location
        binding.tvCompany.text = user.company
        binding.tvFollowers.text = user.followers
        binding.tvFollowing.text = user.following
        binding.tvRepository.text = user.repository
    }

    override fun finish() {
        super.finish()
        overridePendingTransition(android.R.anim.fade_in,android.R.anim.slide_out_right)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}