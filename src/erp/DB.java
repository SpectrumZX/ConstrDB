
package erp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DB {
    public static void connect(){  // статичный метод чтоб не было нескольких соединений
    Connection con = null; // хранит соединение с БД
    Statement stmt = null; // хранит и выполняет SQL-запросы
    ResultSet res = null;  // получает результат SQL-запросов
    
    try {
    // регистрация драйвера SQLite
        Driver d = (Driver) Class.forName("org.sqlite.JDBC").newInstance();    
    // Создание подключения к БД
        String url = "jdbc:sqlite:c:/DB/......../ERP.db"; // ОПРЕДЕЛИТЬ ПУТЬ!!!!
        con = DriverManager.getConnection(url);
    // подготовка SQL-запроса
        String sql = "SELECT * FROM my_table";
        stmt = con.createStatement();
    // выполнение SQL-запроса
        res = stmt.executeQuery(sql);   // executeUpdate для удаления или записи
    // обработка результата
        while (res.next()) {
        System.out.println(res.getString("поле таблицы"));
        
        // попробовать res.getArray для списка входящих деталей
        }
        
    } catch(Exception e){}
    finally {
    try {
        if (res != null) res.close();
        if (stmt != null) stmt.close();
        if (con != null) con.close();
    } catch(Exception ex){}
    }
    
    }
    
    public void selectAllProduct(String sql_query){
    // выборка всех изделий
    }
    public void selectAssembly(String sql_query){
    // выборка данных
    }
      public void update(String sql_query){
    // запись данных
    }  
    
    
}
