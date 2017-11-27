package cn.tedu.ssh.action;

public class HomeAction {
	/**
	 * 必须是public String的方法，无参数，方法名没有要求
	 * @return 随意字符串
	 */
	public String doAction() {
		System.out.println("HomeAction.doAction()");
		return "ok";
	}
	
}
