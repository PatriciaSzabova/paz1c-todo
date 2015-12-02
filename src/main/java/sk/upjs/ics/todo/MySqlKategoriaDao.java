
package sk.upjs.ics.todo;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;

public class MySqlKategoriaDao implements KategoriaDao{
    
    private JdbcTemplate jdbcTemplate;

    public MySqlKategoriaDao() {
        
        MysqlDataSource dataSource = new MysqlDataSource();
        dataSource.setUrl("jdbc:mysql://localhost/todo");
        dataSource.setUser("todo");
        dataSource.setPassword("todo");
        
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    
    

    @Override
    public List<Kategoria> dajVsetky() {
        String sql ="select * from kategoria left join uloha on\n" +
                    "kategoria.id = uloha.kategoria_id \n" +
                    "order by kategoria.id;";
       KategoriaRowCallbackHandler handler = new KategoriaRowCallbackHandler();
       jdbcTemplate.query(sql, handler);
       
       return handler.getKategorie();
    }
    
}
