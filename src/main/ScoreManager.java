package main;

import java.sql.*;
import java.util.LinkedList;

public class ScoreManager {
    private static final String DB_URL = "jdbc:sqlite:highscores.db";

    public ScoreManager() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) { // interogare de tip DDL
            // defineste cum arata baza de date
            // tablea contine un id unic automat incrementat pentru fiecare scor
            // name: nume jucator; score: scorul obtinut
            // timestamp: data si ora la care a fost inregistrat scorul
            String sql = "CREATE TABLE IF NOT EXISTS scores (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "name TEXT," +
                    "score INTEGER," +
                    "timestamp DATETIME DEFAULT CURRENT_TIMESTAMP)";
            conn.createStatement().execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertScore(String name, int score) {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // insereaaza un nou rand in tabel cu numele si scorul jucatorului
            String insert = "INSERT INTO scores (name, score) VALUES (?, ?)";
            PreparedStatement pstmt = conn.prepareStatement(insert);
            pstmt.setString(1, name);
            pstmt.setInt(2, score);
            pstmt.executeUpdate();

            // Daca sunt mai mult de 10 intrari, elimina cele cu scor mai mic
            String deleteOldest = "DELETE FROM scores WHERE id NOT IN (SELECT id FROM scores ORDER BY score DESC LIMIT 10)";
            conn.createStatement().execute(deleteOldest);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getHighScore() {
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            // interogare simpla care returneaza maximul din coloana score
            String sql = "SELECT MAX(score) FROM scores";
            ResultSet rs = conn.createStatement().executeQuery(sql);
            return rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public int getPlayerRank(String name, int score) {
        int rank = 1;
        try (Connection conn = DriverManager.getConnection(DB_URL)) {
            String sql = "SELECT name, score FROM scores ORDER BY score DESC, timestamp ASC";
           // se pregateste o interogare care selecteazza coloanele
           // name si score din tabela scores si le sortam descrescator
            // daca 2 scoruri sunt egale, al doilea criteriu este timpul
            // inregistrarii, punandu-l pe cel mai recent pe primul loc
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // se parcurge fiecare linie din rezulatul interogarii
            while (rs.next()) {
                String currentName = rs.getString("name");
                int currentScore = rs.getInt("score");
                // se verifica daca numele si scorul din randul curent
                // coincid cu cele transmise metodei
                if (currentName.equals(name) && currentScore == score) {
                    return rank;
                }
                rank++; // nu a fost gasit, incrementam cu 1
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1; // nu s-a gasit (teoretic nu ar trebui sa se intample)
    }

}
