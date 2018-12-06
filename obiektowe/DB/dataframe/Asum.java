package dataframe;
import java.util.ArrayList;

public class Asum implements Applyable{
	@Override
	public DataFrame apply(DataFrame df) {
		DataFrame wyn = new DataFrame(df.lista_nazw,df.lista_typow);
		int size = df.size();
		Value[] s = new Value[wyn.lista_nazw.length];
		
		if (size>0) {
			for(int i = 0; i< df.lista_nazw.length;i++) {
				ArrayList<Value> kol = new ArrayList();
				kol=df.outer.get(i);
				Value suma = new DoubleV(0.0);
				for (int j = 0; j< kol.size();j++) {
					suma = suma.add(kol.get(j));
				}
				s[i]=suma;
			}
			wyn.dodaj2(s);
		}
		return wyn;
	}
}
