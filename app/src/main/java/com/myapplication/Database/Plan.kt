package com.myapplication.Database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "plan_table")
class Plan(
    @PrimaryKey(autoGenerate = true) var id: Int?,
    @ColumnInfo(name = "content") var content: String?,
    @ColumnInfo(name = "time") var time: Int
){
    constructor():this(null,"",0)
}