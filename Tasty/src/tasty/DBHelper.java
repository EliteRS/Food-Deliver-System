/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tasty;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Russell Sean H. Gonzalve
 */
public class DBHelper {
    Connection con = null;
    Statement stmt = null;
        
    public Connection getConnection(){
        return con;
    }
    
    public ResultSet signIn(String username, String password) {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tbladmin where admin_username like '"+username+"' and admin_password like '"+password+"'";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void connectDB() throws Exception{
        con = DriverManager.getConnection("jdbc:derby://localhost:1527/dbTastyFoodOrderingSystem","russell","russell");
        System.out.println("Connected to the database");
    }
    
    public boolean insertRecord(int idnum, String fname, String lname, String pnumber, String address, String username, String password){
        boolean flag = false;
        
        try {
            stmt = con.createStatement();
            String sql = "Insert into tblcustomer values ("+idnum+",'"+fname+"','"+lname+"','"+pnumber+"','"+address+"','"+username+"','"+password+"')";
            if(stmt.executeUpdate(sql)==1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public boolean insertAddtoCart(int orderid, String fname, String fcategory, double fprice, int fquantity){
        boolean flag = false;
        
        try {
            stmt = con.createStatement();
            String sql = "Insert into tblcart values ("+orderid+",'"+fname+"','"+fcategory+"',"+fprice+","+fquantity+")";
            if(stmt.executeUpdate(sql)==1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public boolean addToCart(int order_id, int product_id, String product_name, double price, int quantity, double total_price) {
        boolean flag = true;  
        try {
            stmt = con.createStatement();
            String sql = "Insert into tblorder values (DEFAULT, "+order_id+", "+product_id+", '"+product_name+"', "+price+", "+quantity+", "+total_price+")";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
        public ResultSet displayCart(int order_id) {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblorder where order_id = "+order_id+"";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public boolean updateCart(int order_details_id, int order_id, int product_id, String product_name, double price, int quantity, double total_price) {
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Update tblorder set order_id = "+order_id+", product_id = "+product_id+", product_name = '"+product_name+"', price = "+price+", quantity = "+quantity+", total_price = "+total_price+" where order_details_id = "+order_details_id+"";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public boolean deleteFromCart(int order_details_id) {
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Delete from tblorder where order_details_id = "+order_details_id+"";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public ResultSet selectFromTblOrder() {
        ResultSet rs = null;
        try {
            String sql = "Select * from tblcheckoutdetails";
            stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    //checkout
    public boolean insertOrder(int order_id, String full_name, String mobile_number, String delivery_address, double grand_total) {
        boolean flag = true;  
        try {
            stmt = con.createStatement();
            String sql = "Insert into tblcheckoutdetails values ("+order_id+", '"+full_name+"', '"+mobile_number+"', '"+delivery_address+"', "+grand_total+")";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }

    
    public boolean addMenu(int product_id, String product_name, String category, double price) {
        boolean flag = true;  
        try {
            stmt = con.createStatement();
            String sql = "Insert into tblorderlist values ("+product_id+", '"+product_name+"', '"+category+"', "+price+")";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
       public boolean insertCart(String foodname,int quantity, Double price, String name, String address){
        boolean flag = false;
        
        try {
            stmt = con.createStatement();
            String sql2 = "Insert into tblorderlist values ('"+foodname+"',"+quantity+"',"+price+",'"+name+"','"+address+"')";
            if(stmt.executeUpdate(sql2)==1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public ResultSet displayAllRecords(){
           ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblcustomer";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public boolean updateOrderMenu(int prod_id, String prod_name, String category, Double price) {
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Update tblorderlist set food_name = '"+prod_name+"', food_category = '"+category+"',"
                    + " food_price = "+price+" where food_id = "+prod_id+"";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
    
    public boolean deleteOrderMenu(int prod_id) {
        boolean flag = false;
        try {
            stmt = con.createStatement();
            String sql = "Delete from tblorderlist where food_id = "+prod_id+"";
            if(stmt.executeUpdate(sql) == 1)
                flag = true;
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flag;
    }
        
    public ResultSet displayAllMenu() {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblOrderList";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
        
    public ResultSet displayByName(String fname) {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblOrderList where product_name = '"+fname+"'";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public ResultSet displayByCategory(String category) {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblOrderList where food_category = '"+category+"'";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    
    public ResultSet displayByLastname(String lname){
           ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblcustomer where customer_last_name ='"+lname+"'";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
        public ResultSet displayByFoodname(String fname){
           ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblorderlist where food_name ='"+fname+"'";
            rs = stmt.executeQuery(sql);
            
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
 public void deleteOrder(int order_id) {
        try {
            stmt = con.createStatement();
            String sql = "Delete from tblcheckoutdetails where order_id = "+order_id+"";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void deleteCartByOrderID(int order_id) {
        try {
            stmt = con.createStatement();
            String sql = "Delete from tblorder where order_id = "+order_id+"";
            stmt.executeUpdate(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
        public ResultSet displayTblOrder() {
        ResultSet rs = null;
        try {
            stmt = con.createStatement();
            String sql = "Select * from tblcheckoutdetails";
            rs = stmt.executeQuery(sql);
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs;
    }
    
    public void disconnectDB(){
        if (con != null) 
            try {
                con.close();
        } catch (SQLException ex) {
            Logger.getLogger(DBHelper.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public ResultSet executeQuery(String statement) throws SQLException{
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(statement);
        return rs;
    }

    
}
