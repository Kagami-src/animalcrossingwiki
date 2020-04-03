package com.kagami.animalcrossingwiki

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.kagami.animalcrossingwiki.datasource.LocalJsonDataSource
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.InsectDao
import com.kagami.animalcrossingwiki.ui.fish.FishListFragment
import com.kagami.animalcrossingwiki.ui.insect.InsectListFragment
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MainActivity : AppCompatActivity() , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var fishDao:FishDao
    @Inject
    lateinit var insectDao:InsectDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        MainScope().launch {
            importData()
            viewPager.adapter=FragmentPagerAdapter(
                supportFragmentManager,
                arrayOf(getString(R.string.fish),getString(R.string.insect))
            )
            tabs.setupWithViewPager(viewPager)
            progressBar.visibility= View.GONE
            coordinatorLayout.visibility=View.VISIBLE
        }
    }

    suspend fun importData() = withContext(Dispatchers.IO){
        if(fishDao.count()==0){
            val ds=LocalJsonDataSource(assets.open("fish.json"),assets.open("insect.json"))
            fishDao.insertAllTDO(ds.fetchFishData())
            insectDao.insertAllTDO(ds.fetchInsectData())
        }
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector



    class FragmentPagerAdapter(fm: FragmentManager,val titles:Array<String>) : androidx.fragment.app.FragmentPagerAdapter(fm,
        BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT
    ){
        override fun getItem(position: Int): Fragment {
            return when(position){
                0 -> FishListFragment()
                1 -> InsectListFragment()
                else -> Fragment()
            }
        }

        override fun getCount(): Int = 2

        override fun getPageTitle(position: Int): CharSequence? {
            return titles.getOrNull(position) ?: super.getPageTitle(position)
        }

    }
}
