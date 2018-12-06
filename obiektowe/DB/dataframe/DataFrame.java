package dataframe;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class DataFrame {
	int ilosc_wierszy;
    String[] lista_nazw;
    Class<? extends Value>[] lista_typow;
    ArrayList<ArrayList<Value>> outer;
    ArrayList<Value> kolumna;
    
    LinkedList<DataFrame> lista = null;

	
    public void DataFrame(String[] lista_nazw, Class<? extends Value>[]lista_typow) {
		 outer = new ArrayList<ArrayList<Value>>();
		 ilosc_wierszy=0;
		   for (int i=0; i< lista_nazw.length; i++) {
			   ArrayList<Value> inner = new ArrayList();
			   outer.add(inner);
		   }
	    }
	 
	 public DataFrame(String[] lista_nazw, Class<? extends Value>[]lista_typow) {
	        this.lista_typow = lista_typow;
	        this.lista_nazw = lista_nazw;

	        DataFrame(lista_nazw, lista_typow);
	    }
    
    public Class<? extends Value>[] zwroc_typy(){
    	return lista_typow;
    }
    
	 public class NameNotFound extends Exception{
		 NameNotFound(String message){
			 super(message);
		 }
	 }
	 
	 public Value wez(int i, int j) {
		return outer.get(i).get(j);
		 
	 }
	 
	 public void dodaj(String nazwa_kolumny, Value dana){
		 for(int j = 0; j< lista_nazw.length;j++) {
			 if (nazwa_kolumny == lista_nazw[j]) {
				// if(sprawdzTyp(lista_typow[j],dana)) {
					 outer.get(j).add(dana); 
					 ilosc_wierszy = outer.get(1).size();
				// }
			 }
		 } 
	 }
	 public void dodaj2(Value[] dane) {
		 for(int i =0;i< lista_nazw.length;i++) {
			 dodaj(lista_nazw[i],dane[i]);
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
	        	System.out.println("wykonano kopi� g��bok�");
	        }else {
	        	System.out.println("wykonano kopi� p�ytk�");
	        }
	        DataFrame df3 = new DataFrame(outer2);
	        return df3;
	    }
	  
	  	public DataFrame(ArrayList<ArrayList<Value>> kolumny) {
	        this.outer = kolumny;
	        ilosc_wierszy = outer.size();
	    }
	  	
	  	public ArrayList<ArrayList<Value>> ramka(){
	  		return outer;
	  	}
	  	
	  	public DataFrame iloc(int from,int to){
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
	  	
	  public class UnevenColumnLengths extends Exception{
		  public UnevenColumnLengths(String message){
			  super(message);
		  }
		  
	  }
	  public class InvalidActionForThisTypeOfData extends Exception{
		  public InvalidActionForThisTypeOfData(String message){
			  super(message);
			  //message = "Operacja nie moze zostac wykonana dla podanych danych";
			  
		  }
		  public InvalidActionForThisTypeOfData(Throwable cause){
			  super(cause);
		  }
		  public InvalidActionForThisTypeOfData(String message,Throwable cause){
			  super(message,cause);
		  }
	  }
	  
	 
	  public Value[] wiersz(int x){
		  Value[] v = new Value[lista_nazw.length];
		  for (int i = 0; i <lista_nazw.length;i++) {
			  for (int j = 0; j< this.size();j++) {
				  if (j==x) {
					  if ((v[i]==null) || (this.outer.get(i).get(j)==null)){
						 // throw new UnevenColumnLengths("kolumny są różnej długości");
					  }
					  v[i]=(this.outer.get(i).get(j));
				  }
			  }
		  }
		  return v;
	  }
	  public String[] wierszS(int x){
		  String[] v = new String[lista_nazw.length];
		  for (int i = 0; i <lista_nazw.length;i++) {
			  for (int j = 0; j< this.size();j++) {
				  if (j==x) {
					  if ((v[i]==null) || (this.outer.get(i).get(j)==null)){
						 // throw new UnevenColumnLengths("kolumny są różnej długości");
					  }
					  v[i]=(this.outer.get(i).get(j)).toString();
				  }
			  }
		  }
		  return v;
	  }
	 public String Pwiersz(String[] w) {
		 String s="|";
		 for (int i = 0; i< w.length;i++) {
			 s+=w[i]+" |";
		 }
		 return s;
	 }
	 
	 public String[] PFrame() {
		 String[] res= new String[this.size()];
		 for (int i = 0; i< this.size();i++) {
			 res[i]=Pwiersz(wierszS(i));
		 }
		 return res;
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
		         return dana instanceof DoubleV;  
		         
		     case "float":
		     case "Float":
		         return dana instanceof FloatV; 
		         
		     case "int":
		     case "integer":
		     case "Integer":
		         return dana instanceof IntegerV;
		         
		     case "long":
		     case "Long":
		         return dana instanceof Long;
		         
		     case "string":
		     case "String":
		         return dana instanceof StringV;
		         
		     default:
		        System.out.println("nieznany typ danych");
		        return false;
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
	 public void readingFromFileFunction(String path,boolean header) throws IOException {
		   // FileInputStream fstream = new FileInputStream(path);
		   try( BufferedReader br = new BufferedReader(new FileReader(path))){

		    String []strLine;

		    
		    
		    if(header) 
		    {
		    	//lista_nazw=null;
		        strLine=br.readLine().split(",");
		       // for (int i = 0; i < this.lista_typow.length; i++) {
		            lista_nazw=strLine;
		      //  }
		        
		    }
		        
		        DataFrame df = new DataFrame(lista_nazw, lista_typow );
		        String tmp;
		        Value[]wartosciZpliku =  new Value[lista_nazw.length];
		        while(((tmp=br.readLine() )!= null)){
		            strLine=tmp.split(",");
		            int m=0;
		            for(String wartosc:strLine){
		            	Class<? extends Value> typ;
		            	typ=lista_typow[m];
		            	try {
		            	wartosciZpliku[m]=typ.getConstructor().newInstance().create(wartosc);
		            	}catch(Exception e) {
		            		e.printStackTrace();
		            	}
		            	
		            	m++;
		            }
		            
		            
		           dodaj2(wartosciZpliku);
		        }
		   } catch (IOException e) {
	            throw new IOException("Blad przy czytaniu z pliku");
	       }
		    
		}

		    public DataFrame(String path,Class<? extends Value>[] typy_kolumn) throws IOException {
		       this(path,typy_kolumn,null);

		    }
		    public DataFrame(String path,Class<? extends Value>[]typy_kolumn, String[]nazwy_kolumn1) throws IOException{ 
		    	//this(typy_kolumn.length);
		        boolean header = nazwy_kolumn1==null;
		        this.lista_typow=typy_kolumn;
		        if(nazwy_kolumn1!=null) {
		        this.lista_nazw=nazwy_kolumn1;
		        
		        }else {
		        	this.lista_nazw= null;
		        }
		        outer = new ArrayList<ArrayList<Value>>();
				 ilosc_wierszy=0;
				   for (int i=0; i< lista_typow.length; i++) {
					   ArrayList<Value> inner = new ArrayList();
					   outer.add(inner);
				 }
		         readingFromFileFunction(path,header);
		        

		    } 
	 
		    public DataFrame apply (Applyable a) throws UnevenColumnLengths {
		    	DataFrame wynik = this.copyStructure();
		    	for (int i = 0; i<= this.lista.size();i++) {
		    		DataFrame df = this.lista.get(i);
		    		DataFrame nowa = a.apply(df);
			    	if (nowa.size() > 0) {
			    		for (int j=0;j<nowa.size();j++) {
			    			Value[] x = nowa.wiersz(j);
			    			wynik.dodaj2(x);
			    		}
			    	}
		    	}
		    	return wynik;
		    }
		    
		    public DataFrame copyStructure() {
		    	return new DataFrame(this.lista_nazw,this.lista_typow);
		    }
		    
		    public LinkedList<DataFrame> groupby(String nazwa) throws UnevenColumnLengths{
		    	//LinkedList<DataFrame> lista;
		    	LinkedList<DataFrame> lista;
		    	lista = new LinkedList<DataFrame>();
		    	ArrayList<Value> po;
		    	po=null;
		    	//DataFrame dfx = new DataFrame(lista_nazw,lista_typow);
		    	for (int j =0; j<lista_nazw.length;j++) {
		    		if (nazwa == lista_nazw[j]) {
		    			for (int i=0; i< this.size();i++) {
		    				Value v = this.outer.get(j).get(i);
		    				if (po.size()==0) {
		    					po.add(this.outer.get(j).get(i));
		    				}
		    				boolean czy = false;
		    				for(int k=0;k< po.size();k++) {
		    					if(po.get(k)==v) {
		    						czy=true;
		    					}
		    				}
		    				if(czy ==false) {
		    					po.add(v);
		    				}
		    			}
		    			
			    		for(int o=0;o<po.size();o++) {
			    			DataFrame tmp = new DataFrame(lista_nazw,lista_typow);
			    			for(int u=0;u<this.size();u++) {
			    				if(this.outer.get(j).get(u) == po.get(o)) {
			    					tmp.dodaj2(this.wiersz(u));
			    				}
			    			}
			    			lista.add(tmp);
			    		}
		    	}
		    		throw new IllegalArgumentException("Brak kolumny o podanej nazwie");

		    	
		    	}
		    	this.lista=lista;
		    	return lista;
		    }
		    
		    
		    class MojaGrupa{// implements Groupby{
		    	private LinkedList<DataFrame>lista;
		    	private String[] nazwy_kolumn;
		    	//Class<? extends Value>[] typy_kolumn;
		    	private ArrayList kolumna1;
		    	
		    	public MojaGrupa(Collection<DataFrame> lista,String[]nazwy_kolumn) {
		    		this.lista= new LinkedList<>(lista);
		    		this.nazwy_kolumn = nazwy_kolumn;
		    		this.kolumna1 = new ArrayList<>();
		    	}
		    	
		    	
		    	
		    }
		    
		    
		    
		   public ArrayList<Value> dodajKol(String[] nazwy)throws UnevenColumnLengths{
			   ArrayList<Value> wynik = new ArrayList();
			   wynik=null;
			   int tmp = 0;
			   for (int i=0; i< lista_nazw.length;i++){
				   for (int j = 0; j< nazwy.length; j++){
					   if (lista_nazw[i]==nazwy[j]){
						   if(outer.get(i).size()!=outer.get(tmp).size()) {
							   throw new UnevenColumnLengths("różne długości kolumn: "+lista_nazw[i]+lista_nazw[tmp]+"długości: "+String.valueOf(outer.get(i).size())+", "+String.valueOf(outer.get(tmp).size()));
						   }
						   for (int k =0; k<this.size();k++){
							    wynik.add(outer.get(i).get(k).add(outer.get(j).get(k))); 
						   }
						   tmp =i;
						 
					   }
				   }
			   }
			   return wynik;
		   }
		    
		   public ArrayList<Value> mulKol(String[] nazwy) throws NameNotFound{
			   ArrayList<Value> wynik = new ArrayList();
			   wynik=null;
			   for (int i=0; i< lista_nazw.length;i++){
				   for (int j = 0; j< nazwy.length; j++){
					   if (lista_nazw[i]==nazwy[j]){
						   for (int k =0; k<this.size();k++){
							    wynik.add(outer.get(i).get(k).mul(outer.get(j).get(k))); 
						   }
						 
					   }
				   }
			   }
			   return wynik;
		   }
		    
		    
		   public Value AddColumnData(String name){
			   Value wynik = null;
			   for (int j = 0; j< lista_nazw.length; j++){
				   if(name== lista_nazw[j]){
					   for (int i = 0; i< this.size();i++){
						   wynik.add(outer.get(j).get(i));
					   } 
				   }
			   }
			  
			   return wynik;
		   }
		   
		   
		   
		   public Value MulColumnData(String name)throws InvalidActionForThisTypeOfData{
			   DoubleV wynik = new DoubleV();
			   wynik.create("1");
			   for (int j = 0; j< lista_nazw.length; j++){
				
				   if(name== lista_nazw[j]){
					   for (int i = 0; i< this.size();i++){
						   if (outer.get(j).get(i) instanceof StringV) {
							   throw new InvalidActionForThisTypeOfData("Typ String nie ma opcji mnożenia przez typ String, kolumna: "+lista_nazw[j]+" wiersz: "+ String.valueOf(i));
						   }
						   wynik.mul(outer.get(j).get(i));
					   } 
				   }
				   
			   }
			   return wynik;
		   }
		   public ArrayList<Value> AddValToColumn(String name, Value val){
			   ArrayList<Value> wynik = new ArrayList();
			   wynik=null;
			   for (int i=0; i< lista_nazw.length;i++){
					   if (lista_nazw[i]==name){
						   for (int k =0; k<this.size();k++){
							    wynik.add(outer.get(i).get(k).add(val)); 
						   }
					   }
				   
			   }
			   return wynik;
		   }
		   
		   
		   public ArrayList<Value> MulColumnByVal(String name, Value val){
			   ArrayList<Value> wynik = new ArrayList();
			   wynik=null;
			   for (int i=0; i< lista_nazw.length;i++){
					   if (lista_nazw[i]==name){
						   for (int k =0; k<this.size();k++){
							    wynik.add(outer.get(i).get(k).mul(val)); 
						   }
					   }
				   
			   }
			   return wynik;
		   }
		   
		   public class IncorrectFileNameException extends Exception { 
			    public IncorrectFileNameException(String errorMessage, String colname, String idx) {
			        super(errorMessage);
			    }
			}
		   public class BadExcept extends Exception{
				  public BadExcept(String message){
					  super(message);
					  //message = "Operacja nie moze zostac wykonana dla podanych danych";
					  
				  }
				  public BadExcept(Throwable cause){
					  super(cause);
				  }
				  
				  public BadExcept(String message,Throwable cause){
					  super(message,cause);
				  }
			  }
		   
}
