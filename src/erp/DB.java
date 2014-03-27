
package erp;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DB {
    // конструктор активизирует подключение
    DB(){   
    DB.connect();
    }
    
    
    public static Connection con = null; // хранит соединение с БД
    
    public static void connect(){ 
                    
         try {
                  
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            String url = "jdbc:sqlite:c:/DB/ERP_DB.db";
            con = DriverManager.getConnection(url);
                        
            }  catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {        }
                
        
    }
    
    public static void closeConnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) {  }
    }
   
    public void testPrint() {
    ResultSet res = null; // получает результаты SQL запросов
    Statement stment = null; // хранит и выполняет SQL запросы
    String sql = "SELECT * FROM Detail"; // подготовка SQL запроса
   try {            
            stment = con.createStatement();
            res = stment.executeQuery(sql); // выполнение SQL запроса
            
            while (res.next())  {
            System.out.println(res.getString("name_object") + " - " + res.getString("decimal_number"));
            }      
       } catch (SQLException e) {   } 
            
            
    }
   
    // выбрать все для загрузки дерева
   public ArrayList<ArrayList<String>> selectAll() {
   ArrayList<ArrayList<String>> massiv = new ArrayList<>(); // хранит уровни дерева
   ArrayList<String> massiv_id = new ArrayList<>(); // хранит ID деталей/сборок
   
   
   
   ArrayList<String> massiv1 = new ArrayList<>(); 
   ArrayList<String> massiv2 = new ArrayList<>();
   ArrayList<String> massiv3 = new ArrayList<>(); 
   massiv1.add("1 тест");
   massiv1.add("1 проверка");
   massiv1.add("1 деталь");
   massiv1.add("1 массив");
   massiv1.add("1 пункт");
   massiv2.add("2 элемент");
   massiv2.add("2 код");
   massiv2.add("2 джава");
   massiv3.add("3 клавиша");
   massiv3.add("3 комп");
   massiv3.add("3 мусор");
   massiv3.add("3 селект");
   
           
           
    ResultSet res = null; // получает результаты SQL запросов
    Statement stment = null; // хранит и выполняет SQL запросы
    String sql = "SELECT * FROM Detail"; // подготовка SQL запроса
   try {            
            stment = con.createStatement();
            res = stment.executeQuery(sql); // выполнение SQL запроса
            
            while (res.next())  {
            String text = res.getString("name_object") + " - " + res.getString("decimal_number");
            massiv_id.add(text);
            massiv.add(0, massiv_id);   // пока-что запишем только 0-уровень
        
          System.out.println(massiv.get(0).get(massiv_id.size()-1));  // выводим последний элемент массива
            }      
       } catch (SQLException e) {   } 
   
 massiv.add(1, massiv1);  // временная набивка уровней мусором
 massiv.add(2, massiv2);
 massiv.add(3, massiv_id);
 massiv.add(4, massiv3);
   
   return massiv;
   }
    

/*
    public void selectAllProduct(String sql_query){
    // выборка всех изделий
    }
    public void selectAssembly(String sql_query){
    // выборка данных
    }
      public void update(String sql_query){
    // запись данных
    }  
*/
    
  
 }