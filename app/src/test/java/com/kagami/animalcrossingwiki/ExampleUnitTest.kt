package com.kagami.animalcrossingwiki

import org.junit.Test

import org.junit.Assert.*
import java.util.*
import kotlin.math.PI
import kotlin.math.cos

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
//        val str="(全年),1,2,3,12,3"
//        val out=str.replace(Regex("[^0-9]+")," ")
//        print(out)
//        print(Arrays.asList(out.trim().split(" ")))

        print(cos(60/180f* PI))


        assertEquals(4, 2 + 2)
    }
}
