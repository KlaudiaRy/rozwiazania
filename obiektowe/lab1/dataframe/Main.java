import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DataFrame tmp = new DataFrame(new String[]{"z","g","r"},new String []{"String","Integer","bool"});
		tmp.dodaj("z", "abc");
		tmp.dodaj("z", "def");
		tmp.dodaj("g", 88);
		tmp.dodaj("z", "abrakadabra");
		tmp.dodaj("r",  true);
		tmp.dodaj("g", 88);
		tmp.dodaj("z", "abrakadabra");
		tmp.dodaj("r",  true);
		tmp.dodaj("r",  false);
		tmp.dodaj("g", 111);
		tmp.dodaj("r",  false);
		tmp.dodaj("g", 112);
		System.out.println(tmp.outer);
		System.out.println(tmp.iloc(2,4).outer);
		Object x = "abrakadabra";
		SparseDataFrame sp = new SparseDataFrame(tmp,x);
		//System.out.println(sp.outer);
		System.out.println(tmp.size());
	}

}
