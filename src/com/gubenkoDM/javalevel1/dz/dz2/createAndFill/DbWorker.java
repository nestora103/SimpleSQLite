package com.gubenkoDM.javalevel1.dz.dz2.createAndFill;

import java.sql.*;

/**
 * Created by Nestor on 28.01.2017.
 */
public class DbWorker {

    private String driver;
    private String base;

    public DbWorker(String driver,String base) {
        this.driver=driver;
        this.base=base;
    }

    protected void driverReg(){
        //регистрация драйвера базы данных
        try {
            Class.forName(driver);
        }catch (ClassNotFoundException e){
            System.out.println("Не удалось зарегистрировать драйвер базы данных!");
            e.printStackTrace();
        };
    }

    protected Connection getConnect(){
        try {
            return DriverManager.getConnection(base);
        } catch (SQLException e) {
            System.out.println("При попытке подключиться к БД произошла ошибка!");
            e.printStackTrace();
        };
        return null;
    }

    protected Statement getStatement(Connection connect){
        try {
            return connect.createStatement();
        } catch (SQLException e) {
            System.out.println("Не удалось создать внутреннюю структуру для работы с базой!");
            e.printStackTrace();
        }
        return null;
    }

    protected PreparedStatement getPreparedStatement(Connection connect,String ps){
        try {
            return connect.prepareStatement(ps);
        } catch (SQLException e) {
            System.out.println("Не удалось создать внутреннюю структуру для работы с базой!");
            e.printStackTrace();
        }
        return null;
    }


    protected int executeUpdate(Statement st, String query) {
        return 0;
    }




    protected void close(Connection connect, Statement st, ResultSet rs){
        try {
            //rs.close();
            st.close();
            connect.close();
        } catch (SQLException e) {
            System.out.println("Не удалось корректно завершить работы с БД!");
            e.printStackTrace();
        }
    }


    public void fillTable(Connection connect,PreparedStatement ps, int numberFillRecord) {

        try {
            connect.setAutoCommit(false);
        } catch (SQLException e) {
            System.out.println("Не вышло отключить автокиммит");
            e.printStackTrace();
        }

        for (int i = 1,j=10; i <= numberFillRecord; i++,j+=10) {
            try {
                ps.setString(1,"id_товара"+i);
                ps.setString(2,"товар"+i);
                ps.setInt(3,j);
                ps.addBatch();
            } catch (SQLException e) {
                System.out.println("При заполнении запросного пакета произошла ошибка!");
                e.printStackTrace();
            }
        }

        try {
            ps.executeBatch();
        } catch (SQLException e) {
            System.out.println("При выполнении запросного пакета произошла ошибка!");
            e.printStackTrace();
        }

        try {
            connect.setAutoCommit(true);
        } catch (SQLException e) {
            System.out.println("Не вышло включить автокиммит");
            e.printStackTrace();
        }

    }


}
