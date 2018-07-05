package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DBupdate {
	public static boolean updateImg(int id,String Path) {
		DBService helper=new DBService();
		String sql="update user set ImgPath=? where Id=?";
		String[] params= {Path,String.valueOf(id)};
		int retunlines=helper.executeUpdate(sql, params);
		if(retunlines==1) {
			return true;
		}
		return false;
	}

	public static List<Parama> getparams() throws SQLException{
		List<Parama> list1=new LinkedList<Parama>();
		ResultSet rs;
		DBService helper=new DBService();
		String sql="SELECT * FROM ai_qanda.parameters where id>?;";
		String[] params= {"0"};
		rs=helper.executeQueryRS(sql, params);
		while(rs.next()) {
			Parama p=new Parama(rs.getInt(2),rs.getString(4),rs.getInt(6));
			list1.add(p);
		}
		return list1;
	}
}
