package cn.tedu.ssh.action;

public class QueryAction {
	private String keyword;
	
	public String getKeyword() {
		return keyword;
	}

	/**
	 * �÷������ᱻ�Զ�����
	 * @param keyword
	 */
	public void setKeyword(String keyword) {
		System.out.println("QueryAction.setKeyword() -> "
				+ "arg0=" + keyword);
		this.keyword = keyword;
	}

	public String doAction() {
		System.out.println("QueryAction.doAction() -> "
				+ "keyword=" + keyword);
		return "success";
	}

}
