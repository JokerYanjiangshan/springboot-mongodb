package springbootmongodb.mongo.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;
import springbootmongodb.mongo.entity.BlueGlass;
import springbootmongodb.mongo.entity.PageModel;
import springbootmongodb.mongo.entity.SpringPageable;

import java.util.List;

@Component
public class BlueGlassDao {

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 保存user
     *
     * @param user user
     */
    public void saveUser(BlueGlass user) {
        mongoTemplate.save(user);
    }

    /**
     * 根据用户名查询user
     *
     * @param userName userName
     * @return user
     */
    public BlueGlass findUserByUserName(String userName) {
        Query query = new Query(Criteria.where("blueGlassName").is(userName));
        return mongoTemplate.findOne(query, BlueGlass.class);
    }

    /**
     * 更新user
     *
     * @param user user
     */
    public void updateUser(BlueGlass user) {
        Query query = new Query(Criteria.where("id").is(user.getId()));
        Update update = new Update().set("blueGlassName", user.getBlueGlassName()).set("password", user.getPassword());
        mongoTemplate.updateFirst(query, update, BlueGlass.class);
    }

    /**
     * 删除user
     *
     * @param id id
     */
    public void deleteUserById(Long id) {
        Query query = new Query(Criteria.where("id").is(id));
        mongoTemplate.remove(query, BlueGlass.class);
    }

    /**
     * 分页查询
     * @param pageNum 页数
     * @param pageSize 每页数量
     * @param sortField 排序字段
     * @return pages
     */
    public Page<BlueGlass> findUserPagination(Integer pageNum, Integer pageSize, String sortField) {
        SpringPageable pageable = new SpringPageable();
        PageModel pm = new PageModel();
        Query query = new Query();
        Sort sort = new Sort(Sort.Direction.DESC, sortField);
        pm.setPagenumber(pageNum);
        pm.setPagesize(pageSize);
        pm.setSort(sort);
        pageable.setPage(pm);
        Long count = mongoTemplate.count(query, BlueGlass.class);
        List<BlueGlass> list = mongoTemplate.find(query.with(pageable), BlueGlass.class);
        return new PageImpl<>(list, pageable, count);
    }
}
