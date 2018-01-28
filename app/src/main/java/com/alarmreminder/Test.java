package com.alarmreminder;



public class Test {

    String name;
    String type;
    String result;
    String description;
    String date;
    String hour;
// konstruktor danej klasy z implementacją przepisania danych wejściowych
    public Test(String name, String type, String result, String description, String date, String hour){

        this.name=name;
        this.type=type;
        this.result = result;
        this.description = description;
        this.date=date;
        this.hour=hour;


    }

//metody zwracające poszczególne zmienne
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }
}
