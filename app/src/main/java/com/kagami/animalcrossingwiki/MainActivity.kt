package com.kagami.animalcrossingwiki

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.kagami.animalcrossingwiki.datasource.BiliWikiDataSource
import com.kagami.animalcrossingwiki.datasource.DataSourceUtil
import com.kagami.animalcrossingwiki.datasource.LocalJsonDataSource
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.FishItem
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.*
import org.apache.commons.io.FileUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import timber.log.Timber
import java.io.File
import java.nio.charset.Charset
import javax.inject.Inject

class MainActivity : AppCompatActivity() , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var fishDao:FishDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //assets.openFd()
//       MainScope().launch {
//           loadData()
//       }

//        MainScope().launch {
//            savejson()
//        }
        MainScope().launch {
            importData()
            //savejson()
        }

        //FIXME
        //startActivity(Intent(this,DevActivity::class.java))
    }

    suspend fun importData() = withContext(Dispatchers.IO){
        Timber.e("kagamilog  size  ${fishDao.count()}")
        if(fishDao.count()==0){
            val ds=LocalJsonDataSource(assets.open("fish.json"))
            fishDao.insertAllTDO(ds.fetchFishData())
        }
    }

    suspend fun savejson() = withContext(Dispatchers.IO){
        val ds=BiliWikiDataSource()
        val json=DataSourceUtil.toFishJsonFromSource(ds)
        FileUtils.writeStringToFile(File(getExternalFilesDir(null),"data.json"),json, Charsets.UTF_8)
    }
    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
