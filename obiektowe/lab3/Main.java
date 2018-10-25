import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//DataFrame tmp = new DataFrame(new String[]{"a","b","c"},new String []{"String","Integer","bool"});
		DataFrame tmp = new DataFrame(new String[] {"a","b","c"}, new Class[] {StringV.class,IntegerV.class,StringV.class});
		tmp.dodaj("a", "abc");
		tmp.dodaj("a", "def");
		tmp.dodaj("b", 88);
		tmp.dodaj("a", "abrakadabra");
		tmp.dodaj("c",  "T");
		tmp.dodaj("b", 88);
		tmp.dodaj("a", "abrakadabra");
		tmp.dodaj("c",  "T");
		tmp.dodaj("c",  "F");
		tmp.dodaj("b", 111);
		tmp.dodaj("c",  "F");
		tmp.dodaj("b", 112);
		System.out.println(tmp.outer);
		System.out.println(tmp.iloc(2,2).outer);
		String x = "abrakadabra";
		//SparseDataFrame sp = new SparseDataFrame(tmp,new StringV("hello"));
		//System.out.println(sp.outer);
		System.out.println(tmp.size());
	}

}
