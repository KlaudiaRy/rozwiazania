import java.util.ArrayList;

public class Avar implements Applyable {
	@Override
	public DataFrame apply(DataFrame df){
		DataFrame wyn = new DataFrame(df.lista_nazw,df.lista_typow);
		Value[] vr = new Value[wyn.lista_nazw.length];
		Applyable a = new Amean();
		DataFrame mean =  a.apply(df);
		int size = mean.size();
        Value[] srd = mean.wiersz(0);

		
		if (size>0) {
			for(int i=0; i<mean.lista_nazw.length;i++) {
				ArrayList<Value> kol = new ArrayList();
				kol= df.outer.get(i);
				Value rozn;
				Value suma = new DoubleV(0.0);
				for (int j =0; j< kol.size();j++) {
					rozn = kol.get(j).sub(srd[i]).mul(kol.get(j).sub(srd[i]));
					suma = suma.add(rozn);
				}
				vr[i] = suma.div(new DoubleV((df.size())));
			}
			wyn.dodaj2(vr);
		}
		return wyn;
	}
}
