
package core;
/* 
Класс наполняет структуру дерева 
и возвращает наполненую DefaultTreeModel
*/

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.*;

public class Tree {
 
 HashMap<Integer, DefaultMutableTreeNode> obj_list = new HashMap<>();
 HashMap<Integer, Integer> check_list = new HashMap<>();
  // загружает данные из массива (от БД) в модель дерева
   public DefaultTreeModel loadToModelTree(HashMap<Integer, ArrayList<Integer>> consist_map, HashMap<Integer, Element> elements_map) { 
       
    
     // создаем корневой каталог
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Каталог");   
    DefaultTreeModel dtm = new DefaultTreeModel(root, true);
   
  
  // заполняем список obj_list объектами node в соответсвии иерархии consist_map
  for(int id : consist_map.keySet()){
  DefaultMutableTreeNode node = new DefaultMutableTreeNode(id);
  obj_list.put(id, node);
  }
  
   for(int pid : consist_map.keySet()){
   DefaultMutableTreeNode newTree = fill(pid, consist_map.get(pid));
   if(pid == 1) {root.add(newTree);}
   }
  
  return dtm;
   }
   
   public DefaultMutableTreeNode fill(int p_id, ArrayList<Integer> consist_list){
    DefaultMutableTreeNode node = obj_list.get(p_id);

    for(int sid : consist_list) {

      node.add(obj_list.get(sid));
      obj_list.put(p_id, node);
     
 
    }
    return node;
   }
   
 // проверка ветки, если уже добавлялась, клонировать
  public DefaultMutableTreeNode checkNode(int id){
     
      for(Integer key : check_list.keySet()){
     if(key == id){ 
      
                 }
      }
  return new DefaultMutableTreeNode();
  }
  

 
}