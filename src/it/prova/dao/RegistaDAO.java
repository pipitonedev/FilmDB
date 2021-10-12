package it.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import it.prova.connection.*;
import it.prova.model.Film;
import it.prova.model.Regista;

public class RegistaDAO {

	public int insert(Regista nuovoRegista) {

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("INSERT INTO regista (nome, cognome,numerooscarvinti) VALUES (?, ?,?);");
			ps.setString(1, nuovoRegista.getNome());
			ps.setString(2, nuovoRegista.getCognome());
			ps.setInt(3, nuovoRegista.getNumeroOscarVinti());

			result = ps.executeUpdate();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				ps.close();
				c.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int update(Regista input) {

		if (input == null || input.getId() < 1) {
			return 0;
		}

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("UPDATE film SET nome=?, cognome=?, numerooscarvinti=? where id=?;");
			ps.setString(1, input.getNome());
			ps.setString(2, input.getCognome());
			ps.setInt(3, input.getNumeroOscarVinti());
			ps.setLong(4, input.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				ps.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public int delete(Regista input) {

		if (input == null || input.getId() < 1) {
			return 0;
		}

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("DELETE from regista where id=?;");
			ps.setLong(1, input.getId());

			result = ps.executeUpdate();

		} catch (Exception e) {

			e.printStackTrace();

		} finally {
			try {
				ps.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return result;
	}

	public List<Regista> findAllRegistaConPiuOscar() {

		Connection c = null;
		ResultSet rs = null;
		PreparedStatement ps = null;
		List<Regista> result = new ArrayList<Regista>();

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("select * from regista r WHERE r.numerooscarvinti = (select max(numerooscarvinti) from regista r)");
			rs = ps.executeQuery();

			while (rs.next()) {
				Regista registaTemp = new Regista();
				registaTemp.setId(rs.getLong("id"));
				registaTemp.setNome(rs.getString("nome"));
				registaTemp.setCognome(rs.getString("cognome"));
				registaTemp.setNumeroOscarVinti(rs.getInt("numerooscarvinti"));

				result.add(registaTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				ps.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
