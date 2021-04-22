package model.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import db.DB;
import db.DbException;
import model.dao.SellerDao;
import model.entities.Department;
import model.entities.Seller;

public class SellerDaoJDBC implements SellerDao {

	private Connection conn;

	public SellerDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Seller seller) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement(
					"Insert into Seller (Name, Email, BirthDate, BaseSalary, DepartmentId) " + "Values (?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);

			prst.setString(1, seller.getName());
			prst.setString(2, seller.getEmail());
			prst.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			prst.setDouble(4, seller.getBaseSalary());
			prst.setInt(5, seller.getDepartment().getId());

			int resultInsert = prst.executeUpdate();

			if (resultInsert > 0) {
				ResultSet rs = prst.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					seller.setId(id);
				}
				DB.closeResultSet(rs);
			} else {
				throw new DbException("Erro ao incluir o registro");
			}

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(prst);
		}
	}

	@Override
	public void update(Seller seller) {
		PreparedStatement prst = null;
		try {
			prst = conn.prepareStatement(
					"Update Seller set Name = ?, Email = ?, BirthDate = ?, BaseSalary = ?, DepartmentId = ? "
							+ "where Id = ?");

			prst.setString(1, seller.getName());
			prst.setString(2, seller.getEmail());
			prst.setDate(3, new java.sql.Date(seller.getBirthDate().getTime()));
			prst.setDouble(4, seller.getBaseSalary());
			prst.setInt(5, seller.getDepartment().getId());
			prst.setInt(6, seller.getId());

			prst.executeUpdate();

		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(prst);
		}

	}

	@Override
	public void deleteById(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public Seller findById(Integer id) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ?");

			prst.setInt(1, id);
			rs = prst.executeQuery();

			if (rs.next()) {
				Department depto = new Department();
				depto.setId(rs.getInt("DepartmentId"));
				depto.setName(rs.getString("DepName"));

				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setDepartment(depto);
				return seller;

			}
			return null;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(prst);
			DB.closeResultSet(rs);

		}
	}

	@Override
	public List<Seller> findAll() {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement("SELECT seller.*,department.Name as DepName "
					+ "FROM seller INNER JOIN department " + "ON seller.DepartmentId = department.Id order by Name");

			rs = prst.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department depto = map.get(rs.getInt("DepartmentId"));

				if (depto == null) {
					depto = new Department();
					depto.setId(rs.getInt("DepartmentId"));
					depto.setName(rs.getString("DepName"));

					map.put(rs.getInt("DepartmentId"), depto);

				}

				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setDepartment(depto);
				list.add(seller);

			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(prst);
			DB.closeResultSet(rs);

		}
	}

	@Override
	public List<Seller> findByDepartment(Department department) {
		PreparedStatement prst = null;
		ResultSet rs = null;
		try {
			prst = conn.prepareStatement(
					"SELECT seller.*,department.Name as DepName " + "FROM seller INNER JOIN department "
							+ "ON seller.DepartmentId = department.Id " + "WHERE seller.Id = ? order by Name");

			prst.setInt(1, department.getId());
			rs = prst.executeQuery();

			List<Seller> list = new ArrayList<>();
			Map<Integer, Department> map = new HashMap<>();

			while (rs.next()) {

				Department depto = map.get(rs.getInt("DepartmentId"));

				if (depto == null) {
					depto = new Department();
					depto.setId(rs.getInt("DepartmentId"));
					depto.setName(rs.getString("DepName"));

					map.put(rs.getInt("DepartmentId"), depto);

				}

				Seller seller = new Seller();
				seller.setId(rs.getInt("Id"));
				seller.setName(rs.getString("Name"));
				seller.setEmail(rs.getString("Email"));
				seller.setBaseSalary(rs.getDouble("BaseSalary"));
				seller.setBirthDate(rs.getDate("BirthDate"));
				seller.setDepartment(depto);
				list.add(seller);

			}
			return list;

		} catch (SQLException e) {
			throw new DbException(e.getMessage());

		} finally {
			DB.closeStatement(prst);
			DB.closeResultSet(rs);

		}
	}

}
