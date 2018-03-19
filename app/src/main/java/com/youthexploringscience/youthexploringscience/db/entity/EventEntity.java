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

package com.youthexploringscience.youthexploringscience.db.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.youthexploringscience.youthexploringscience.db.Event;

@Entity(tableName = "events")
public class EventEntity implements Event {

    @PrimaryKey
    @NonNull
    private String eventId;
    private String eventSummary;
    private String eventHtmlLink;
    private String eventDescription;
    private String eventLocation;
    private int dateTimeStart;
    private int dateTimeEnd;

    public EventEntity(@NonNull String eventId, String eventSummary, String eventHtmlLink, String eventDescription, String eventLocation, int dateTimeStart, int dateTimeEnd) {
        this.eventId = eventId;
        this.eventSummary = eventSummary;
        this.eventHtmlLink = eventHtmlLink;
        this.eventDescription = eventDescription;
        this.eventLocation = eventLocation;
        this.dateTimeStart = dateTimeStart;
        this.dateTimeEnd = dateTimeEnd;
    }

    @Override
    public String getEventId() {
        return eventId;
    }

    public void setEventId(String id) {
        eventId = id;
    }

    @Override
    public String getEventSummary() {
        return eventSummary;
    }

    public void setEventSummary(String summary) {
        eventSummary = summary;
    }

    @Override
    public String getEventHtmlLink() {
        return eventHtmlLink;
    }

    public void setEventHtmlLink(String link) {
        eventHtmlLink = link;
    }

    @Override
    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String description) {
        eventDescription = description;
    }

    @Override
    public String getEventLocation() {
        return eventLocation;
    }

    public void setEventLocation(String location) {
        eventLocation = location;
    }

    @Override
    public int getDateTimeStart() {
        return dateTimeStart;
    }

    public void setDateTimeStart(int start) {
        dateTimeStart = start;
    }

    @Override
    public int getDateTimeEnd() {
        return dateTimeEnd;
    }

    public void setDateTimeEnd(int end) {
        dateTimeEnd = end;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EventEntity that = (EventEntity) o;

        return eventId.equals(that.eventId);
    }

    @Override
    public int hashCode() {
        return eventId.hashCode();
    }
}
