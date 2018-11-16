import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		//DataFrame tmp = new DataFrame(new String[]{"a","b","c"},new String []{"String","Integer","bool"});
		DataFrame tmp = new DataFrame(new String[] {"a","b","c"}, new Class[] {StringV.class,IntegerV.class,StringV.class});
		/*tmp.dodaj("a", "abc");
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
		tmp.dodaj("b", 112);*/
		
		//Value v = IntegerV.create("1");
		StringV ss = new StringV();
		IntegerV ff = new IntegerV();
		tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});
		tmp.dodaj2(new Value[] {ss.create("ABA"),ff.create("1"),ss.create("bbb")});
		tmp.dodaj2(new Value[] {ss.create("AAA"),ff.create("1"),ss.create("bbb")});
		//tmp.groupby("a");
		System.out.println(tmp.outer);
		System.out.println(tmp.wiersz(2));
		//System.out.println(tmp.iloc(0,0).outer);
		String x = "abrakadabra";
		//SparseDataFrame sp = new SparseDataFrame(tmp,new StringV("hello"));
		//System.out.println(sp.outer);
		System.out.println(tmp.size());
		System.out.println(tmp.lista_typow[1]);
	}

}
