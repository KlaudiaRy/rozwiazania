package dataframe;
import java.util.ArrayList;

public class Astd implements Applyable{
	@Override
	public DataFrame apply(DataFrame df) {
		DataFrame wyn = new DataFrame(df.lista_nazw,df.lista_typow);
		int size = df.size();
		Value[] st = new Value[wyn.lista_nazw.length];
		Applyable a = new Avar();
		DataFrame var =  a.apply(df);
		
		Value[] wariancja = var.wiersz(0);
		if (size>0) {
			for(int i = 0; i< df.lista_nazw.length;i++) {
				st[i]=wariancja[i].pow(new DoubleV(0.5));
			}
			wyn.dodaj2(st);
		}
		return wyn;
	}
}
