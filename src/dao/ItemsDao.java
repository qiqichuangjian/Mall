package dao;

import entity.Items;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import util.DbUtil;
import util.PageBean;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Acthor:孙琪; date:2019/3/14;
 */
public class ItemsDao {
    private QueryRunner qr = new QueryRunner(DbUtil.getDataSource());

    public List<Map<String, Object>> findAll() {
        List<Map<String, Object>> list = new ArrayList<>();
        String sql = "select * from items";

        try {
            list = qr.query(sql, new MapListHandler());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Map<String, Object> queryOne(int id) {
        String sql = "select * from items where id=?";
        try {
            Map<String, Object> query = qr.query(sql, new MapHandler(), id);
            return query;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int add(Map<String, Object> item) {
        String sql = "INSERT INTO db_mall.items (name, city, price, number, picture) VALUES (?,?,?,?,?)";
        try {
            int i = qr.update(sql, item.get("name"), item.get("city"), item.get("price"), item.get("number"), item.get("picture"));
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int update(Map<String, Object> item) {
        String sql = "UPDATE db_mall.items SET name = ?, city = ?, price = ?, number = ?, picture = ? WHERE id = ?";
        try {
            int i = qr.update(sql, item.get("name"), item.get("city"), item.get("price"), item.get("number"), item.get("picture"),item.get("id"));
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int delete(int id) {
        String sql = "delete from items  WHERE id = ?";
        try {
            int i = qr.update(sql, id);
            return i;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

   //分页查询
    public PageBean<Items> queryPageBean(int pageIndex,int pageCount){
        PageBean<Items> pageBean =  new PageBean<>();
        pageBean.setCount(getCount());
        pageBean.setPageIndex(pageIndex);//(int pageIndex,int pageCount)就是limit ?,?
        pageBean.setPageCount(pageCount);
        int index = pageBean.getIndex();
        String sql = "select * from items limit ?,?";
        List<Items> itemsList = null;
        try {
            itemsList = qr.query(sql, new BeanListHandler<>(Items.class), index, pageCount);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        pageBean.setList(itemsList);
        return pageBean;
    }

    public int getCount(){
        int count = 0;
        //select count(*) 别名 from items
        String sql ="select count(*)  count from items ";
        try {
            Number query = qr.query(sql, new ScalarHandler<>());
            count = query.intValue();

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return count;
    }
}
