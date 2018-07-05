package DAO;

public class Parama {

	private int questionId;
	private String parama;
	private int rank;

	public Parama(int quesid,String param,int rank) {
		this.questionId=quesid;
		this.parama=param;
		this.rank=rank;
	};

	/**
	 * @return the questionId
	 */
	public int getQuestionId() {
		return questionId;
	}
	/**
	 * @param questionId the questionId to set
	 */
	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}
	/**
	 * @return the parama
	 */
	public String getParama() {
		return parama;
	}
	/**
	 * @param parama the parama to set
	 */
	public void setParama(String parama) {
		this.parama = parama;
	}
	/**
	 * @return the rank
	 */
	public int getRank() {
		return rank;
	}
	/**
	 * @param rank the rank to set
	 */
	public void setRank(int rank) {
		this.rank = rank;
	}

}
