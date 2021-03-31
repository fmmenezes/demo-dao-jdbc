package application;

import java.util.Date;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		Department depto = new Department(1, "Books");
		System.out.println(depto);

		Seller seller = new Seller(1, "Branca Mendes", "branca.mendes@hotmail.com", new Date(), 10000.00, depto);
		System.out.println(seller);

	}

}
