package DAO;

import java.io.IOException;
import java.util.List;
import java.util.Set;



public interface ContextDao {
	Set<String> findLastParamenters(String userid) throws IOException;

	boolean insert(String userid,String receive,String reply,Set<String> paramenters,int replyid,int type) throws IOException;

	boolean updateStatu(String userid) throws IOException;
}
