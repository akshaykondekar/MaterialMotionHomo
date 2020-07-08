package com.core.materialmotionhomo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.commit
import com.core.materialmotionhomo.databinding.ActivityMainBinding
import com.google.android.material.transition.MaterialSharedAxis

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var selected = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val fragment = HomoFragment.newInstance(homos[selected].id)
        supportFragmentManager.commit {
            add(R.id.fragmentContainer, fragment, FRAGMENT_TAG)
        }
        setRecyclerView()
    }

    private fun setRecyclerView() {
        val adapter = StepperAdapter(homos)
        binding.rvStepper.adapter = adapter

        adapter.stepClickListener = object : StepperAdapter.StepClickListener{
            override fun onStepClick(position: Int) {
                val forword = position >= selected
                selected = position
                val previousFragment = supportFragmentManager.findFragmentByTag(FRAGMENT_TAG)
                previousFragment?.exitTransition = buildTransition(forword)
                val fragment = HomoFragment.newInstance(homos[selected].id)
                fragment.enterTransition = buildTransition(forword)
                supportFragmentManager.commit {
                    replace(R.id.fragmentContainer, fragment, FRAGMENT_TAG)
                }
            }
        }
    }

    private fun buildTransition(forward: Boolean) =
        MaterialSharedAxis(MaterialSharedAxis.Y, forward).apply {
            duration = 500
        }

    companion object {
        private const val FRAGMENT_TAG = "HOMO_FRAGMENT"
    }
}