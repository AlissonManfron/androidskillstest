package br.com.cinq.androidtestcinq.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Single


@Dao
interface UserDAO {

    @Query("SELECT * FROM user")
    fun gelAll(): Flowable<MutableList<User>>

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun gelByName(name: String): Flowable<MutableList<User>>

    @Query("SELECT * FROM user WHERE name LIKE :name AND password LIKE :password")
    fun validate(name: String, password: String): Single<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE from user WHERE id=:id")
    fun deleteById(id: Long)

    @Query("UPDATE user SET name=:name, email=:email, password=:password WHERE id=:id")
    fun update(name: String, email: String, password: String, id: Long)
}