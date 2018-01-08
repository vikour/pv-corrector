package modelo;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

class BD {

    // Configuración de la base de datos.
    private static final String BD_FILE_NAME = "datos.db";
    private static final String BD_DDL_BD = "src/resources/ddl.sql";
    private static final String DB_URL = "jdbc:sqlite:" + BD_FILE_NAME;
    // Fin configuración.
    
    private static BD instance = null;
    private Connection con;

    private BD() {
        boolean ddl_loaded = false;
        try {
            Class.forName("org.sqlite.JDBC");
            ddl_loaded = (new File(BD_FILE_NAME)).exists();
            con = DriverManager.getConnection(DB_URL);
            execute("PRAGMA foreign_keys = ON");

            if (!ddl_loaded) {
                
                if (BD_DDL_BD == null)
                    cargarDDL();
                else
                    leerDDL();
                
            }
            
        } catch (ClassNotFoundException ex) {
            System.err.println("No se ha encontrado el driver de SQLite");
            System.exit(1);
        } catch (SQLException e) {
            System.err.println("Hubo un error al iniciar la base de datos");
            System.exit(1);
        }
    }
    
    public static BD getInstance() {

        if (instance == null) {
            instance = new BD();
        }

        return instance;
    }

    private void cargarDDL() {
        // DDL va aqui
    }

    private void leerDDL() {
        BufferedReader br = null;
        StringBuilder sb = new StringBuilder();
        int c;
        
        try {
            br = new BufferedReader(new FileReader(BD_DDL_BD));
            
            c = br.read();
            
            while (c != -1) {
                
                if ( ((char) c) != ';')
                    sb.append((char) c);
                else {
                    execute(sb.toString().trim().replace("\n", ""));
                    sb = new StringBuilder();
                }
                
                c = br.read();
            }


        }
        catch (IOException ex) {
            System.err.println("Error al leer el fichero DDL : " + ex.getMessage());
            borrarDB();
        }
        finally {
            
            if (br != null)
                try {
                    br.close();
                }
                catch (IOException ex) {
                    System.out.println("Error al cerrar el fichero : " + ex.getMessage());
                }
            
        }
        
        execute("CREATE TRIGGER remove_curvas_iv AFTER DELETE ON curvas_medidas FOR EACH ROW BEGIN " + 
                " DELETE FROM curvas_iv WHERE id = OLD.id; END");
    }

    /**
     * Ejecuta una sentencia SELECT en la base de datos y devuelve una lista de 
     * tuplas variable.
     * 
     * @param query  Sentencia SELECT
     * @return       Una lista de tuplas con el resultado.
     */
    
    public List<String[]> select(String query) {
        ArrayList<String[]> list = new ArrayList<>();

        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();

            while (rs.next()) {
                String[] sm = new String[rsmt.getColumnCount()];

                for (int i = 0; i < sm.length; i++) {
                    sm[i] = rs.getString(i+1);
                }

                list.add(sm);
            }

        } catch (SQLException ex) {
            System.out.println("Error al ejecutar sentencia : " + query + "\nError : " + ex.getMessage());
        }

        return list;
    }

    /**
     * Ejecuta una setencia SELECT en la base de datos.
     * 
     * @param query  Sentencia SELECT
     * @return  Un 'String' con el primer valor del select. NULL si no existe tal valor.
     */
    
    public String selectEscalar(String query) {
        String result = null;
        
        try {
            Statement stm = con.createStatement();
            ResultSet rs = stm.executeQuery(query);
            ResultSetMetaData rsmt = rs.getMetaData();
            
            if (rsmt.getColumnCount() > 0 && rs.next())
                result = rs.getString(1);
            
        }
        catch (SQLException ex) {
            System.out.println("Error al ejecutar sentencia : " + query + "\nError : " + ex.getMessage());
        }
        
        return result;
    }
    
    private void borrarDB() {
        File file_db = new File(BD_FILE_NAME);
        
        if (file_db.exists()) {
            close();
            file_db.delete();
            instance = null;
        }
        
    }

    /**
     * Ejecuta una sentencia SQL a la base de datos.
     * 
     * @param statement Sentencia SQL
     */
    
    public void execute(String statement) {

        try {
            Statement stm = con.createStatement();
            stm.execute(statement);
            stm.close();
        } catch (SQLException ex) {
            System.err.println("Error al ejecutar la sentencia : " + statement + "\nError : " + ex.getMessage());
            throw new Error(ex.getMessage());
        }
    }

    /**
     * Ejecuta una sentencia INSERT pasada como argumento.
     * 
     * @param statement Sentencia SQL, INSERT
     */
    
    public void insert(String statement) {
        execute(statement);
    }
    
    /**
     * Ejecuta una sentencia DELETE pasada como argumento.
     * 
     * @param statement Sentencia SQL, DELETE
     */
    
    public void delete(String statement) {
        execute(statement);
    }

    /**
     * Ejecuta una sentencia UPDATE pasada como argumento.
     * 
     * @param statement Sentencia SQL, UPDATE
     */
    
    public void update(String statement) {
        execute(statement);
    }

    /**
     * Cierra la conexión de la base de datos.
     */
    
    public void close() {
        try {
            con.close();
            instance = null;
        } catch (SQLException e) {
            System.err.println("Error al cerrar la base de datos");
        }
    }

}
