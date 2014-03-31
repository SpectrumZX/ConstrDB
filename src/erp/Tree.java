
package erp;
/* 
Класс наполняет структуру дерева 
и возвращает наполненую DefaultTreeModel
*/

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.tree.*;

public class Tree {
 
  int I=0;
  int K=0;
  // загружает данные из массива (от БД) в модель дерева
   public DefaultTreeModel loadToModelTree(HashMap<Integer, ArrayList<Integer>> consist_map, HashMap<Integer, String> name_map, HashMap<Integer, String> decimal_map) { 
       
       

     // создаем корневой каталог
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Каталог");   
    DefaultTreeModel dtm = new DefaultTreeModel(root, true);   
  
    ArrayList<Integer> list_level1 = consist_map.get(1);
    ArrayList<Integer> list_level_next = null;
   // System.out.println(consist_map.get(3));
       
   for(int pid : consist_map.keySet()) {
    
  DefaultMutableTreeNode temp = new DefaultMutableTreeNode(pid);
      
       System.out.println("PID "+pid);
   
       
         
       
       
       ArrayList<Integer> sid_list = consist_map.get(pid);
   
      for(int sid : sid_list) {
         temp.add(new DefaultMutableTreeNode(sid)); 
          
        System.out.println("  - "+sid); 
      } 
   
 for (int x : list_level1){  if (x==pid){ 
    System.out.println("-------was added to root"); 
      root.add(temp);
      }
     // else {list_level_next.add(pid);
    //  System.out.println(pid + "go to next level");
   //   }   
      }
    
 }
 //   System.out.println(decimal_map.get(1));
//    while (K<3) {
//        
//       DefaultMutableTreeNode AAA;
//       AAA = cycleAdd("БУП-М"+Integer.toString(K), massiv, K);
//       root.add(AAA); // добавляет ветку "koren" с содержимым детали по id из massiv 
//       K++;
//       while (I<3) {
//       DefaultMutableTreeNode BBB = AAA.getNextNode();
//       
//       BBB.add(cycleAdd("подсборка"+Integer.toString(I), massiv, I));
// I++;
//       }
//       
//    }
       return dtm;
   }
   
   public DefaultMutableTreeNode cycleAdd(String root_item_name, ArrayList<ArrayList<String>> mass, int id) {
   
       DefaultMutableTreeNode newItem = new DefaultMutableTreeNode(root_item_name, true);
   for (String txt : mass.get(id)) {
       newItem.add(new DefaultMutableTreeNode(txt, true)); 
   }
 
   return newItem;
   }
}