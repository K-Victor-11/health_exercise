package com.exercise.health_exercise

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.exercise.health_exercise.data.exercises.ExercisesData
import com.exercise.health_exercise.data.exercises.ExercisesRepository
import com.exercise.health_exercise.data.health_list.HealthListData
import com.exercise.health_exercise.data.health_list.HealthListRepository
import com.exercise.health_exercise.database.AppDataBase
import com.exercise.health_exercise.utils.PreferenceUtils
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class SplashActivity : AppCompatActivity() {
    val healthListRepository by lazy {
        HealthListRepository(application)
    }

    val exercisesRepository by lazy {
        ExercisesRepository(application)
    }

    val healthItemListRepository by lazy {
        HealthListRepository(application)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
    }

    override fun onResume() {
        super.onResume()
        init()

        var intent:Intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }

    fun init(){
        /** DB 생성 **/
        AppDataBase.getInstance(this)

        /** DB Data Insert **/
//        var executor:Executor = Executors.newSingleThreadExecutor()
//        executor.execute(Runnable {
//
//        })
        var prefUtils : PreferenceUtils = PreferenceUtils.getInstance(this)

        if(!prefUtils.getBoolean("default_data", false)) {
            var healthList:ArrayList<HealthListData> = ArrayList<HealthListData>()
            healthList.add(HealthListData(0L, "운동리스트 1", "D"))
            healthList.add(HealthListData(0L, "운동리스트 2", "D"))
            healthList.add(HealthListData(0L, "운동리스트 3", "D"))
            healthList.add(HealthListData(0L, "운동리스트 4", "D"))
            healthList.add(HealthListData(0L, "운동리스트 5", "D"))
            healthList.add(HealthListData(0L, "운동리스트 6", "D"))

            healthList.forEachIndexed { index, healthListData ->
                AppDataBase.getInstance(this).healthListDao().insert(healthListData)
            }

            var exerciseList:ArrayList<ExercisesData> = ArrayList<ExercisesData>()
            exerciseList.add(ExercisesData(0L, "운동 1", 3, 30000, "운동설명!!!!", "i_11", false))
            exerciseList.add(ExercisesData(0L, "운동 2", 5, 60000, "운동설명22222!!!!", "i_12", false))
            exerciseList.add(ExercisesData(0L, "운동 3", 4, 20000, "운동설명333333!!!!", "i_13", false))
            exerciseList.add(ExercisesData(0L, "운동 4", 2, 25000, "운동설명444444!!!!", "i_14", false))
            exerciseList.add(ExercisesData(0L, "운동 5", 6, 40000, "운동설명55555!!!!", "i_15", false))
            exerciseList.add(ExercisesData(0L, "운동 6", 10, 35000, "운동설명66666!!!!", "i_16", false))

            exerciseList.forEachIndexed { index, exercisesData ->
                AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
            }

            prefUtils.setBoolean("default_data", true)
        }



//
//        var exercisesData : ExercisesData = ExercisesData( 0L, "테스트 운동1", 3, 30, "운동 설명 입니다.", "")
//        AppDataBase.getInstance(this).exercisesDao().insert(exercisesData)
    }
}