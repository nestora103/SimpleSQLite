package com.gubenkoDM.javalevel1.dz.dz2.createAndFill;

/**
 * Created by Nestor on 28.01.2017.
 */
public interface BaseConst {
    String createTableQuery=
            "CREATE TABLE IF NOT EXISTS Product "+
                    "(id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"+
                    "prodid  STRING  NOT NULL,"+
                    "title STRING NOT NULL,"+
                    "cost INTEGER NOT NULL"+");";
    String fillTableQuery=
            "INSERT INTO Product(prodid,title,cost)VALUES(?,?,?);";

    String deleteTableQuery=
            "DELETE FROM Product;";

    int NUMBER_FILL_RECORD=10000;
}
