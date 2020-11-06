package com.megalom.tutorial.postgresql;
import java.sql.*;
public class HelloSQL {
    public void testSQL()throws SQLException{
        Connection conn = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/appdb","app","1234");
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(
                "select * from test");

        while ((rs.next())
        ) {
            System.out.print(rs.getString("m_id"));
            System.out.print(" | ");
            System.out.print(rs.getString("name"));
            System.out.print("\n");
        }
        rs.close();
        st.close();
        conn.close();
    }
}
