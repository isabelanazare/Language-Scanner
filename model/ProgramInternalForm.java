package model;

import java.util.ArrayList;
import java.util.List;

public class ProgramInternalForm {
    private List<Pair<Integer, Integer>> content = new ArrayList<>();

    public void add(int code, int id) {
        this.content.add(new Pair<>(code, id));
    }

    public List<Pair<Integer, Integer>> getContent() {
        return this.content;
    }
}
