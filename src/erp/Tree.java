
package erp;
/* 
Класс наполняет структуру дерева 
и возвращает наполненую DefaultTreeModel
*/
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
  
  // Универсальный загрузчик данных из БД в Дерево
   public DefaultTreeModel loadToTree() { 
       DefaultMutableTreeNode xroot = new DefaultMutableTreeNode("/ Корень");
       
       
       
       DefaultTreeModel dtm = new DefaultTreeModel(xroot, true);
       return dtm;
   }
   }
