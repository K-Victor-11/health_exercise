package com.exercise.health_exercise.data.exercises

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "exercise")
data class ExercisesData(
    @PrimaryKey(autoGenerate = true)
    val idx : Long,
    val title : String,
    var revert_count : Int,
    var play_Time : Long,
    val health_Notice : String,
    val health_Photo : String,
    var checkIndex:Int,
    var check:Boolean = false) : Serializable {

}