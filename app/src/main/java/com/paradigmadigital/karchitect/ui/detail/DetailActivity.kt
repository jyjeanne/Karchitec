package com.paradigmadigital.karchitect.ui.detail

import android.arch.lifecycle.ViewModelProviders
import com.paradigmadigital.karchitect.platform.BaseActivity

class DetailActivity : BaseActivity() {

    val CHANNEL_KEY = "om.paradigmadigital.karchitect.channel.key"

    @javax.inject.Inject
    lateinit var decorator: DetailActivityDecorator
    @javax.inject.Inject
    lateinit var presenter: DetailActivityPresenter

    lateinit var viewModel: DetailViewModel;

    override fun onCreate(bundle: android.os.Bundle?) {
        super.onCreate(bundle)
        setContentView(com.paradigmadigital.karchitect.R.layout.activity_detail)
        activityComponent.inject(this)

        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
        viewModel.initialize(bundle?.getString(CHANNEL_KEY) ?: "")

        decorator.bind(getRootView())
        presenter.initialize(decorator, viewModel)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dispose()
        decorator.dispose()
    }
}
