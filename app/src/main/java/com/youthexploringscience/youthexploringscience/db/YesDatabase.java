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

package com.youthexploringscience.youthexploringscience.db;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.youthexploringscience.youthexploringscience.db.dao.ContactDao;
import com.youthexploringscience.youthexploringscience.db.dao.EventDao;
import com.youthexploringscience.youthexploringscience.db.entity.ContactEntity;
import com.youthexploringscience.youthexploringscience.db.entity.EventEntity;


@Database(entities = {ContactEntity.class, EventEntity.class}, version = 1)
public abstract class YesDatabase extends RoomDatabase {
    public static final String DATABASE_NAME = "yes_database";
    private static YesDatabase INSTANCE;

    public abstract ContactDao contactDao();
    public abstract EventDao eventDao();

    public static YesDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            synchronized (YesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            YesDatabase.class, DATABASE_NAME)
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
