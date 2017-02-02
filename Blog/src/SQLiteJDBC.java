import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteJDBC {
	  public static void main( String args[] )
	  {
		  
		  //1，保证SQLite数据库文件的路径首字符为小写，并且路径为unix路径  
          String thisPath = "E:\\Abc\\xxxx.db";  
          String str1=thisPath.substring(0,1).toLowerCase();  //直接将字符串第一个字母小写  
          String str2=thisPath.substring(1,thisPath.length());//截取字符串第二个以后  
          thisPath=str1+str2;  
          //String sql = "jdbc:sqlite://"+ thisPath.replace('\\', '/')+FinalString.DB_NAME;//windows && linux都适用  
          //System.out.println(sql);  
          
	    Connection c = null;
	    try {
	      Class.forName("org.sqlite.JDBC");
	      c = DriverManager.getConnection("jdbc:sqlite:blog2222.db");
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      System.exit(0);
	    }
	    System.out.println("Opened database successfully");
	  }
}
