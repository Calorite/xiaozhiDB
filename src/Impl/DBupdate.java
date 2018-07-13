package Impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import algorithm.ParameterProcess;

public class DBupdate {

	public static Map<Integer,Parama> getparams() throws SQLException{
		Map<Integer,Parama> allparamenters=new HashMap<Integer,Parama>();
		ResultSet rs;
		DBService helper=new DBService();
		String sql="SELECT * FROM ai_qanda.parameter_tb where id>?;";
		String[] params= {"0"};
		rs=helper.executeQueryRS(sql, params);
		while(rs.next()) {
			Parama p=new Parama(rs.getInt(1),rs.getInt(2),rs.getString(4),rs.getInt(6));
			allparamenters.put(rs.getInt(1), p);
		}
		return allparamenters;
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

	public static int getQuestionid(int type,Set<Integer> set1,Map<Integer,Parama> allparamenter) throws SQLException {
		StringBuffer sql=null;
		DBService helper=new DBService();
		if(type==0) {//Œ¢
			sql=new StringBuffer("SELECT id,rank FROM ai_qanda.parameter_tb where ");
		}else {//”L
			sql=new StringBuffer("SELECT id,rank FROM ai_qanda.parameter_cat_tb where ");
		}
		Map<Integer,Integer> map=new HashMap<Integer,Integer>();
		for(int id:set1) {
			Parama paramenter=allparamenter.get(id);
			map.put(id,paramenter.getRank());
		}
		Map<Integer,Integer> soredmap=ParameterProcess.sortByValue(map);
		Map.Entry<Integer,Integer> entry = soredmap.entrySet().iterator().next();
		Integer firstone=entry.getKey();
		if(firstone!=null) {
			Parama paramenter=allparamenter.get(firstone);
			return paramenter.getQuestionId();
		}
		return 0;
	}

	public static String getQustionStr(String id) {
		DBService helper=new DBService();
		String sql="SELECT question FROM ai_qanda.paramenterques_tb where id=?";
		String[] params= {id};
		Object returnlist=helper.executeQuerySingle(sql, params);
		return returnlist.toString();
	}

	public static String getSolutinStr(String id) {
		DBService helper=new DBService();
		String sql="SELECT question FROM ai_qanda.solution_tb where id=?";
		String[] params= {id};
		Object returnlist=helper.executeQuerySingle(sql, params);
		return returnlist.toString();
	}

}
