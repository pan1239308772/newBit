package cn.tedu.ssh.action;

import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

public class LoginAction implements SessionAware {
	// �û��ύ�����ݣ�������Ӧ����<input />��ǩ��name
	// ���Ե�ֵ����ͬ�ģ���<input name="username" />����
	// ��Action���������ĳ�Ա����ҲӦ�ý�username
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
		// ��¼��֤
		// �ٶ���ȷ���û��������룺
		// admin / admin888
		// -------------------------------
		// ����equals()����ʱ��
		// "admin".equals(username)
		// username.equals("admin")
		// �ھ��󲿷���û������ģ�
		// ���ǣ�ǿ���Ƽ�ʹ�ã�����.equals(����)
		// ��Ϊ����������Ϊnull������ܵ���NullPointerException
		if ("admin".equals(user.getUsername()) 
				&& "admin888".equals(user.getPassword())) {
			session.put("username", "admin");
			return "success";
		} else {
			// ��¼ʧ�ܣ����ô�����Ϣ��Ȼ����ת������
			setMessage("��¼ʧ�ܣ��û�����������������µ�¼��");
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
