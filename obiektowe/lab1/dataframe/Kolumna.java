import java.util.*;

public class Kolumna {
    String nazwa;
    String typ;
    ArrayList dane_w_kolumnie;

    public Kolumna(String nazwa, String typ){
        this.nazwa=nazwa;
        this.typ=typ;
        dane_w_kolumnie =  new ArrayList();
    }

    public Kolumna(Kolumna wzor_kopii){
        nazwa = new String(wzor_kopii.nazwa);
        typ = new String(wzor_kopii.typ);
        dane_w_kolumnie = new ArrayList<>(wzor_kopii.dane_w_kolumnie);
    }
    
//___________________________________________________________________________________________

 public void Dodaj(Object dana){
	        if(SprawdzTyp(dana)) {
	            dane_w_kolumnie.add(dana);
	        }
	        else {
	            throw new IllegalArgumentException("Argument innego typu niz kolumna");
	        }
    }
 
 private boolean SprawdzTyp(Object dana) {
     switch (typ){
     	 case "bool":
         case "boolean":
         case "Boolean":
             return dana instanceof Boolean;
             
         case "byte":
         case "Byte":
             return dana instanceof Byte;
             
         case "char":
         case "character":
         case "Character":
             return dana instanceof Character; 
             
         case "double":
         case "Double":
             return dana instanceof Double;  
             
         case "float":
         case "Float":
             return dana instanceof Float; 
             
         case "int":
         case "integer":
         case "Integer":
             return dana instanceof Integer;
             
         case "long":
         case "Long":
             return dana instanceof Long;
             
         case "string":
         case "String":
             return dana instanceof String;
             
         default:
            System.out.println("nieznany typ danych");
            return false;
     }
 }
}