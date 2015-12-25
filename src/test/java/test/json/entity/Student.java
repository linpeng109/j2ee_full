package test.json.entity;

import test.json.entity.Contact;

import java.util.Date;

/**
 * Created by jupiter on 15-12-21.
 */
public class Student {
    public String studentId;
    public Date bathday;
    public String name;
    public Contact contact;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getBathday() {
        return bathday;
    }

    public void setBathday(Date bathday) {
        this.bathday = bathday;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }
}
