package Server;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import ForDebug.Parameter;
import Impl.ContextImpl;
import Impl.DBupdate;
import Impl.Parama;
import Impl.ReturnObj;
import algorithm.ParameterProcess;
import algorithm.WordsParameter;

public class Process {
	private static Set<Integer> stopwords=new HashSet<Integer>(187);

	public ReturnObj getNextreturnStr(Set<Parama> parametes,String text) throws SQLException {
		ParameterProcess pp=new ParameterProcess();
		Set<Integer> getedpara=new HashSet<Integer>();
		Map<Set<Integer>, Integer> parameterlist=DBupdate.getsolutionlist();
		for(Parama curpara:parametes) {
			getedpara.add(curpara.getId());
		}
		if(getedpara.size()==1&&stopwords.containsAll(getedpara)) {
			//API
		}else {
			if(getedpara.size()>0) {
				ReturnObj returnobj=new ReturnObj();
				Set<Parameter> parameterset=WordsParameter.getParameterWords(parametes,text);
				List<String> returnnum=pp.returnquesid(parameterlist,parametes);
				if(returnnum==null) {
					Set<Integer> vildparameters=ParameterProcess.getValidparameters(parameterlist,getedpara);
					Set<Parama> newparametes=new HashSet<Parama>();
					for(Parama pama:parametes) {
						for(int id:getedpara) {
							if(pama.getId()==id) {
								newparametes.add(pama);
								break;
							}
						}
					}
					parameterset=WordsParameter.getParameterWords(newparametes,text);
					if(vildparameters.size()==1&&stopwords.containsAll(vildparameters)) {
						//API
					}else {
						List<String> returnlist=pp.returnidbyVildparameters(parameterlist,vildparameters);
						if(returnlist.size()>0) {
							if(returnlist.get(0)=="solutin") {
								returnobj.setId(Integer.valueOf(returnlist.get(1)));
								returnobj.setQuestion(DBupdate.getQustionStr(returnlist.get(1)));
								returnobj.setType(1);
								returnobj.setSet(vildparameters);
								//return new ReturnObj((Integer.valueOf(returnlist.get(1))),(DBupdate.getSolutinStr(returnlist.get(1))),1,vildparameters);
							}else if(returnlist.get(0)=="question") {
								returnobj.setId(Integer.valueOf(returnlist.get(1)));
								returnobj.setQuestion(DBupdate.getQustionStr(returnlist.get(1)));
								returnobj.setType(0);
								returnobj.setSet(vildparameters);
							}
						}else {
							//API
						}
					}
				}else {
					if(returnnum.get(0)=="solutin") {
						returnobj.setId(Integer.valueOf(returnnum.get(1)));
						returnobj.setQuestion(DBupdate.getQustionStr(returnnum.get(1)));
						returnobj.setType(1);
						returnobj.setSet(getedpara);
					}else if(returnnum.get(0)=="question") {
						returnobj.setId(Integer.valueOf(returnnum.get(1)));
						returnobj.setQuestion(DBupdate.getQustionStr(returnnum.get(1)));
						returnobj.setType(1);
						returnobj.setSet(getedpara);
					}
				}
				returnobj.setWords(parameterset);
				return returnobj;
			}else {
				//API
			}
		}
		return null;
	}

	public static void inputsolution(String solutionid,String text,Set<Parama> parametes) throws SQLException {
		long startTime=System.nanoTime();
		Process process=new Process();
		Set<Integer> parameteid=new HashSet<Integer>();
		for(Parama p:parametes) {
			parameteid.add(p.getId());
		}
		//Set<String> lastparameters=ContextImpl.findLastParamenters("testuser");
		ReturnObj returnobj=process.getNextreturnStr(parametes,text);
		//if(lastparameters.size()>0) {

		//}
		System.out.println(returnobj.getQuestion());
		for(Parameter word:returnobj.getWords()) {
			System.out.println("word:"+word.getWord()+"    "+word.getParameter()+"   rank:"+word.getRank());
		}
		long endTime=System.nanoTime();
		System.out.println("运行时间： "+(endTime-startTime)/(long)1000000+"微秒");

	}

}
