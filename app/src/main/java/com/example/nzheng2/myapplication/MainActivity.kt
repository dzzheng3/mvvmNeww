package com.example.nzheng2.myapplication

import android.app.Activity
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.databinding.DataBindingUtil
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.View
import android.widget.Toast
import com.example.nzheng2.myapplication.databinding.ActivityMainBinding
import com.example.nzheng2.myapplication.recyclerview.PersonListAdapter
import com.example.nzheng2.myapplication.room.Person
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), AFragment.OnFragmentInteractionListener,
    BFragment.OnFragmentInteractionListener {

    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    companion object {
        const val newPersonActivityRequestCode = 1
    }

    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        var activityMainBinding = DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)
        activityMainBinding.person = Person("aaaaa", "bbbbb")
        viewModel.getUsers().observe(this, Observer {
            activityMainBinding.person = it
        })

        val adapter = PersonListAdapter(this)
        rv.adapter = adapter
        rv.layoutManager = LinearLayoutManager(this)

        viewModel.allPerson.observe(this, Observer {
            adapter.setItems(it ?: emptyList())
        })

        bt_insert.setOnClickListener() {
            //            viewModel.insert(Person("aaa", "ccc"))
            val intent = Intent(this@MainActivity, NewPersonActivity::class.java)
            startActivityForResult(intent, newPersonActivityRequestCode)
        }
        bt_remove.setOnClickListener() {
            viewModel.removeAll()
        }
        tv_link.setClickable(true);
        tv_link.setMovementMethod(LinkMovementMethod.getInstance());
        tv_link.setText(Html.fromHtml("<a href='http://zheng.com'> zheng </a>"))
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.let {
            var person = Person(it.getStringExtra(NewPersonActivity.EXTRA_REPLY), "test")
            viewModel.insert(person)
        }
        if (requestCode == newPersonActivityRequestCode && resultCode == Activity.RESULT_OK) {
            data?.let {
                var person = Person(it.getStringExtra(NewPersonActivity.EXTRA_REPLY), "test")
                viewModel.insert(person)
            }
        } else
            Toast.makeText(this, R.string.empty_not_saved, Toast.LENGTH_SHORT).show()
    }

}
