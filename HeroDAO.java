
package model;

import config.Koneksi;
import java.sql.*;
import java.util.*;

public class HeroDAO {
    private Connection conn;

    public HeroDAO() {
        conn = Koneksi.getConnection();
    }

    public void insert(Hero hero) {
        String sql = "INSERT INTO tm_hero (nama_hero, kategori, gender) VALUES (?, ?, ?)";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, hero.getNama());
            pst.setString(2, hero.getKategori());
            pst.setString(3, hero.getGender());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void update(Hero hero) {
        String sql = "UPDATE tm_hero SET nama_hero=?, kategori=?, gender=? WHERE id_hero=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, hero.getNama());
            pst.setString(2, hero.getKategori());
            pst.setString(3, hero.getGender());
            pst.setInt(4, hero.getId());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM tm_hero WHERE id_hero=?";
        try (PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, id);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Hero> getAll() {
        List<Hero> list = new ArrayList<>();
        String sql = "SELECT * FROM tm_hero";
        try (Statement st = conn.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                list.add(new Hero(
                    rs.getInt("id_hero"),
                    rs.getString("nama_hero"),
                    rs.getString("kategori"),
                    rs.getString("gender")
                ));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
}
