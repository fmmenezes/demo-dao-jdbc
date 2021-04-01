package application;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		//Department depto = new Department(1, "Books");
		//System.out.println(depto);

		//Seller seller = new Seller(1, "Branca Mendes", "branca.mendes@hotmail.com", new Date(), 10000.00, depto);
		//System.out.println(seller);
		
		SellerDao sellerDao = DaoFactory.createSellerDao();
		
		Seller seller = sellerDao.findById(3);
		System.out.println(seller);


	}

}
