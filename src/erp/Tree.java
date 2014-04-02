
package erp;
/* 
Класс наполняет структуру дерева 
и возвращает наполненую DefaultTreeModel
*/

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.*;

public class Tree {
 
 ArrayList<Integer> list_level_next = new ArrayList<>();
 HashMap<Integer, DefaultMutableTreeNode> obj_list = new HashMap<>();
  // загружает данные из массива (от БД) в модель дерева
   public DefaultTreeModel loadToModelTree(HashMap<Integer, ArrayList<Integer>> consist_map, HashMap<Integer, String> name_map, HashMap<Integer, String> decimal_map) { 
       
       
     // создаем корневой каталог
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Каталог");   
    DefaultTreeModel dtm = new DefaultTreeModel(root, true);
   
  
  // заполняем список id - object node
  for(int id : consist_map.keySet()){
  DefaultMutableTreeNode node = new DefaultMutableTreeNode(id+")  "+name_map.get(id)+" - "+decimal_map.get(id));
  obj_list.put(id, node);
  }
  
   for(int pid : consist_map.keySet()){
   DefaultMutableTreeNode newTree = fill(pid, consist_map.get(pid));
   if(pid == 1) {root.add(newTree);}
   }
  

 
  return dtm;
   }
   
   public DefaultMutableTreeNode fill(int p_id, ArrayList<Integer> consist_list){
   // System.out.println("PID: "+p_id); 
    DefaultMutableTreeNode node = obj_list.get(p_id);
    for(int sid : consist_list) {
      node.add(obj_list.get(sid));
      obj_list.put(p_id, node);
     
    // System.out.println("       "+sid);
    }
    return node;
   }
   
 // проверка ветки, если уже добавлялась, клонировать
  public void checkNode(){
  
  }
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
//   list_level = consist_map.get(1); 
//   for(int pid : consist_map.keySet()) {
//     
//  DefaultMutableTreeNode node = new DefaultMutableTreeNode(pid);
//      
//  System.out.println("PID "+pid);
//         
//       sid_list = consist_map.get(pid);
//      
//
//
// for (int x : list_level){  if (x==pid){ 
//        
//          for(int sid : sid_list) {          
//         node.add(new DefaultMutableTreeNode(sid)); 
//         System.out.println("  - "+sid); 
//         list_level.add(sid);
//      } 
//   
//    System.out.println("was added to root"); 
//      root.add(node);
//     }   }   }
   
   
   
   
       
 
 
}