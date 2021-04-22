package application;

import java.util.Date;
import java.util.List;

import model.dao.DaoFactory;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class Program {

	public static void main(String[] args) {

		//Department depto = new Department(1, "Books");
		//System.out.println(depto);

		//Seller seller = new Seller(1, "Branca Mendes", "branca.mendes@hotmail.com", new Date(), 10000.00, depto);
		//System.out.println(seller);
		


//		Teste para o findById
//		SellerDao sellerDao = DaoFactory.createSellerDao();
//		Seller seller = sellerDao.findById(3);
//		System.out.println(seller);
//		
//		Department depto = new Department(2, null);
//		List<Seller> list = sellerDao.findByDepartment(depto);
//		for(Seller obj : list) {
//			System.out.println(obj);
//		}

		// Teste para o findAll
		/*
		SellerDao sellerDao = DaoFactory.createSellerDao();
		List<Seller> list = sellerDao.findAll();
		for(Seller obj : list) {
			System.out.println(obj);
		}
		*/
		
		// Teste 4 Inserindo o registro
		/*
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Department depto = new Department(2, null);
		Seller seller = new Seller(null, "Cibele", "cibele@gmail.com", new Date(), 2000.00, depto);
		sellerDao.insert(seller);
		System.out.println("Inserido! Novo id = " + seller.getId());
		*/

		/*
		// Teste 5 Alterando o registro
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = new Seller();
		seller = sellerDao.findById(1);
		seller.setName("Tatolina");
		sellerDao.update(seller);
		System.out.println("Alterado!");
		*/
		
		// Teste 6 Excluindo o registro
		SellerDao sellerDao = DaoFactory.createSellerDao();
		Seller seller = new Seller();
		seller = sellerDao.findById(1);
		sellerDao.deleteById(1);
		System.out.println("Deletado!");

		
	}

}
