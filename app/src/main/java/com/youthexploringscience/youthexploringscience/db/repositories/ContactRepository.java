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

package com.youthexploringscience.youthexploringscience.db.repositories;

import android.app.Application;
import android.arch.lifecycle.LiveData;

import com.youthexploringscience.youthexploringscience.db.YesDatabase;
import com.youthexploringscience.youthexploringscience.db.dao.ContactDao;
import com.youthexploringscience.youthexploringscience.db.entity.ContactEntity;

import java.util.List;

public class ContactRepository {

    private ContactDao mContactDao;
    private LiveData<List<ContactEntity>> mAllContacts;

    ContactRepository(Application application) {
        YesDatabase database = YesDatabase.getInstance(application);
        mContactDao = database.contactDao();
        mAllContacts = mContactDao.loadAllContacts();
    }

    LiveData<List<ContactEntity>> loadAllContacts() {
        return mAllContacts;
    }

    //TODO:  retrieve cloud data from Google Sheet and sync with db

}
