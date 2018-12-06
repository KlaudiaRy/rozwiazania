package dataframe;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException, IllegalAccessException, InstantiationException {
		DataFrame tmp = new DataFrame(new String[] {"a","b","c"}, new Class[] {StringV.class,IntegerV.class,StringV.class});
	
		StringV ss = new StringV();
		IntegerV ff = new IntegerV();
		tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});
		tmp.dodaj2(new Value[] {ss.create("ABA"),ff.create("1"),ss.create("bbb")});
		tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});
		//System.out.println(tmp.outer);
		//System.out.println(tmp.wiersz(2));
		String x = "abrakadabra";
		//System.out.println(tmp.size());
		//System.out.println(tmp.lista_typow[1]);

       try { 
    	  // DataFrame tmp1 = new DataFrame("C:\\Users\\xhapp\\Downloads\\data.csv", new Class[] {StringV.class,StringV.class,StringV.class},new String[] {"a","b","c"});
    	   DataFrame tmp1 = new DataFrame("C:\\Users\\xhapp\\Downloads\\data.csv", new Class[] {StringV.class,StringV.class,StringV.class});
    	   System.out.println(tmp1.Pwiersz(tmp1.wierszS(2)));
    	   String[] res = tmp1.PFrame();
    	   String n1= tmp1.Pwiersz(tmp1.lista_nazw);
    	   System.out.println(n1);
    	    for(String str : res)
    	        System.out.println(str);

       }catch(IOException e) {
    	   e.printStackTrace();
       }
       
	}

}
