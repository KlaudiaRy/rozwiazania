import java.util.*;

public class DataFrame {
    Kolumna[] kolumny;
    int ilosc_wierszy;

    public DataFrame(String[] nazwy, String[] typy) {
        if (nazwy.length != typy.length) {
        	throw new IllegalArgumentException("Niepoprawna ilosc wpisanych danych");
        }
        ilosc_wierszy = 0;        
        kolumny = new Kolumna[nazwy.length];
        for (int i = 0; i < nazwy.length; i++) {
            kolumny[i] = new Kolumna(nazwy[i], typy[i]);
        }
    }

    
    public DataFrame(Kolumna[] kolumny) {
        this.kolumny = kolumny;
        ilosc_wierszy = kolumny[0].dane_w_kolumnie.size();
    }

    
    public int size() {
        return ilosc_wierszy; 
    }

    public Kolumna get(String colname) {
        for (Kolumna i : kolumny) {
            if (i.nazwa == colname) {
                return i;
            }
        }
        throw new IllegalArgumentException("Brak kolumny o podanej nazwie");

    }


    public DataFrame iloc(int i){
        return iloc(i,i);
    }

    public DataFrame get(String[] cols,boolean copy){ 
        Kolumna[] nowe_kolumny = new Kolumna[cols.length];
        for(int i=0; i<cols.length; i++) {
            if (copy){
                nowe_kolumny[i] = new Kolumna(get(cols[i]));
                }
            else {
                nowe_kolumny[i] = get(cols[i]);
                }
        }
        if (copy) {
        	System.out.println("wykonano kopiê g³êbok¹");
        }else {
        	System.out.println("wykonano kopiê p³ytk¹");
        }
        DataFrame df3 = new DataFrame(nowe_kolumny);
        return df3;
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

        String[] typy = new String [kolumny.length];
        String[] nazwy = new String [kolumny.length];

        for (int i=0;i<kolumny.length;i++){
            typy[i]= new String (kolumny[i].typ);
            nazwy[i]=new String (kolumny[i].nazwa);
        }

        DataFrame df2 = new DataFrame(nazwy,typy);
        Object[] nowe_dane = new Object[kolumny.length];

        for (int i=from; i<to; i++){
            for (Kolumna x: this.kolumny){
                nowe_dane[i]=x.dane_w_kolumnie.get(i); //wybieranie danych z zakresu
            }
            for (int j=0; j<nowe_dane.length; j++){
                    df2.kolumny[j].dane_w_kolumnie.add(nowe_dane[j]); //przypisywanie danych do nowej ramki 
            }
        }
        return df2;
    }
       
}


