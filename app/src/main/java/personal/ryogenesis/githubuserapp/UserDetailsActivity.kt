package personal.ryogenesis.githubuserapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.bumptech.glide.Glide
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

        with(binding){
            Glide.with(this@UserDetailsActivity)
                .load(user.avatarImg)
                .into(imgAvatar)

            tvUsername.text = user.username
            tvName.text = user.name
            tvLocation.text = user.location
            tvCompany.text = user.company
            tvFollowers.text = user.followers
            tvFollowing.text = user.following
            tvRepository.text = user.repository
        }
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