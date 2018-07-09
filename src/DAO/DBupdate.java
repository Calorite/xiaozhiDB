package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
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


	public static Map<Set<Integer>,Integer> getsolutionlist() throws SQLException{
		Map<Set<Integer>,Integer> list1=new HashMap<Set<Integer>,Integer>();
		ResultSet rs;
		DBService helper=new DBService();
		String sql="SELECT * FROM ai_qanda.paramenter_solution where solutionid>?;";
		String[] params= {"0"};
		rs=helper.executeQueryRS(sql, params);
		while(rs.next()) {
			Set<Integer> set1=new HashSet<Integer>();
			String paramets=rs.getString(1);
			String[] pararray=paramets.split(",");
			Integer returnid=rs.getInt(2);
			for(String paramete:pararray) {
				set1.add(Integer.valueOf(paramete));
			}
			list1.put(set1, returnid);
		}
		return list1;
	}

	public static int getQuestionid(int type,Set<Integer> set1) {
		String sql="";
		int maxrank=1000;
		DBService helper=new DBService();
		int maxid=100000;
		for(int id:set1) {
			if(type==0) {//Œ¢
				sql="SELECT rank FROM ai_qanda.parameter_tb where id=?";
			}else {//”L
				sql="SELECT rank FROM ai_qanda.parameter_cat_tb where id=?";
			}
			String[] params= {String.valueOf(id)};
			Object returnlist=helper.executeQuerySingle(sql, params);
			try{
				int rank=(int)returnlist;
				if((int)rank<maxrank) {
					maxrank=rank;
					maxid=id;
				}
			}catch (Exception e){
				e.printStackTrace();
			}
		}
		if(maxid!=100000) {
			String getqusidsql="SELECT quesid FROM ai_qanda.parameter_tb where id=?";
			String [] paramater= {String.valueOf(maxid)};
			Object queidobj=helper.executeQuerySingle(getqusidsql, paramater);
			return (int)queidobj;
		}
		return 0;
	}




}
