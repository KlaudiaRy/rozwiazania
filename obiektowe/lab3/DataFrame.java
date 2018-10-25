import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataFrame {
	int ilosc_wierszy;
    String[] lista_nazw;
    Class<? extends Value>[] lista_typow;
    //String[] lista_typow;
    ArrayList<ArrayList> outer;
    ArrayList kolumna;

	
    public void DataFrame(String[] lista_nazw, Class<? extends Value>[]lista_typow) {
		 outer = new ArrayList<ArrayList>();
		 ilosc_wierszy=0;
		   for (int i=0; i< lista_nazw.length; i++) {
			   ArrayList inner = new ArrayList();
			   outer.add(inner);
		   }
	    }
	 
	 public DataFrame(String[] lista_nazw, Class<? extends Value>[]lista_typow) {
	        this.lista_typow = lista_typow;
	        this.lista_nazw = lista_nazw;

	        DataFrame(lista_nazw, lista_typow);
	    }
    
    
    
	/* public void DataFrame(String[] lista_nazw, String[] lista_typow) {
		 outer = new ArrayList<ArrayList>();
		 ilosc_wierszy=0;
		   for (int i=0; i< lista_nazw.length; i++) {
			   ArrayList inner = new ArrayList();
			   outer.add(inner);
		   }
	    }
	 
	 public DataFrame(String[] lista_nazw, String[] lista_typow) {
	        this.lista_typow = lista_typow;
	        this.lista_nazw = lista_nazw;

	        DataFrame(lista_nazw, lista_typow);
	    }
	    */
	 
	 public void dodaj(String nazwa_kolumny, Object dana) {
		 for(int j = 0; j< lista_nazw.length;j++) {
			 if (nazwa_kolumny == lista_nazw[j]) {
				// if(sprawdzTyp(lista_typow[j],dana)) {
					 outer.get(j).add(dana); 
					 ilosc_wierszy = outer.get(1).size();
				// }
			 }
		 } 
	 }
	 
	 public int size() {
		 return ilosc_wierszy;
	 }
	 
	 public ArrayList get(String colname) {
		 for (int i = 0; i< lista_nazw.length; i++) {
			 if (lista_nazw[i]==colname) {
				 kolumna = outer.get(i);
				 return kolumna;
			 }
		 }
		 throw new IllegalArgumentException("Brak kolumny o podanej nazwie");
	 }
	 
	 
	  public DataFrame get(String[] cols,boolean copy){ 
		  ArrayList<ArrayList> outer2;
		  outer2 = new ArrayList<ArrayList>();
		  if (copy) {
			  for (int i=0; i< lista_nazw.length; i++) {
				  for (int j =0; j<cols.length;j++) {
					  if (lista_nazw[i]==cols[j]) {
						   ArrayList inner = new ArrayList();
						   inner = new ArrayList(outer.get(i));
						   outer2.add(inner);
					  }
				  }
			  }
		  }else {			  
			  for (int i=0; i< lista_nazw.length; i++) {
				  for (int j =0; j<cols.length;j++) {
					  if (lista_nazw[i]==cols[j]) {
						   ArrayList inner = new ArrayList();
						   inner = outer.get(i);
						   outer2.add(inner);
					  }
				  }
			  }
	        }
	        
	        if (copy) {
	        	System.out.println("wykonano kopiê g³êbok¹");
	        }else {
	        	System.out.println("wykonano kopiê p³ytk¹");
	        }
	        DataFrame df3 = new DataFrame(outer2);
	        return df3;
	    }
	  
	  	public DataFrame(ArrayList<ArrayList> kolumny) {
	        this.outer = kolumny;
	        ilosc_wierszy = outer.size();
	    }
	  	
	  	
	  	
	  	public DataFrame iloc(int from,int to) {
	        if (from<0 || from >= ilosc_wierszy || to<0 || to> ilosc_wierszy){
	            throw new IndexOutOfBoundsException("Nie ma wierszy w zadanym zakresie");
	        }
	        if(to<from){
	            int tmp;
	            tmp= to;
	            to = from;
	            from = tmp;
	            System.out.println("niepoprawna kolejnosc danych podczas podawania zakresu zostala automatycznie poprawiona");
	        }

	       // Class[] typy = new Class[];
	        Class[] typy = new Class [lista_nazw.length];
	        String[] nazwy = new String [lista_nazw.length];

	        for (int i=0;i<lista_nazw.length;i++){
	        	//typy[i]= new Class  lista_typow[i];
	        	//typy[i]= new String (lista_typow[i]);
	            nazwy[i]=new String (lista_nazw[i]);
	        }

	        DataFrame df2 = new DataFrame(nazwy,lista_typow);
	        for (int j =0; j<lista_nazw.length;j++) {
	        	for (int i=from-1; i<=to-1; i++){
	        		df2.outer.get(j).add((outer.get(j)).get(i));
	        	}
	        }
	           
	        return df2;
	    }
	  	
	  	
	  	
	  public DataFrame iloc(int i){
	       return iloc(i,i);
	  }
	  	
	  	
	  
	 
	 
	 
	 public boolean sprawdzTyp(String typ_danych, Object dana) {
		 switch (typ_danych) {
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
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void readingFromFileFunction(String path,boolean header) throws IOException {
		    FileInputStream fstream = new FileInputStream(path);
		    BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		    String []strLine;

		    
		    
		    if(header) 
		    {
		        strLine=br.readLine().split(",");
		        for (int i = 0; i < lista_nazw.length; i++) {
		            lista_nazw[i]=strLine[i];
		        }
		        
		        DataFrame df = new DataFrame(lista_nazw, lista_typow );
		        String tmp;
		        Object[]wartosciZpliku =  new Object[lista_nazw.length];
		        while(((tmp=br.readLine() )!= null)){
		            strLine=tmp.split(",");
		            int m=0;
		            for(String wartosc:strLine){
		            	wartosciZpliku[m] = wartosc;
		            	for (int l = 0; l< lista_nazw.length;l++) {
		            		df.dodaj(lista_nazw[l],wartosciZpliku[l]);
		            	m++;	
		            	}
		            }
		            //dodaj(lista_nazw[m],wartosciZpliku);
		        }
		    }
		    br.close();
		}

		    public DataFrame(String path,String[] typy_kolumn) throws IOException{
		        this(path,typy_kolumn,null);

		    }
		    public DataFrame(String path,String[] typy_kolumn, String[]nazwy_kolumn) throws IOException { 
		        boolean header = nazwy_kolumn==null;
		        for (int i =0; i<typy_kolumn.length;i++){
		            if (!header){
		                lista_nazw[i]=(nazwy_kolumn[i]);
		            }
		            else{
		                continue;
		            }
		         readingFromFileFunction(path,header);
		        }

		    } 
	 
	 
}
