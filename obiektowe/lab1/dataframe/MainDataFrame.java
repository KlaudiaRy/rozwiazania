
public class MainDataFrame {

	public static void main (String[] args) {
		
		DataFrame df = new DataFrame(new String[]{"kol1","kol2","kol3"},new String []{"Integer","String","Double"});
		
		System.out.println(df.size());
		System.out.println(df.get("kol3"));
		String[] wybrane_kolumny = {"kol1","kol2"};
		System.out.println(df.get(wybrane_kolumny,true));
		//System.out.println(df.iloc(1,1));
		//System.out.println(df.iloc(1));
		System.out.println(df);

    }
	
}
