/*
Сборка или деталь иерархического дерева изделия
Содержит в себе всю информацию об элементе (id, ДЦ номер, название и т.д.) получаемую из БД
 */

package core;

public class Element {
    int id;
    String name;
    String decimal;
    int type_obj;
    int condition;
    String comment;
    int material;
    Element parent_object; // родительский объект если есть
    boolean is_copy; // флаг - это копия повторяющегося элемента (да/нет)
    
     // конструктор для оригинального элемента
    public Element(int id, String name, String decimal, int type_obj){
        this.id = id;
        this.name = name;
        this.decimal = decimal;
        this.type_obj = type_obj;
        is_copy = false;
    }
    
    // конструктор для копии элемента
        public Element(int id, String name, String decimal, int type_obj, Element parent_object){
        this.id = id;
        this.name = name;
        this.decimal = decimal;
        this.type_obj = type_obj;
        this.parent_object = parent_object;
        is_copy = true;
        
    }
    
public int getID(){ return id;}
public String getName(){ return name;}
public String getDecimal(){ return decimal;}
public int getTypeObj(){ return type_obj;}
public Element getParentObj(){ return parent_object;}
public boolean thisCopy(){ return is_copy;}


}
