package springbootmongodb.mongo.entity;

import org.springframework.data.annotation.Id;

public class Car {
    @Id
    private String id;
    private String name;
    private String fast;

    public void setName(String name) {
        this.name = name;
    }

    public void setFast(String fast) {
        this.fast = fast;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getFast() {
        return fast;
    }

    public Car(String name, String fast) {
        this.name = name;
        this.fast = fast;
    }

}
