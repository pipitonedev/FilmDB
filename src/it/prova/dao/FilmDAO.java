package it.prova.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import it.prova.model.Film;
import it.prova.model.Regista;

import it.prova.connection.*;

public class FilmDAO {

	public List<Film> list() {

		Connection c = null;
		ResultSet rs = null;
		Statement s = null;
		List<Film> result = new ArrayList<Film>();

		try {

			c = MyConnection.getConnection();
			s = c.createStatement();

			rs = s.executeQuery("select * from film f inner join regista r on r.id=f.regista;");

			while (rs.next()) {
				Film filmTemp = new Film();
				filmTemp.setTitolo(rs.getString("titolo"));
				filmTemp.setGenere(rs.getString("genere"));
				filmTemp.setId(rs.getLong("id"));
				filmTemp.setDurata(rs.getInt("durata"));

				Regista registaTemp = new Regista();
				registaTemp.setId(rs.getLong("id"));
				registaTemp.setNome(rs.getString("nome"));
				registaTemp.setCognome(rs.getString("cognome"));
				registaTemp.setNumeroOscarVinti(rs.getInt("numeroOscarVinti"));

				filmTemp.setRegista(registaTemp);
				result.add(filmTemp);
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				rs.close();
				s.close();
				c.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	// INSERT//

	public int insert(Film film) {

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("INSERT INTO film (titolo, genere,durata,regista) VALUES (?, ?,?, ?);");
			ps.setString(1, film.getTitolo());
			ps.setString(2, film.getGenere());
			ps.setInt(3, film.getDurata());
			ps.setLong(4, film.getRegista().getId());

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

	// UPDATE//

	public int update(Film input) {

		if (input == null || input.getId() < 1) {
			return 0;
		}

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("UPDATE film SET titolo=?, genere=?, durata=? where id=? and regista=?;");
			ps.setString(1, input.getTitolo());
			ps.setString(2, input.getGenere());
			ps.setInt(3, input.getDurata());
			ps.setLong(4, input.getId());
			ps.setLong(5, input.getRegista().getId());

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

	// DELETE//

	public int delete(Film input) {

		if (input == null || input.getId() < 1) {
			return 0;
		}

		Connection c = null;
		PreparedStatement ps = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();
			ps = c.prepareStatement("DELETE from film where id=?;");
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

	public int insertCompleto(Film film) {

		Connection c = null;
		PreparedStatement ps = null;
		PreparedStatement ps2 = null;
		int result = 0;

		try {

			c = MyConnection.getConnection();

			// prima l'indirizzo
			ps = c.prepareStatement("INSERT INTO regista (nome, cognome,numerooscarvinti) VALUES (?, ?,?);",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, film.getRegista().getNome());
			ps.setString(2, film.getRegista().getCognome());
			ps.setInt(3, film.getRegista().getNumeroOscarVinti());

			int indirizziInseriti = ps.executeUpdate();

			// mi metto da parte l'id inserito
			int last_inserted_id = -1;
			ResultSet rs = ps.getGeneratedKeys();
			if (rs.next()) {
				last_inserted_id = rs.getInt(1);
			}

			// poi l'abitante
			if (indirizziInseriti > 0) {
				ps2 = c.prepareStatement("INSERT INTO film (titolo, genere,durata,regista) VALUES (?, ?,?,?);");
				ps2.setString(1, film.getTitolo());
				ps2.setString(2, film.getGenere());
				ps2.setInt(3, film.getDurata());
				ps2.setInt(4, last_inserted_id);
				result = ps2.executeUpdate();
			}

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

}
