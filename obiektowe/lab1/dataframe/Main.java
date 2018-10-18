import java.util.ArrayList;
import java.util.List;

public class Main {

	public static void main(String[] args) {
		DataFrame tmp = new DataFrame(new String[]{"z","g","r"},new String []{"String","Integer","bool"});
		tmp.outer.get(0).add("abc");
		tmp.outer.get(0).add(1);
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
		
		
	}

}
