package model;

import java.util.HashMap;

public class SymbolTable {
    private HashMap<Integer, String> table = new HashMap<>();
    private int count = -1;

    public int add(String value) {
        this.count++;
        this.table.put(this.count, value);
        return this.count;
    }

    public HashMap<Integer, String> getMap() {
        return this.table;
    }

}
