package co.edu.uninorte.betit.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

import co.edu.uninorte.betit.model.Match;

@Dao
public interface BetsDao {

    @Query("Select * from `match`")
    LiveData<List<Match>> getAll();

    @Insert (onConflict = OnConflictStrategy.REPLACE)
    void insert(Match match);

    @Delete
    void delete(Match match);

}
