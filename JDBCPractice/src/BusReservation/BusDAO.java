package BusReservation;

import javax.swing.plaf.nimbus.State;
import javax.xml.transform.Result;
import java.sql.*;

public class BusDAO {
    public void displayInfo() throws Exception{
        String query="Select *from bus";
        Connection conn= dbConnection.getConnection();
        Statement st=conn.createStatement();
        ResultSet res=st.executeQuery(query);

        while (res.next()){
            System.out.println("Bus No: "+res.getInt(1));
            if(res.getInt(2)==0){
                System.out.println("AC: no");
            }
            else{
                System.out.println("AC: yes");
            }
            System.out.println("Capacity: "+res.getString(3));
        }
    }
    public int getCapacity(int id ) throws SQLException{
        String query="select capacity from bus where id="+id;
        Connection con=dbConnection.getConnection();
        Statement st=con.createStatement();
        ResultSet rs=st.executeQuery(query);
        rs.next();
        return rs.getInt(1);

    }
}
