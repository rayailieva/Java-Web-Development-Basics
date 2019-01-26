package fdmc.domain.entities;

public class Cat {

    private String name;
    private String breed;
    private String color;
    private Integer age;

    public Cat(String name, String breed, String color, Integer age) {
        this.name = name;
        this.breed = breed;
        this.color = color;
        this.age = age;
    }

    public Cat() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return this.breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getColor() {
        return this.color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getAge() {
        return this.age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}

