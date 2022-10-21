package com.lemonboy.handycalculator.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "calculator_history_table")
data class HistoryItem(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    @ColumnInfo(name = "input")
    val inputString: String,
    @ColumnInfo(name = "output")
    val outputString: String,
    @ColumnInfo(name = "calculated_on")
    val calculatedOn: Date?,
)