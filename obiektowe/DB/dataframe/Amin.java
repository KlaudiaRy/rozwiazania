package dataframe;

public class Amin implements Applyable {
	@Override
	public DataFrame apply(DataFrame df) {
		DataFrame wyn = new DataFrame(df.lista_nazw,df.lista_typow);
		int size = df.size();
		
		
		if (size>0) {
			Value[] min = df.wiersz(0);
			for (int i=0; i< size; i++) {
				Value[] wr = df.wiersz(i);
				for (int k =0; k< wr.length;k++) {
					if(min[k].gte(wr[k])) {
						min[k]=wr[k];
					}
				}
			}
			wyn.dodaj2(min);
		}
		return wyn;
	}
}
