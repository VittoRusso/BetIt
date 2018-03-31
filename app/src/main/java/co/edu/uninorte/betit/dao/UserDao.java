package co.edu.uninorte.betit.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import co.edu.uninorte.betit.model.User;


@Dao
public interface UserDao {

    @Query("Select * from `user`")
    LiveData<List<User>> getAllUsers();

    @Query("Select COUNT(*) from `user` where email =:email AND password =:pass")
    Integer checkUser (String email, String pass);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertUser (User user);

    @Delete
    void deleteUser (User user);

}