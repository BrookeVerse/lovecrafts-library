package org.example;

public abstract class People {
    public int id;
    public String name;

    public boolean admin;

    public People(int id, String name, boolean admin) {
        this.id = id;
        this.name = name;
        this.admin = admin;
    }

}
