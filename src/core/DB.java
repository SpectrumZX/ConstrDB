
package core;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;


public class DB {
    // конструктор активизирует подключение
   public DB(){   
    DB.connect();
   
    }
    
  
    public static Connection con = null; // хранит соединение с БД
    public static Connection con2 = null;
    public static void connect(){ 
                    
         try {
                  
            Driver driver = (Driver) Class.forName("org.sqlite.JDBC").newInstance();
            String url = "jdbc:sqlite:c:/DB/ERP_DB.db";
            con = DriverManager.getConnection(url);
            con2 = DriverManager.getConnection(url);
                        
            }  catch (SQLException | ClassNotFoundException | InstantiationException | IllegalAccessException e) {  System.out.println("error from driver");      }
                
        
    }
    
    public static void closeConnect() {
        try {
            if (con != null) {
                con.close();
            }
        } catch (SQLException ex) { System.out.println("error from con"); }
        
                try {
            if (con2 != null) {
                con2.close();
            }
        } catch (SQLException ex) { System.out.println("error from con2"); }
    }
   
   
  public  HashMap<Integer, String> name_map = new HashMap<>();
  public  HashMap<Integer, String> decimal_map = new HashMap<>();
  public  HashMap<Integer, Integer> type_obj_map = new HashMap<>();
  public  HashMap<Integer, Integer> condition_map = new HashMap<>();
  public  HashMap<Integer, String> comment_map = new HashMap<>();
  public  HashMap<Integer, Integer> material_map = new HashMap<>(); 
  
  // список включений
 public ArrayList<Integer> consist_id = new ArrayList<>();
 public ArrayList<Integer> temp_id = new ArrayList<>();
 public ArrayList<Integer> temp_val = new ArrayList<>();
 // public HashMap<Integer, Integer> tempory_full_consist_id = new HashMap<>(); // временная таблица иерархий (все подряд)
 public HashMap<Integer, ArrayList<Integer>> consist_map = new HashMap<>(); // HashMap в котором Key - id, value - список входящих id  
 public HashMap<Integer, Element> elements_map = new HashMap<>();

    // выбрать все для загрузки дерева
   public void selectAll() {
                
    ResultSet res = null; // получает результаты SQL запросов
    ResultSet res_struct = null;
    Statement stment = null; // хранит и выполняет SQL запросы
    Statement stment_struct = null;
    String sql = "SELECT * FROM Detail"; // подготовка SQL запроса
    String sql_struct = "SELECT * FROM TreeStructure";
    
   try {            
            stment = con.createStatement();
            stment_struct = con.createStatement();
            res = stment.executeQuery(sql); // выполнение SQL запроса
            res_struct = stment_struct.executeQuery(sql_struct); // выполнение SQL запроса по структуре дерева
            
             // заполняем временный список иерархии (ве подряд)
            while(res_struct.next()) {
            temp_id.add(res_struct.getInt("id"));
            temp_val.add(res_struct.getInt("consist_id")); 
        
                    }           
            
           while (res.next())  {
            int id = res.getInt("id");

            
int count;  // как достать индекс по значению ячейки в temp_id??  пока временный костыль со счетчиком
count = 0;
            // заполняем иерархию
           if(res.getInt("type_object") == 1 || res.getInt("type_object") == 3){ // если тип равен 1-сборка или 3-заг.сборки, заполняем иерархию
                for (int key_id : temp_id)  { 
                    
                    int value_id;
                   
                     if (key_id == id) {  
                         value_id = temp_val.get(count);
                        consist_id.add(value_id);  // заполняем ArrayList для конкретного ID 
                        
                     }
                   count++;   
                 }
              
            }        
            consist_map.put(id, new ArrayList<Integer>(consist_id)); // привязываем список id к сборке
            consist_id.clear();
            
            name_map.put(id, res.getString("name_object"));
            decimal_map.put(id, res.getString("decimal_number"));
            type_obj_map.put(id, res.getInt("type_object"));
            condition_map.put(id, res.getInt("condition"));
            comment_map.put(id, res.getString("comment"));
            material_map.put(id, res.getInt("material"));
            
            elements_map.put(id, new Element(id, res.getString("name_object"), res.getString("decimal_number"), res.getInt("type_object")));
            
          }      
       } catch (SQLException e) {System.out.println("error from selectAll"); } 
   

   }
  
}
  
