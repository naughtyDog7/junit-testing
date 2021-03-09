package uz.dev;


public class Main {

    int lastId = 0;

    synchronized int getLastId() {
        return lastId++;
    }
}

