package br.com.cinq.androidtestcinq.persistence

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
class User(@PrimaryKey(autoGenerate = true) var id: Long?,
           @ColumnInfo(name = "name") var name: String,
           @ColumnInfo(name = "email") var email: String,
           @ColumnInfo(name = "password") var password: String) {

    constructor() : this(null, "", "", "")
}