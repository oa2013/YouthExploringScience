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
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.youthexploringscience.youthexploringscience.db.Contact;

@Entity(tableName = "contacts")
public class ContactEntity implements Contact {
    @PrimaryKey
    @NonNull
    private String contactName;
    private String contactTitle;
    private String contactPhone;
    private String contactEmail;
    private String contactImageLink;


    public ContactEntity(@NonNull String contactName, String contactTitle, String contactPhone, String contactEmail, String contactImageLink) {
        this.contactName = contactName;
        this.contactTitle = contactTitle;
        this.contactPhone = contactPhone;
        this.contactEmail = contactEmail;
        this.contactImageLink = contactImageLink;
    }

    @Override
    public String getContactName() {
        return contactName;
    }

    public void setContactName(String name) {
        contactName = name;
    }

    @Override
    public String getContactTitle() {
        return contactTitle;
    }

    public void setContactTitle(String title) {
        contactTitle = title;
    }

    @Override
    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String phone) {
        contactPhone = phone;
    }

    @Override
    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String email) {
        contactEmail = email;
    }

    @Override
    public String getContactImageLink() {
        return contactImageLink;
    }

    public void setContactImageLink(String imageLink) {
        contactImageLink = imageLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactEntity that = (ContactEntity) o;

        return contactName.equals(that.contactName);
    }

    @Override
    public int hashCode() {
        return contactName.hashCode();
    }
}
