package br.com.cinq.androidtestcinq.persistence

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import android.arch.persistence.room.Update
import io.reactivex.Flowable
import io.reactivex.Single


@Dao
interface UserDAO {

    @Query("SELECT * FROM user")
    fun getAll(): Flowable<MutableList<User>>

    @Query("SELECT * FROM user WHERE name LIKE :name")
    fun getByName(name: String): Flowable<MutableList<User>>

    @Query("SELECT * FROM user WHERE email LIKE :email")
    fun getByEmail(email: String): Single<User>

    @Query("SELECT * FROM user WHERE email LIKE :email AND password LIKE :password")
    fun validate(email: String, password: String): Single<User>

    @Insert(onConflict = REPLACE)
    fun insert(user: User)

    @Query("DELETE from user WHERE id=:id")
    fun deleteById(id: Long)

    @Query("SELECT * FROM user WHERE id=:id")
    fun getById(id: Long): Single<User>

    @Update()
    fun update(user: User)
}