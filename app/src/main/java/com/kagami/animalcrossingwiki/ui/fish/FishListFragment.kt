package com.kagami.animalcrossingwiki.ui.fish

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.kagami.animalcrossingwiki.R
import com.kagami.animalcrossingwiki.di.Injectable
import com.kagami.animalcrossingwiki.viewmodel.FishListViewModel
import kotlinx.android.synthetic.main.fragment_fish_list.*
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

    lateinit var adapter:FishListAdapter
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter= FishListAdapter()
        fishListViewModel.fishListLiveData.observe(this, Observer {
            adapter.updateList(it)
        })
        recyclerview.adapter=adapter
    }

}
