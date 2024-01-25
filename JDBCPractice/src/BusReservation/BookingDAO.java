package BusReservation;
import java.awt.print.Book;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import  java.util.*;

public class BookingDAO {
    public int getBookingCount(int busNo,Date date) throws SQLException {
        String query="select count(passenger_name) from booking where bus_no=? and travel_date=?";
        Connection conn=dbConnection.getConnection();
        PreparedStatement pst=conn.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(date.getTime());
        pst.setInt(1,busNo);
        pst.setDate(2,sqlDate);
        ResultSet rs=pst.executeQuery();
        rs.next();
        return rs.getInt(1);
    }
    public void addBooking(Booking booking) throws SQLException{
        String query="insert into booking values(?,?,?)";
        Connection conn=dbConnection.getConnection();

        PreparedStatement pst= conn.prepareStatement(query);
        java.sql.Date sqlDate = new java.sql.Date(booking.date.getTime());
        pst.setString(1,booking.passengerName);
        pst.setInt(2,booking.busNo);
        pst.setDate(3,sqlDate);

        pst.executeUpdate();

    }
}
