package springbootmongodb.mongo.dao;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import springbootmongodb.mongo.entity.Car;
import springbootmongodb.mongo.entity.User;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class UserDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    private static final String collectionName = "user";



    public void add(){
        /*for (int i = 0; i < 20; i ++){*/
        List<Car> cars=new ArrayList<Car>();
        Car car =new Car("汽车" + 0,0+"");
        cars.add(car);
        User user = new User("测试" + 0,0);
        user.setCars(cars);
        mongoTemplate.save(user);
        /*  }*/

    }


    public void delete(){
        //只删除查询到的第一条
        //System.out.println(mongoTemplate.findAndRemove(new Query(Criteria.where("age").is(1)),User.class));


        //删除查询到的所有记录
        //System.out.println(mongoTemplate.findAllAndRemove(new Query(Criteria.where("age").gte(0)),User.class));

        //集合中删除
        Update update=new Update();
        Document doc = new Document();
        doc.put("name","汽车3");

        mongoTemplate.updateMulti(new Query(Criteria.where("cars.name").is("汽车3")),
                update.pull("cars",doc),User.class);


        /*mongoTemplate.remove(new Query(Criteria.where("age").gte(0)),User.class);*/
    }



    public void update(){
        //mongoTemplate.updateFirst() 删除符合条件的第一个

        //集合中添加
        List<Car> cars=new ArrayList<Car>();
        Car car =new Car("汽车" + 2,2+"");
       /* Car car2 =new Car("汽车" + 2,2+"");
        Car car3 =new Car("汽车" + 3,3+"");
        Car car4 =new Car("汽车" + 4,4+"");*/
        cars.add(car);
       /* cars.add(car2);
        cars.add(car3);
        cars.add(car4)*/;
        Update update=new Update();
        mongoTemplate.upsert(new Query(Criteria.where("name").is("测试0")),
                update.addToSet("cars",car),User.class); //删除符合条件的记录，没有符合条件的则添加
        String b="10";
        Double c=10d;
        BigDecimal a=new  BigDecimal(c);

       /* mongoTemplate.updateMulti(new Query(Criteria.where("age").gte(0)),
                Update.update("name","修改后的").set("title","新增"),User.class);*/

        //查询并删除
       /* System.out.println(mongoTemplate.findAndModify(new Query(Criteria.where("age").is(0)),
                new Update().set("name","修改后的"),User.class));*/

    }


    public void select(){

        //查询符合条件的第一个
        /* System.out.println(mongoTemplate.findOne(new Query(Criteria.where("age").gte(0)),User.class));*/

        //查询user集合所有数据
        System.out.println(mongoTemplate.findAll(User.class));
        List<User> all = mongoTemplate.findAll(User.class);


        for (User use: all) {
            System.out.println(use.getName());
            System.out.println(use.getAge());
            List<Car> cars=use.getCars();
            for (Car car: cars ) {
                System.out.println(car.getId());
                System.out.println(car.getName());
            }

        }


        //查询age >= 0 的所有数据
        /* System.out.println(mongoTemplate.find(new Query(Criteria.where("age").gte(0)),User.class));*/
    }
}
