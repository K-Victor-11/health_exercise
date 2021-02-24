package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.Menu
import android.view.View
import android.widget.Toast
import androidx.annotation.ArrayRes
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import com.exercise.health_exercise.data.AppContents
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListWithItemData
import com.exercise.health_exercise.ui.activitys.BaseActivity
import com.exercise.health_exercise.ui.activitys.ListAddActivity
import com.exercise.health_exercise.ui.custom_exercise.CustomExerciseViewModel
import com.exercise.health_exercise.ui.fragments.CompleteExerciseFragment
import com.exercise.health_exercise.ui.fragments.CustomListFragment
import com.exercise.health_exercise.ui.home.HomeFragment
import com.exercise.health_exercise.utils.DialogUtils
import com.google.android.material.bottomsheet.BottomSheetDialog
import kotlinx.android.synthetic.main.app_bar_main.*
import java.util.*
import kotlin.collections.LinkedHashMap

class MainActivity : BaseActivity(), View.OnClickListener, HomeFragment.onHomeFragmentListener {

    private lateinit var appBarConfiguration: AppBarConfiguration

    val listViewModel:CustomExerciseViewModel by lazy {
        ViewModelProvider(this, CustomExerciseViewModel.Factory(ExerciseApplication.currentActivity!!.application)).get(CustomExerciseViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val toolbar: Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)

        ExerciseApplication.currentActivity = this

        /** 플로팅 버튼 **/
//        val fab: FloatingActionButton = findViewById(R.id.fab)
//        fab.setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                    .setAction("Action", null).show()
//        }
        val drawerLayout: DrawerLayout = findViewById(R.id.drawer_layout)
//        val navView: NavigationView = findViewById(R.id.nav_view)
//        val navController = findNavController(R.id.nav_host_fragment)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
//        appBarConfiguration = AppBarConfiguration(setOf(
//                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow), drawerLayout)
//        setupActionBarWithNavController(navController, appBarConfiguration)
//        navView.setupWithNavController(navController)

//        val db:AppDataBase = Room.databaseBuilder(this, AppDataBase::class.java, "health_exercise-db")
//                .allowMainThreadQueries() /** 이값은 MainThread에서도 돌도록 만들어진 함수 **/
//                .build()

        var fragment: HomeFragment = HomeFragment.newInstance(this)
        pushFragment(R.id.nav_host_fragment, fragment)
        clMain_BottomMenu1.setOnClickListener(this)
        clMain_BottomMenu2.setOnClickListener(this)
        clMain_BottomMenu3.setOnClickListener(this)

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.d("kamuel", "MainActivity onActivityResult!!!")
        currentFragment().let {
            it!!.onActivityResult(requestCode, resultCode, data)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.nav_host_fragment)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }

    override fun onClick(v: View?) {
        when (v){
            clMain_BottomMenu1 -> {
                var fragment : HomeFragment = HomeFragment()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
            clMain_BottomMenu2 -> {
                var fragment : CustomListFragment = CustomListFragment()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
            clMain_BottomMenu3 -> {
                var fragment : CompleteExerciseFragment = CompleteExerciseFragment.newInstance()
                pushFragment(R.id.nav_host_fragment, fragment)
            }
        }
    }



    override fun onListMore(position: Int, data: HealthListWithItemData) {

    }
}