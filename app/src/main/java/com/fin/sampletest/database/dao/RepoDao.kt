package com.fin.sampletest.database.dao

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.fin.sampletest.data.RepoResponse

@Dao
interface RepoDAO
{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBRepoDetails(data: RepoResponse)

    @Query(value = "Select * from reporesponse")
    fun getAllDetails() : List<RepoResponse>

 }