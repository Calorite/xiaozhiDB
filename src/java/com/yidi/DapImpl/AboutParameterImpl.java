package com.yidi.DapImpl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import com.yidi.Interface.AboutParametersDAO;
import com.yidi.entity.Parameter;


public class AboutParameterImpl implements AboutParametersDAO {

	@Override
	public Map<Integer, Parameter> getparams() throws SQLException {
		Map<Integer,Parameter> allparamenters=new HashMap<>();
		ResultSet rs;
		DBService helper=new DBService();
		String sql="SELECT * FROM ai_qanda.parameter_tb;";
		rs=helper.executeQueryRS(sql, null);
		while(rs.next()) {
			Parameter p=new Parameter(rs.getInt(1),rs.getInt(2),rs.getString(4),rs.getInt(6));
			allparamenters.put(rs.getInt(1), p);
		}
		return allparamenters;
	}

	@Override
	public boolean checkandpara(String para,String text) {
		if(para.contains("&")) {
			String[] pararray=para.split("&");
			int count=0;
			for(String parameter:pararray) {
				if(parameter.contains("!")){
					if(text.contains(parameter)) {

					}else {
						count++;
					}
				}
				else if(text.contains(parameter)) {
					count++;
				}
			}
			if(count==pararray.length) {
				return true;
			}
		}else {
			if(text.contains(para)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String checkParameterLine(String parameline,String text) {
		if(parameline.contains("/")) {//有多个参
			String[] paramelist=parameline.split("/");
			for(String parameter:paramelist) {
				if(checkandpara(parameter,text)) {
					return parameter;
				}
			}
		}else {
			if(checkandpara(parameline,text)) {
				return parameline;
			}
		}
		return null;
	}

}
