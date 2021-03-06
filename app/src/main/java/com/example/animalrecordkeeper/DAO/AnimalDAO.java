package com.example.animalrecordkeeper.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.animalrecordkeeper.Entities.AnimalEntity;

import java.util.List;

@Dao
public interface AnimalDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(AnimalEntity animal);

    @Query("DELETE FROM animal_table")
    void deleteAllAnimals();

    @Query("SELECT * FROM animal_table ORDER BY animalId ASC")
    LiveData<List<AnimalEntity>> getAllAnimals();

    @Query("UPDATE animal_table SET basicStatus = 2 WHERE animalId == :id")
    void deleteById(int id);

    @Query("SELECT * FROM animal_table WHERE name LIKE :name OR species LIKE :name OR healthStatus LIKE :name ORDER BY animalId ASC")
    LiveData<List<AnimalEntity>> getAnimalByName(String name);

    @Query("SELECT * FROM animal_table WHERE groupId == :id ORDER BY animalId ASC")
    LiveData<List<AnimalEntity>> getAnimalsByGroupId(int id);

    @Query("UPDATE animal_table SET recentFeeding = :recentFeeding WHERE animalId == :id")
    void updateRecentFeeding(String recentFeeding, int id);
}
