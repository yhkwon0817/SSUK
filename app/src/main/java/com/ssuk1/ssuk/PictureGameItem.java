package com.ssuk1.ssuk;

import java.io.Serializable;

public class PictureGameItem implements Serializable {

    public int id_;
    public String name;

    public PictureGameItem(int id_, String name) {
        this.id_ = id_;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getId_() {
        return id_;
    }
}
