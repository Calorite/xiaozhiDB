package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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
		String sql="SELECT * FROM ai_qanda.parameter_tb where id>?;";
		String[] params= {"0"};
		rs=helper.executeQueryRS(sql, params);
		while(rs.next()) {
			Parama p=new Parama(rs.getInt(1),rs.getInt(2),rs.getString(4),rs.getInt(6));
			list1.add(p);
		}
		return list1;
	}


	public static List<Set<Integer>> getsolutionlist() throws SQLException{
		List<Set<Integer>> list1=new LinkedList<Set<Integer>>();
		ResultSet rs;
		DBService helper=new DBService();
		String sql="SELECT * FROM ai_qanda.paramenter_solution where solutionid>?;";
		String[] params= {"0"};
		rs=helper.executeQueryRS(sql, params);
		while(rs.next()) {
			Set<Integer> set1=new HashSet<Integer>();
			String paramets=rs.getString(1);
			String[] pararray=paramets.split(",");
			for(String paramete:pararray) {
				set1.add(Integer.valueOf(paramete));
			}
			list1.add(set1);
		}
		return list1;
	}

}
