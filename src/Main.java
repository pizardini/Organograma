import java.util.*;

class Setor {
    private String name;
    private List<Setor> subordinates;

    public Setor(String name) {
        this.name = name;
        this.subordinates = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public List<Setor> getSubordinates() {
        return subordinates;
    }

    public void addSubordinate(Setor subordinate) {
        subordinates.add(subordinate);
    }
}