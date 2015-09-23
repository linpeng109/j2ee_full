package com.cn.cassandra.tables;

import org.springframework.data.cassandra.mapping.Column;
import org.springframework.data.cassandra.mapping.PrimaryKey;
import org.springframework.data.cassandra.mapping.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Table(value = "person")
public class Person {

    @PrimaryKey
    private UUID id;

    @Column(value = "name")
    private String name;

    @Column(value = "age")
    private int age;

    @Column(value = "address")
    private String address;

    @Column(value = "personMap")
    private Map<String, String> personMap;

    public Person(String name, int age, String address) {
        this.id = UUID.randomUUID();
        this.name = name;
        this.age = age;
        this.address = address;
        this.personMap = new HashMap<String, String>();
        personMap.put("name", new String("linpeng"));
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }


    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", name=" + name + ", age=" + age + ",address=" + address + "]";
    }

}