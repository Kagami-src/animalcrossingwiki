package com.kagami.animalcrossingwiki.datasource

import android.util.Base64
import org.apache.commons.io.IOUtils
import org.jsoup.Jsoup
import org.jsoup.nodes.Element
import java.net.URL

class BiliWikiDataSource():WikiDataSource {
    override suspend fun fetchFishData(): List<FishDTO> {
        val doc= Jsoup.connect("https://wiki.biligame.com/dongsen/%E5%8D%9A%E7%89%A9%E9%A6%86%E5%9B%BE%E9%89%B4").get()
        val table=doc.select("#CardSelectTr").select("tr")
        val result= mutableListOf<FishDTO>()
        for(i in 1 until table.size){
            result.add(parseRow(table[i]))
        }
        return result
    }

    private fun parseRow(tr: Element):FishDTO{
        val tds=tr.select("td")
        val imageurl=tds[0].select("img[src]").attr("src")
        val bs=IOUtils.toByteArray(URL(imageurl))
        return FishDTO(
            name = tds[0].select("a[title]").attr("title"),
            imagePath = imageurl,
            place = tds[1].text(),
            size = tds[2].text(),
            month1 = numberCustomFormat(tds[3].text()),
            month2 = numberCustomFormat(tds[4].text()),
            interval = numberCustomFormat(tds[5].text()),
            price = tds[6].text().toIntOrNull() ?: 0,
            imageData = Base64.encodeToString(bs,Base64.DEFAULT)
        )
    }

    private fun numberCustomFormat(str:String):String
            = str.replace(Regex("[^0-9]+")," ").trim()
}