import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DataFrame tmp = new DataFrame(new String[]{"a","b","c"},new String []{"String","Integer","bool"});
		tmp.dodaj("a", "abc");
		tmp.dodaj("a", "def");
		tmp.dodaj("b", 88);
		tmp.dodaj("a", "abrakadabra");
		tmp.dodaj("c",  true);
		tmp.dodaj("b", 88);
		tmp.dodaj("a", "abrakadabra");
		tmp.dodaj("c",  true);
		tmp.dodaj("c",  false);
		tmp.dodaj("b", 111);
		tmp.dodaj("c",  false);
		tmp.dodaj("b", 112);
		System.out.println(tmp.outer);
		System.out.println(tmp.iloc(2,4).outer);
		Object x = "abrakadabra";
		SparseDataFrame sp = new SparseDataFrame(tmp,x);
		//System.out.println(sp.outer);
		System.out.println(tmp.size());
	}

}
