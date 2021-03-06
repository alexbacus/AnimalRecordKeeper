package com.example.animalrecordkeeper.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.animalrecordkeeper.DAO.AnimalDAO;
import com.example.animalrecordkeeper.DAO.FeedingDAO;
import com.example.animalrecordkeeper.DAO.GroupDAO;
import com.example.animalrecordkeeper.DAO.IntakeFormDAO;
import com.example.animalrecordkeeper.Entities.AnimalEntity;
import com.example.animalrecordkeeper.Entities.FeedingEntity;
import com.example.animalrecordkeeper.Entities.GroupEntity;
import com.example.animalrecordkeeper.Entities.IntakeFormEntity;

import java.util.List;

public class AnimalManagementRepository {
    private GroupDAO mGroupDao;
    private AnimalDAO mAnimalDao;
    private FeedingDAO mFeedingDao;
    private IntakeFormDAO mIntakeFormDao;
    private LiveData<List<GroupEntity>> mAllGroups;
    private LiveData<List<AnimalEntity>> mAllAnimals;
    private LiveData<List<FeedingEntity>> mAllFeedings;
    private LiveData<List<IntakeFormEntity>> mAllIntakeForms;
    AnimalManagementDatabase db;

    public AnimalManagementRepository(Application application) {
        db = AnimalManagementDatabase.getDatabase(application);
        mGroupDao = db.groupDAO();
        mAllGroups = mGroupDao.getAllGroups();
        mAnimalDao = db.animalDAO();
        mAllAnimals = mAnimalDao.getAllAnimals();
        mFeedingDao = db.feedingDAO();
        mAllFeedings = mFeedingDao.getAllFeedings();
        mIntakeFormDao = db.intakeFormDAO();
        mAllIntakeForms = mIntakeFormDao.getAllIntakeForms();
    }

    public LiveData<List<GroupEntity>> getAllGroups() {
        return mAllGroups;
    }

    public LiveData<List<AnimalEntity>> getAllAnimals() { return mAllAnimals; }

    public LiveData<List<FeedingEntity>> getAllFeedings() { return mAllFeedings; }

    public LiveData<List<IntakeFormEntity>> getAllIntakeForms() { return mAllIntakeForms; }

    public void insert(GroupEntity group) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mGroupDao.insert(group);
        });
    }

    public void insert(AnimalEntity animal) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mAnimalDao.insert(animal);
        });
    }

    public void insert(FeedingEntity feeding) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mFeedingDao.insert(feeding);
        });
    }

    public void insert(IntakeFormEntity intakeForm) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mIntakeFormDao.insert(intakeForm);
        });
    }

    public void deleteAnimal(int id) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mAnimalDao.deleteById(id);
        });
    }

    public void deleteGroup(int id) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mGroupDao.deleteById(id);
        });
    }

    public void deleteFeeding(int id) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mFeedingDao.deleteById(id);
        });
    }

    public void deleteIntakeForm(int id) {
        AnimalManagementDatabase.databaseWriteExecutor.execute(() -> {
            mIntakeFormDao.deleteById(id);
        });
    }

    public LiveData<List<AnimalEntity>> searchAnimals(String name) {
        mAnimalDao = db.animalDAO();
        return mAnimalDao.getAnimalByName(name);
    }

    public LiveData<List<AnimalEntity>> getAnimalsByGroupId(int id) {
        mAnimalDao = db.animalDAO();
        return mAnimalDao.getAnimalsByGroupId(id);
    }

    public void updateRecentFeeding(String recentFeeding, int animalId) {
        mAnimalDao = db.animalDAO();
        mAnimalDao.updateRecentFeeding(recentFeeding, animalId);
    }

    public LiveData<List<FeedingEntity>> getFeedingsByAnimalId(int id) {
        mFeedingDao = db.feedingDAO();
        return mFeedingDao.getFeedingsByAnimalId(id);
    }
}
