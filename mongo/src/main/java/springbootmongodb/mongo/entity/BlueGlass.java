package springbootmongodb.mongo.entity;

import org.springframework.data.mongodb.core.mapping.Field;

import java.io.Serializable;

public class BlueGlass implements Serializable {
    private static final long serialVersionUID = -3258839839160856613L;
    @Field("id")
    private Long id;
    @Field("blue_glass_name")
    private String blueGlassName;
    @Field("password")
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBlueGlassName() {
        return blueGlassName;
    }

    public void setBlueGlassName(String blueGlassName) {
        this.blueGlassName = blueGlassName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", blueGlassName='" + blueGlassName + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
