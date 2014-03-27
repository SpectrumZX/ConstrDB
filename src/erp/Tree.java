
package erp;
/* 
Класс наполняет структуру дерева 
и возвращает наполненую DefaultTreeModel
*/
import java.util.List;
import java.util.ArrayList;
import javax.swing.tree.*;

public class Tree {
  public DefaultTreeModel show(){

  String[][] xtree = new String[10][10];
  xtree[0][0] = "Коктейль";
  xtree[0][1] = "Сок";
  xtree[0][2] = "Морс";
  
  xtree[1][0] = "Яблоки";
  xtree[1][1] = "Груши";
  
  xtree[2][0] = "Апельсины";
  xtree[2][1] = "Мандарины";
  xtree[2][2] = "Лимоны";
  
  DefaultMutableTreeNode xroot = new DefaultMutableTreeNode("/ Корень");
  
  for (int a=0; a<9; a++) {
      for (int b=0; b<9; b++) {
   
  if (xtree[a][b] != null) xroot.add(new DefaultMutableTreeNode(xtree[a][b], false));
      }
  }
  
    // FALSE - (лист) конечный элемент
    // TRUE - (ветка) создает каталог, даже если пустой
 
       DefaultTreeModel dtm = new DefaultTreeModel(xroot, true);
       
       return dtm;
  }
  
  // загружает данные из массива (от БД) в модель дерева
   public DefaultTreeModel loadToModelTree(ArrayList<ArrayList<String>> massiv) { 
    
     // создаем корневой каталог
    DefaultMutableTreeNode root = new DefaultMutableTreeNode("Каталог");   
    DefaultTreeModel dtm = new DefaultTreeModel(root, true);   
    
    int L=0; // текущий уровень глубины каталога
    for (String name : massiv.get(0)) {     // выводим весь 0-уровень
          DefaultMutableTreeNode newItem = new DefaultMutableTreeNode(name, true);  // ЗДЕСЬ ДОБАВИТЬ ПРОВЕРКУ, ЕСЛИ ДЕТАЛЬ ТО FALSE, иначе TRUE
          root.add(newItem);  
     L++ ; 
          for (String item : massiv.get(L)) {
          newItem.add(new DefaultMutableTreeNode(item, true));   
        
          
          }
      }
        
  
     
    
       
       
       

       
       return dtm;

   }
}