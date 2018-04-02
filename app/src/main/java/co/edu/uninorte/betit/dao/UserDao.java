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

    @Query("Select COUNT(*) from `user` where email =:email")
    Integer checkRegister (String email);

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insertUser (User user);

    @Query("Update `user` Set score = score + 3 Where email = :email")
    void addThreeScore (String email);

    @Query("Update `user` Set score = score + 1 Where email = :email")
    void addOneScore (String email);

    @Delete
    void deleteUser (User user);


}