package com.kagami.animalcrossingwiki

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.kagami.animalcrossingwiki.datasource.BiliWikiDataSource
import com.kagami.animalcrossingwiki.datasource.DataSourceUtil
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.apache.commons.io.FileUtils
import java.io.File

class DevActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dev)
    }
    suspend fun savejson() = withContext(Dispatchers.IO){
        val ds= BiliWikiDataSource()
        val json= DataSourceUtil.toInsectJsonFromSource(ds)
        FileUtils.writeStringToFile(File(getExternalFilesDir(null),"insect.json"),json, Charsets.UTF_8)
    }
}
