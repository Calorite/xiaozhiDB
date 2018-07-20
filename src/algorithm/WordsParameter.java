package algorithm;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.huaban.analysis.jieba.JiebaSegmenter;
import com.huaban.analysis.jieba.JiebaSegmenter.SegMode;
import com.huaban.analysis.jieba.SegToken;

import ForDebug.Parameter;
import Impl.Parama;

public class WordsParameter {

	public static Set<Parameter> getParameterWords(Set<Parama> getedparama,String text){
		ParameterProcess pprocess=new ParameterProcess();
		Set<Parameter> set=new HashSet<Parameter>();
		JiebaSegmenter segmenter = new JiebaSegmenter();
		List<SegToken> list=segmenter.process(text, SegMode.INDEX);
		for(SegToken token:list) {
			for(Parama parameter:getedparama) {
				if(pprocess.checkParamete(parameter.getParama(),token.word)!=null) {
					Parameter pa=new Parameter();
					pa.setWord(token.word);
					pa.setParameter(parameter.getParama());
					pa.setParameterid(parameter.getId());
					pa.setQuestionid(parameter.getQuestionId());
					pa.setRank(parameter.getRank());
					set.add(pa);
				}
			}

		}
		return set;

	}
}
