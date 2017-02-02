package com.gubenkoDM.javalevel1.dz.dz2.createAndFill;

import java.sql.*;

/**
 * Created by Nestor on 25.01.2017.
 */
public class DbWorkermain implements BaseConst{


    public static void main(String[] args) throws SQLException {
        DbWorker dbw=new DbWorker("org.sqlite.JDBC","jdbc:sqlite:testsqlite_bd");
        //reg
        dbw.driverReg();
        //get base connect
        Connection connect=dbw.getConnect();

        //создание выражения для работы с базой
        Statement st=dbw.getStatement(connect);

        //создадим таблицу
        int numModStr=dbw.executeUpdate(st,createTableQuery);

        //заполняем таблицу
        PreparedStatement ps=dbw.getPreparedStatement(connect,fillTableQuery);

        //очистка содержимого таблицыв
        st.execute(deleteTableQuery);

        //заполнение содержимого таблицы
        dbw.fillTable(connect,ps,NUMBER_FILL_RECORD);

        ResultSet rs=null;



        //завершение работы с базой(закрытие соединения)
        dbw.close(connect,st,rs);
    }
}
