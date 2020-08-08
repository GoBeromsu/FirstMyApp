package com.myapplication.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "plan_table")
class Plan(

    @ColumnInfo(name = "content") val content: String,
    @ColumnInfo(name = "time") val time: Int
){
    @PrimaryKey(autoGenerate = true) var  id:Int=0

}
