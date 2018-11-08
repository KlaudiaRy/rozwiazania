import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SparseDataFrame extends DataFrame {
	/* public SparseDataFrame(String[] lista_nazw, String[] lista_typow, Object hide) {
	        super(lista_nazw, lista_typow);
	        this.outer = new ArrayList<ArrayList>();
			this.ilosc_wierszy=0;
			for (int i=0; i< lista_nazw.length; i++) {
				ArrayList inner = new ArrayList();
				this.outer.add(inner);
			}
	 }
	 public SparseDataFrame(DataFrame D, Object hide) {
		 	super(D.lista_nazw, D.lista_typow);

		 	 String [] lista_kolumn = D.lista_nazw;
		     String [] lista_typow = D.lista_typow;
		     
		     for (int i = 0; i < D.lista_typow.length; i++) {
		    	 
		    	   ArrayList inner1 = new ArrayList();
		    	   inner1 = SparseKol(D.outer.get(i),hide,i);
				   this.outer.add(inner1);
	            }
	    }
	*/ 
	Value hide;
	

	
	
	
	public SparseDataFrame(String[] lista_nazw, Class[] lista_typow, Object hide) {
        super(lista_nazw, lista_typow);
        this.outer = new ArrayList<ArrayList<Value>>();
		this.ilosc_wierszy=0;
		for (int i=0; i< lista_nazw.length; i++) {
			ArrayList inner = new ArrayList();
			this.outer.add(inner);
		}
	 }
	 public SparseDataFrame(DataFrame D, Value hide) {
		 	super(D.lista_nazw, D.lista_typow);
	
		 	 String [] lista_kolumn = D.lista_nazw;
		     Class [] lista_typow = D.lista_typow;
		     
		     for (int i = 0; i < D.lista_typow.length; i++) {
		    	 
		    	   ArrayList inner1 = new ArrayList();
		    	   inner1 = SparseKol(D.outer.get(i),hide,i);
				   this.outer.add(inner1);
	            }
	    }
	
	 ArrayList kolumna1;
	 
	 	public ArrayList SparseKol(ArrayList<? extends Value> kol, Value hide,int i) {
	 		ArrayList kolumna1 = new ArrayList();
	 		for (int k = 0; k< 4;k++) {	
			    if (kol.get(k)!= hide) { 
			    	
			    	COOValue c = new COOValue(kol.get(k),i);
					kolumna1.add(c);
				}
			}
			return kolumna1;
	 	}
	 
	 
	/* 
	 public ArrayList SparseKol(String nazwa, Object hide) {
		 
		 for (int i = 0; i< lista_nazw.length; i++) {
			 if (lista_nazw[i]==nazwa) {
				System.out.println(ilosc_wierszy);
				 for (int j = 0; j< 3;j++) {					
					 if (outer.get(i).get(j)!= hide) { 
						  System.out.println("dzia³am");
						 kolumna.add(new COOValue(outer.get(i).get(j),i));
					 }
				 }
				 return kolumna;
			 }
		 }
		 throw new IllegalArgumentException("Brak kolumny o podanej nazwie");
		 
	 }
	 */
	        
	    

	 
	   /* final class COOValue {
	        private final Object wartosc;
	        private final int indeks;

	         COOValue(Object wartosc, int indeks) {
	            this.wartosc = wartosc;
	            this.indeks = indeks;
	        }

	        public int zwrocIndeks() {
	            return indeks;
	        }

	        public Object zwrocWartosc(){
	            return wartosc;
	        }
	    }
	 */
	    
	    
	    
	    public DataFrame toDense(SparseDataFrame SPD, Object hide){
	        String []nazwy = new String[SPD.lista_nazw.length];
	        Class [] typy = new Class[SPD.lista_typow.length];

	        nazwy = SPD.lista_nazw;
	        typy = SPD.lista_typow;

	        DataFrame df =  new DataFrame(nazwy,typy);

	        for(int i=0;i<nazwy.length;i++) {
	        	for (int j =0; j< size();j++) {
	//        		if(SPD.outer.get(i).get(j).zwrocIndeks == j ){
	//        			df.dodaj(nazwy[i],SPD.outer.get(i).get(j).zwrocWartosc());
	//        		}else {
	 //       			df.dodaj(nazwy[i], hide);
	 //       		}
	        	}
	        }
	        return df;
	    }
	    
	    
	    
	    
	  /*  
	    public DataFrame toDense(SparseDataFrame SPD){
	        String []nazwy = new String[SPD.lista_nazw.length];
	        String [] typy = new String[SPD.lista_typow.length];

	        nazwy = SPD.lista_nazw;
	        typy = SPD.lista_typow;

	        DataFrame df =  new DataFrame(nazwy,typy);

	        for(int i=0;i<size();i++)
	        {
	        	for (int j =0; j< nazwy.length;j++) {
	            df.dodaj(nazwy[j], 0);
	        }
	        }
	        return df;
	    }
	    
	 */   
	    
	    
 /* 	    
	    public SparseDataFrame(String path,String[] typy_kolumn,Object hide) throws IOException {
	        this(path,typy_kolumn,null,hide); 

	    }

	  public SparseDataFrame(String path,String[] typy_kolumn, String[]nazwy_kolumn,Object hide) throws IOException { // header==false
	        super(lista_typow, nazwy_kolumn);
	        boolean header = nazwy_kolumn==null;
	        for (int i =0; i<typy_kolumn.length;i++){
	            if (!header){
	                lista_nazw=new SparseKol(nazwy_kolumn[i],TmpTypDanych.zwrocTypDanej(typy_kolumn[i]),hide[i]); 
	            }
	            else{
	                continue;
	            }
	            readingFromFileFunction(path,header);
	        }

	    } 
	    
	    
	 */   
	        
//____________________________
	   
		 @Override
		 public void dodaj(String nazwa_kolumny, Value dana) {
			 for(int j = 0; j< lista_nazw.length;j++) {
				 if (nazwa_kolumny == lista_nazw[j]) {
					 //if(sprawdzTyp(lista_typow[j],dana)) {
						 outer.get(j).add(dana); 
						 ilosc_wierszy = outer.get(1).size();
					// }
				 }
			 } 
		 }
		 
		 @Override
		 public int size() {
			 return ilosc_wierszy;
		 }
		 
		 @Override
		 public ArrayList get(String colname) {
			 for (int i = 0; i< lista_nazw.length; i++) {
				 if (lista_nazw[i]==colname) {
					 kolumna = outer.get(i);
					 return kolumna;
				 }
			 }
			 throw new IllegalArgumentException("Brak kolumny o podanej nazwie");
		 }
		 
		 
		 @Override
		  public SparseDataFrame get(String[] cols,boolean copy){ 
			  ArrayList<ArrayList<Value>> outer2;
			  outer2 = new ArrayList<ArrayList<Value>>();
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
		        
		        DataFrame df = new DataFrame(outer2);

		    
		      //  DataFrame df = new DataFrame(outer2);
		       // Class hide = new Class;
		        SparseDataFrame df3 = new SparseDataFrame(df,hide);
		        return df3;
		    }
		   	
		  	@Override
		  	public SparseDataFrame iloc(int from,int to) {
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

		        Class[] typy = new Class[lista_nazw.length];
		        String[] nazwy = new String [lista_nazw.length];

		        for (int i=0;i<lista_nazw.length;i++){
		            //typy[i]= new Class(lista_typow[i]);
		            nazwy[i]=new String (lista_nazw[i]);
		        }

		        DataFrame df2 = new DataFrame(nazwy,typy);
		        for (int j =0; j<lista_nazw.length;j++) {
		        	for (int i=from; i<to; i++){
		        		df2.outer.get(j).add((outer.get(j)).get(i));
		        	}
		        }
		        //Object hide = new Object();
		        SparseDataFrame spd1 = new SparseDataFrame(df2,hide);
		        
		        return spd1;
		    }
		  	
		  	
		  @Override
		  public SparseDataFrame iloc(int i){
		       return iloc(i,i);
		  }
		  	
		  	
		  
		 
		 
		 @Override
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
		 
	        
//_______________________________
		 
		 
		 @Override
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
			        Value[]wartosciZpliku =  new Value[lista_nazw.length];
			        while(((tmp=br.readLine() )!= null)){
			            strLine=tmp.split(",");
			            int m=0;
			            for(String wartosc:strLine){
			            	//wartosciZpliku[m] = wartosc;
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

	    
		 public SparseDataFrame(String path,Class[] typy_kolumn, Object hide) throws IOException{
		        this(path,typy_kolumn,null,hide);

		    }
		    public SparseDataFrame(String path,Class[] typy_kolumn, String[]nazwy_kolumn, Object hide) throws IOException { 
		    	super(nazwy_kolumn,typy_kolumn);
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