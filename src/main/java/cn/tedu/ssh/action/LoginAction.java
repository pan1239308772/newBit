package cn.tedu.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class LoginAction implements SessionAware {
	// 用户提交的数据，该名称应该与<input />标签的name
	// 属性的值是相同的，即<input name="username" />，则
	// 在Action类中声明的成员变量也应该叫username
	// private String username;
	// private String password;
	//
	// public String getUsername() {
	// return username;
	// }
	//
	// public void setUsername(String username) {
	// System.out.println("LoginAction.setUsername() -> arg0=" + username);
	// this.username = username;
	// }
	//
	// public String getPassword() {
	// return password;
	// }
	//
	// public void setPassword(String password) {
	// System.out.println("LoginAction.setPassword() -> arg0=" + password);
	// this.password = password;
	// }

	private User user;
	private Map<String, Object> session;

	public Map<String, Object> getSession() {
		return session;
	}

	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String execute() {
		// 登录验证
		// 假定正确的用户名和密码：
		// admin / admin888
		// -------------------------------
		// 调用equals()方法时：
		// "admin".equals(username)
		// username.equals("admin")
		// 在绝大部分是没有区别的！
		// 但是，强烈推荐使用：常量.equals(变量)
		// 因为，变量可能为null，则可能导致NullPointerException
		if ("admin".equals(user.getUsername()) 
				&& "admin888".equals(user.getPassword())) {
			session.put("username", "admin");
			return "success";
		} else {
			// 登录失败，设置错误信息，然后再转发请求
			setMessage("登录失败！用户名或密码错误！请重新登录！");
			return "error";
		}
	}

	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}
