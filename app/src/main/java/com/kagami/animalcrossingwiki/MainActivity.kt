package com.kagami.animalcrossingwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.kagami.animalcrossingwiki.db.FishDao
import com.kagami.animalcrossingwiki.db.FishItem
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.coroutines.*
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() , HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>
    @Inject
    lateinit var fishDao:FishDao
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//       MainScope().launch {
//           loadData()
//       }
    }

    suspend fun loadData()= withContext(Dispatchers.IO){
        val doc=Jsoup.connect("https://wiki.biligame.com/dongsen/%E5%8D%9A%E7%89%A9%E9%A6%86%E5%9B%BE%E9%89%B4").get()
        val table=doc.select("#CardSelectTr").select("tr")
        Timber.d("kagamilog:${table[0]}")
        for(i in 1 until table.size){
            parseRow(table[i])
        }
        Timber.d("kagamilog:${table.size}")

    }
    fun parseRow(tr:Element){

        val tds=tr.select("td")
        val item=FishItem()
        item.name=tds[0].select("a[title]").attr("title")
        item.imagePath=tds[0].select("img[src]").attr("src")
        item.place=tds[1].text()
        item.size=tds[2].text()
        item.month1=tds[3].text()
        item.month2=tds[4].text()
        item.interval=tds[5].text()
        item.price=tds[6].text().toIntOrNull() ?: 0
        fishDao.insert(item)
        Timber.d("kagamilog:${tds[1]}")
        Timber.d("kagamilog:${item.name} ${item.place}  ${item.price}")
    }

    override fun supportFragmentInjector() = dispatchingAndroidInjector
}
