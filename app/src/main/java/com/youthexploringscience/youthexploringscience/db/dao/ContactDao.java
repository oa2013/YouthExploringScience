/*
 * Copyright (C) 2018 Janice Wildrick
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.youthexploringscience.youthexploringscience.db.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.youthexploringscience.youthexploringscience.db.entity.ContactEntity;

import java.util.List;

@Dao
public interface ContactDao {

    @Query("SELECT * FROM contacts")
    LiveData<List<ContactEntity>> loadAllContacts();

    @Query("SELECT * FROM contacts WHERE contactName = :contactName")
    LiveData<ContactEntity> loadContact(String contactName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContacts(ContactEntity... contacts);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertContact(ContactEntity contactEntity);

    @Query("DELETE FROM contacts")
    public void deleteAllContacts();

    @Delete
    public void deleteContact(ContactEntity contactEntity);
}
