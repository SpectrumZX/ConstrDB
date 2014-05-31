
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
 HashMap<Integer, DefaultMutableTreeNode> check_list = new HashMap<>(); // проверочный список повторяющихся элементов. key хранит id оригинального элемента, value хранит список id клонов
 HashMap<Integer, ArrayList<Integer>> consist_map; // для доступа локальных методов создаем коллекцию
  // загружает данные из массива (от БД) в модель дерева
   public DefaultTreeModel loadToModelTree(HashMap<Integer, ArrayList<Integer>> consist_map, HashMap<Integer, Element> elements_map) { 
    check_list.clear();
    this.consist_map = consist_map;
    
     // создаем корневой каталог
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Каталог");   
    DefaultTreeModel dtm = new DefaultTreeModel(root, true);
   
 
  // заполняем список obj_list объектами node в соответсвии иерархии consist_map
  for(int id : consist_map.keySet()){
  DefaultMutableTreeNode node = new DefaultMutableTreeNode("("+id+") "+elements_map.get(id).getName()+" "+elements_map.get(id).getDecimal());
  obj_list.put(id, node);
  }
 
   for(int pid : consist_map.keySet()){
   DefaultMutableTreeNode newTree = fill(pid, consist_map.get(pid));
   if(pid == 1) {root.add(newTree);}  // если id = 1 то это корневой элемент, добавляем его в корень
   }



  return dtm;
   }
   // наполняет элемент дерева потомками
            public DefaultMutableTreeNode fill(int p_id, ArrayList<Integer> consist_list){
                        
                DefaultMutableTreeNode node = obj_list.get(p_id);
               
                for(int sid : consist_list) {
                    
               node.add(checkNode(sid));
                 obj_list.put(p_id, node);
                }
            return node;
          
            }
            
 // проверка ветки, если уже добавлялась, клонировать
  public DefaultMutableTreeNode checkNode(int id){
if(check_list.containsKey(id)){ 
  DefaultMutableTreeNode clone_node = (DefaultMutableTreeNode) check_list.get(id).clone();

  return clone_node;
}
else {
  DefaultMutableTreeNode new_node = obj_list.get(id);
  check_list.put(id, new_node); // добавляем id элемента и сам объект node в проверочный список повторений 

  return new_node;
}  
}
  

 
}