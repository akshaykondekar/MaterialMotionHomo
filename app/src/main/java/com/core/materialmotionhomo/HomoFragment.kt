package com.core.materialmotionhomo

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.core.materialmotionhomo.databinding.FragmentHomoBinding

private const val ARG_HOMO_ID = "homo_id"

class HomoFragment : Fragment() {
    private lateinit var binding : FragmentHomoBinding
    private var homoId: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            homoId = it.getInt(ARG_HOMO_ID)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_homo, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val homo = homos.singleOrNull{ it.id == homoId}
        homo.let {
            binding.homo = it
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: Int) =
            HomoFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_HOMO_ID, param1)
                }
            }
    }
}