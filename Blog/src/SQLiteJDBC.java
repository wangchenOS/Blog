import java.sql.Connection;
import java.sql.DriverManager;

public class SQLiteJDBC {
	  public static void main( String args[] )
	  {
		  
		  //1����֤SQLite���ݿ��ļ���·�����ַ�ΪСд������·��Ϊunix·��  
          String thisPath = "E:\\Abc\\xxxx.db";  
          String str1=thisPath.substring(0,1).toLowerCase();  //ֱ�ӽ��ַ�����һ����ĸСд  
          String str2=thisPath.substring(1,thisPath.length());//��ȡ�ַ����ڶ����Ժ�  
          thisPath=str1+str2;  
          //String sql = "jdbc:sqlite://"+ thisPath.replace('\\', '/')+FinalString.DB_NAME;//windows && linux������  
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
