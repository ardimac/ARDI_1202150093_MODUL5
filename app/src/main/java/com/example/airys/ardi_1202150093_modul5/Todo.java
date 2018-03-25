package com.example.airys.ardi_1202150093_modul5;

/**
 * Created by airys on 22/03/18.
 */

public class Todo {

    private long id;
    private String name;
    private String desc;
    private String prio;

    public Todo(){
    }

    public Todo(String name, String desc, String prio) {
        this.name = name;
        this.desc = desc;
        this.prio = prio;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPrio() {
        return prio;
    }

    public void setPrio(String prio) {
        this.prio = prio;
    }


}
