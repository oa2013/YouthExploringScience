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
import com.youthexploringscience.youthexploringscience.db.dao.EventDao;
import com.youthexploringscience.youthexploringscience.db.entity.EventEntity;

import java.util.List;

public class EventRepository {

    private EventDao mEventDao;
    private LiveData<List<EventEntity>> mAllEvents;

    EventRepository(Application application) {
        YesDatabase database = YesDatabase.getInstance(application);
        mEventDao = database.eventDao();
        mAllEvents = mEventDao.loadAllEvents();
    }

    LiveData<List<EventEntity>> loadAllEvents() {
        return mAllEvents;
    }


    //TODO:  retrieve cloud data from Google Calendar and sync with db
    /*  Research whether Calendar API push notifications can be made to app
        1. Using JobScheduler (conditions:  on wifi;  period: daily? ask Jesse)
            * delete all events from local YesDatabase
            * retrieve all contacts from Google Sheet API
            * serialize json to pojo collection (or save to db individually? research Gson)
            * save pojo collection to db
    */


}
