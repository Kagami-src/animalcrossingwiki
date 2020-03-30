package com.kagami.animalcrossingwiki

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelLazy
import androidx.lifecycle.ViewModelProvider
import com.kagami.animalcrossingwiki.di.Injectable
import com.kagami.animalcrossingwiki.viewmodel.FishListViewModel
import timber.log.Timber
import javax.inject.Inject

/**
 * A simple [Fragment] subclass.
 */
class FishListFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    val fishListViewModel:FishListViewModel by lazy {
        ViewModelProvider(viewModelStore,viewModelFactory).get(FishListViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fish_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        fishListViewModel.fishListLiveData.observe(this, Observer {
            it.forEach {
                Timber.e("kagamilog:${it.imagePath}")
            }
        })
    }

}
