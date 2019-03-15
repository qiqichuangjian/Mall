package servlt;

import dao.ItemsDao;
import entity.Items;
import util.PageBean;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Acthor:孙琪; date:2019/3/14;
 */
@WebServlet(name = "ItemsServlet",urlPatterns = "/ItemsServlet")
public class ItemsServlet extends HttpServlet {
    private ItemsDao dao = new ItemsDao();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String method = req.getParameter("method");
        if("add".equals(method)){
            add(req,resp);
        }else if("delete".equals(method)){
            delete(req,resp);
        }else if("query".equals(method)){
            query(req,resp);
        }else if("update".equals(method)){
            update(req,resp);
        }else if("queryOne".equals(method)){
            queryOne(req,resp);
        }else if("queryPage".equals(method)){
            queryPage(req,resp);
        }
    }

    private void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //String id = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String price = req.getParameter("price");
        String number = req.getParameter("number");
        String picture = req.getParameter("picture");
        Map<String,Object> map = new HashMap<>();
        //map.put("id",id);
        map.put("name",name);
        map.put("city",city);
        map.put("price",price);
        map.put("number",number);
        map.put("picture",picture);
        int i = dao.add(map);
        resp.sendRedirect(req.getContextPath()+"/add.jsp");
    }


    private void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        dao.delete(Integer.parseInt(id));
        //转发
        resp.sendRedirect(req.getContextPath()+"/ItemsServlet?method=queryPage");
    }


    private void query(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, Object>> list = dao.findAll();
        req.setAttribute("list",list);
        req.getRequestDispatcher("/query.jsp").forward(req,resp);
    }

    private void queryOne(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Map<String, Object> map = dao.queryOne(Integer.parseInt(id));
        req.setAttribute("map",map);
        req.getRequestDispatcher("/queryOne.jsp").forward(req,resp);

    }

    private void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String id = req.getParameter("id");
        String name = req.getParameter("name");
        String city = req.getParameter("city");
        String price = req.getParameter("price");
        String number = req.getParameter("number");
        String picture = req.getParameter("picture");
        Map<String,Object> map = new HashMap<>();
        map.put("id",id);
        map.put("name",name);
        map.put("city",city);
        map.put("price",price);
        map.put("number",number);
        map.put("picture",picture);
        int i = dao.update(map);
        resp.sendRedirect(req.getContextPath()+"/ItemsServlet?method=queryPage");
    }
    private void queryPage(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int pageIndex = 1;//第一页开始
        int pageCount = 10;//显示10条
        if(req.getParameter("pageIndex")!=null){
            String strPageIndex= req.getParameter("pageIndex");
            pageIndex = Integer.parseInt(strPageIndex);
        }
        PageBean<Items> pageBean = dao.queryPageBean(pageIndex, pageCount);
        req.setAttribute("pageBean",pageBean);
        req.getRequestDispatcher("query.jsp").forward(req,resp);
    }
}
