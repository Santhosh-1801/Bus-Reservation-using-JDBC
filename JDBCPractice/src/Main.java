

import java.sql.*;
public class Main {
    public static void main(String[] args) throws Exception {
      commitBatchDemo();
    }

    public static void readRecords() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        String query = "select *from employee";
        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery(query);

        while (rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getString(3));

        }}
        public static void insertRecords() throws Exception{
            String url = "jdbc:mysql://localhost:5000/JDBC";
            String username = "root";
            String password = "123456789";
            Connection conn = DriverManager.getConnection(url, username, password);
            System.out.println(conn);
            int id=2000;
            String name="Krishna";
            int salary=29000;
            String query = "insert into employee values(" +id+",'"+name+"',"+salary+");";
            Statement st = conn.createStatement();
            int rows= st.executeUpdate(query);

            System.out.println("Number of rows affected: "+rows);
}
    public static void insertRecordsPST() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        int id=202;
        String name="Madhavan";
        int salary=219000;
        String query = "insert into employee values(?,?,?);";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1,id);
        pst.setString(2,name);
        pst.setInt(3,salary);
        int rows=pst.executeUpdate();
        System.out.println("The number of rows affected: "+rows);
        conn.close();
    }
    public static void deleteRecords() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        int emp_id=202;
        String query = "delete from employee where emp_id=?;";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1,emp_id);
        int rows=pst.executeUpdate();
        System.out.println("The number of rows affected: "+rows);
        conn.close();
    }
    public static void updateRecords() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        int emp_id=2;
        String query = "update employee set salary=100000 where emp_id=?;";
        PreparedStatement pst=conn.prepareStatement(query);
        pst.setInt(1,emp_id);
        int rows=pst.executeUpdate();
        System.out.println("The number of rows affected: "+rows);
        conn.close();
    }
    public static void callProcedurals() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        CallableStatement cst=conn.prepareCall("{call GetEmp()}");
        ResultSet rs=cst.executeQuery();

        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getString(3));
        }
        conn.close();
    }
    public static void callProcedurals2() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        int id=2;
        CallableStatement cst=conn.prepareCall("{call GetEmpById(?)}");
        cst.setInt(1,id);
        ResultSet rs=cst.executeQuery();

        while(rs.next()) {
            System.out.println("Id is " + rs.getInt(1));
            System.out.println("Name is " + rs.getString(2));
            System.out.println("Salary is " + rs.getString(3));
        }
        conn.close();
    }
    public static void callProcedurals3() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        Connection conn = DriverManager.getConnection(url, username, password);
        System.out.println(conn);
        int id=2;
        CallableStatement cst=conn.prepareCall("{call GetEmpNameById(?,?)}");
        cst.setInt(1,id);
        cst.registerOutParameter(2,Types.VARCHAR);
        cst.executeUpdate();

        System.out.println(cst.getString(2));

        conn.close();
    }
    public static void commitDemo() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        String query1="update employee set salary=1000000 where emp_id=1";
        String query2="update employee set salary=3000000 where emp_id=2";
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        Statement st= conn.createStatement();
        int rows1=st.executeUpdate(query1);
        System.out.println("Rows affected "+rows1);
        int rows2=st.executeUpdate(query2);
        System.out.println("Rows affected "+rows2);
        if(rows1>0 && rows2>0){
            conn.commit();
        }

        conn.close();
    }
    public static void commitBatchDemo() throws Exception{
        String url = "jdbc:mysql://localhost:5000/JDBC";
        String username = "root";
        String password = "123456789";
        String query1="update employee set salary=9000000 where emp_id=1";
        String query2="update employee set salary=9000000 where emp_id=2";
        String query3="update employee set salary=9000000 where emp_id=3";
        String query4="update employee set salary=9000000 where emp_id=5";
        Connection conn = DriverManager.getConnection(url, username, password);
        conn.setAutoCommit(false);
        Statement st= conn.createStatement();
        st.addBatch(query1);
        st.addBatch(query2);
        st.addBatch(query3);
        st.addBatch(query4);
        int res[]=st.executeBatch();
        for(int i:res){
            if(i>0){
                continue;
            }
            else{
                break;
            }
        }
        conn.commit();
        conn.close();
    }
}
