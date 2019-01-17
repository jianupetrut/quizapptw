package ro.ase.codinquiz.quizapplication.Main.Entities;

public class Category {
    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {

        this.name = name;
    }

    public Category(int id, String name) {

        this.id = id;
        this.name = name;
    }
}
