package fun.web.mvc.form;

public class LoginForm extends BaseForm {

  private static final long serialVersionUID = 1L;

  private String userName;
  private String password;

  public String getPassword() {
    return password;
  }

  public String getUserName() {
    return userName;
  }

  public void setPassword(final String password) {
    this.password = password;
  }

  public void setUserName(final String userName) {
    this.userName = userName;
  }
}
