
public class Main {

	public static void main(String[] args) {
		DB db= new DB();
		db.connect();
		db.listAll();
		db.byNumber("1234567891247");
		db.byAuthor("Pamuk");
	}

}
