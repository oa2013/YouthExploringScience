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

package com.youthexploringscience.youthexploringscience.viewmodel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.youthexploringscience.youthexploringscience.db.entity.ContactEntity;
import com.youthexploringscience.youthexploringscience.db.repositories.ContactRepository;

import java.util.List;

public class ContactListViewModel extends AndroidViewModel {

    private ContactRepository mContactRepository;
    private LiveData<List<ContactEntity>> mAllContacts;

    public ContactListViewModel(@NonNull Application application) {
        super(application);
        mContactRepository = new ContactRepository(application);
        mAllContacts = mContactRepository.loadAllContacts();
    }

    public LiveData<List<ContactEntity>> getAllContacts() {
        return mAllContacts;
    }
}
