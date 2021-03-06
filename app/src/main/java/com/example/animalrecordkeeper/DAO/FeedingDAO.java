package com.example.animalrecordkeeper.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.animalrecordkeeper.Entities.FeedingEntity;

import java.util.List;

@Dao
public interface FeedingDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(FeedingEntity feeding);

    @Query("DELETE FROM feeding_table")
    void deleteAllFeedings();

    @Query("SELECT * FROM feeding_table WHERE basicStatus != 2 ORDER BY feedingId DESC")
    LiveData<List<FeedingEntity>> getAllFeedings();

    @Query("UPDATE feeding_table SET basicStatus = 2 WHERE feedingId == :id")
    void deleteById(int id);

    @Query("SELECT * FROM feeding_table WHERE animalId == :id AND basicStatus != 2 ORDER BY feedingId ASC")
    LiveData<List<FeedingEntity>> getFeedingsByAnimalId(int id);
}
