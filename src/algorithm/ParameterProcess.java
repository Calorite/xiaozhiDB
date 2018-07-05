package algorithm;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import DAO.DBupdate;
import DAO.Parama;

public class ParameterProcess {
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


	public boolean checkParamete(String parameline,String text) {
		if(parameline.contains("/")) {//有多个参
			String[] paramelist=parameline.split("/");
			for(String parameter:paramelist) {
				if(checkandpara(parameter,text)) {
					return true;
				}else {
					return false;
				}
			}
		}else {
			if(checkandpara(parameline,text)) {
				return true;
			}else {
				return false;
			}
		}
		return false;
	}



	public Set<Parama> getParemetes(String text) throws SQLException{
		List<Parama> parameterlist=DBupdate.getparams();
		Set<Parama> targetpara=new HashSet<Parama>();
		for(Parama curtparamente:parameterlist) {
			if(checkParamete(curtparamente.getParama(),text)) {
				targetpara.add(curtparamente);
			}
		}
		return targetpara;
	}

	public boolean compareSet(Set<Integer> set1,Set<Integer> set2) {
		if(set1.containsAll(set2)) {
			return true;
		}else {
			Set<Integer> set3 = new HashSet<Integer>();
			set3.addAll(set1);
			set3.removeAll(set2);
			Set<Integer> set4 = new HashSet<Integer>();
			set4.addAll(set2);
			set4.removeAll(set3);
			compareSet(set1,set2);
		}
		return false;
	}


	public int returnquesid(Set<Parama> parametes) throws SQLException {
		List<Set<Integer>> parameterlist=DBupdate.getsolutionlist();
	}


	public static void main(String[] args) throws SQLException {
		ParameterProcess pp=new ParameterProcess();
		Set<Parama> parametes=pp.getParemetes("狗狗不吃狗粮怎办");

	}
}
