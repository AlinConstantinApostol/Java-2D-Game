package main;

import javax.xml.transform.Result;
import java.io.File;
import java.sql.*;


public class SQL {

    public Connection c;

    public SQL() {
        try { // bloc try/catch pentru a prinde orice eroare
            Class.forName("org.sqlite.JDBC"); // incarcam in memorie
            // driverul JDBC pentru SQLite
            // cum sa se conecteze
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            // cream conexiunea catre fisierul configuratii.db
            c.setAutoCommit(false);
            // dezactiveaza commit-ul automat
            // Crearea tabelului daca nu exista deja
            Statement stmt = c.createStatement();
            // Statement SQL folosit pentru a executa interogari
            String sql = "CREATE TABLE IF NOT EXISTS SCORE (" +
                    "LEVEL INT NOT NULL," +
                    "LIFE INT NOT NULL," +
                    "MANNA INT NOT NULL," +
                    "PUNCTAJ INT NOT NULL," +
                    "NUME TEXT NOT NULL," +
                    "HASTOY BOOLEAN NOT NULL," +
                    "HASBOOK BOOLEAN NOT NULL," +
                    "HASKEY BOOLEAN NOT NULL," +
                    "TALKED_TO_BUTCH BOOLEAN NOT NULL)";
            stmt.executeUpdate(sql);

            // Verificam daca exista deja date
            ResultSet rs = stmt.executeQuery("SELECT COUNT(*) AS count FROM SCORE;");
            if (rs.getInt("count") == 0) {
                // Daca nu exista niciun rand, adaugam unul cu valori implicite
                stmt.executeUpdate("INSERT INTO SCORE (LEVEL, LIFE, MANNA, PUNCTAJ, NUME," +
                        "HASTOY, HASBOOK, HASKEY, TALKED_TO_BUTCH) VALUES (0, 7, 0, 0, 'Anonim', 0, 0, 0, 0)");
            }
            // confirmam modificarile si inchidem conexiunea
            c.commit();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public int GetLife() {
        Statement stmt = null;
        int life = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            // Executa o interogare SQL pentru a selecta toate inregistrarile
            // din tabela
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) { // mergem la primul rand(daca exista)
                // si extragem valoarea din coloana LIFE si o punem in life
                life = rs.getInt("LIFE");
                break;
            }

            // inchide setul de rezultate si declaratia SQL
            rs.close();
            stmt.close();
            c.close();
            // inchide conexiunea la baza de date
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return life;
    }

    public boolean GetHasTalkedToButch() {
        Statement stmt = null;
        boolean hastalkedtobutch = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                hastalkedtobutch = rs.getBoolean("TALKED_TO_BUTCH");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hastalkedtobutch;
    }

    public boolean GetToy() {
        Statement stmt = null;
        boolean hastoy = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                hastoy = rs.getBoolean("HASTOY");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hastoy;
    }

    public boolean GetBook() {
        Statement stmt = null;
        boolean hasbook = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                hasbook = rs.getBoolean("HASBOOK");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return hasbook;
    }

    public boolean GetKey() {
        Statement stmt = null;
        boolean haskey = false;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                haskey = rs.getBoolean("HASKEY");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return haskey;
    }

    public int GetManna() {
        Statement stmt = null;
        int manna = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                manna = rs.getInt("MANNA");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return manna;
    }

    public int GetLevel() {
        Statement stmt = null;
        int level = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                level = rs.getInt("LEVEL");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return level;
    }

    public int GetScore() {
        Statement stmt = null;
        int level = 0;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                level = rs.getInt("PUNCTAJ");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return level;
    }

    public String GetName() {
        Statement stmt = null;
        String nume = "";
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stmt = c.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM SCORE;");
            while (rs.next()) {
                nume = rs.getString("NUME");
                break;
            }
            rs.close();
            stmt.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
        return nume;
    }

    public void updateLevel(int level) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE set LEVEL = '" + level + "'";
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateToy(boolean hastoy) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE SET HASTOY = " + (hastoy ? 1 : 0);
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateBook(boolean hasbook) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE SET HASBOOK = " + (hasbook ? 1 : 0);
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateKey(boolean haskey) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE SET HASKEY = " + (haskey ? 1 : 0);
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateHasTalkedToButch(boolean hastalkedtobutch) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE SET TALKED_TO_BUTCH = " + (hastalkedtobutch ? 1 : 0);
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateLife(int life) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql2 = "UPDATE SCORE set LIFE = '" + life + "'";
            // setam valoarea LIFE in acel rand cu life dat ca argument
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql2);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateManna(int Manna) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql2 = "UPDATE SCORE set MANNA = '" + Manna + "'";
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql2);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateScore(int score) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql2 = "UPDATE SCORE set PUNCTAJ = '" + score + "'";
            //c# string s = $"UPDATE SCORE {kills}";
            stm.executeUpdate(sql2);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }

    public void updateName(String nume) {
        Statement stm = null;
        try {
            Class.forName("org.sqlite.JDBC");
            c = DriverManager.getConnection("jdbc:sqlite:configuratii.db");
            c.setAutoCommit(false);
            stm = c.createStatement();
            String sql = "UPDATE SCORE set NUME = '" + nume + "'";
            stm.executeUpdate(sql);
            c.commit();
            stm.close();
            c.close();
        } catch (Exception e) {
            System.err.println(e.getClass().getName() + ": " + e.getMessage());
            System.exit(0);
        }
    }
}