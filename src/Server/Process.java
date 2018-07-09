package Server;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import DAO.DBupdate;
import DAO.Parama;
import algorithm.ParameterProcess;

public class Process {
	private static Set<Integer> stopwords=new HashSet<Integer>(187);

	public String getNextreturnStr(String text) throws SQLException {
		ParameterProcess pp=new ParameterProcess();
		Set<Parama> parametes=pp.getParemetes(text);
		Set<Integer> getedpara=new HashSet<Integer>();
		Map<Set<Integer>, Integer> parameterlist=DBupdate.getsolutionlist();
		for(Parama curpara:parametes) {
			getedpara.add(curpara.getId());
		}
		if(getedpara.size()==1&&stopwords.containsAll(getedpara)) {
			//API
		}else {
			if(parametes.size()>0) {
				List<String> returnnum=pp.returnquesid(parameterlist,parametes);
				if(returnnum==null) {
					Set<Integer> vildparameters=ParameterProcess.getValidparameters(parameterlist,getedpara);
					if(vildparameters.size()==1&&stopwords.containsAll(vildparameters)) {
						//API
					}else {
						List<String> returnlist=pp.returnidbyVildparameters(parameterlist,vildparameters);
						if(returnlist.size()>0) {
							if(returnlist.get(0)=="solutin") {
								return (DBupdate.getSolutinStr(returnlist.get(1)));
							}else if(returnlist.get(0)=="question") {
								return (DBupdate.getQustionStr(returnlist.get(1)));
							}
						}else {
							//API
						}
					}
				}else {
					if(returnnum.get(0)=="solutin") {
						return (DBupdate.getSolutinStr(returnnum.get(1)));
					}else if(returnnum.get(0)=="question") {
						return (DBupdate.getQustionStr(returnnum.get(1)));
					}
				}
			}else {
				//API
			}
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		Process process=new Process();
		System.out.println(process.getNextreturnStr("狗狗在家乱叫"));
	}
}
