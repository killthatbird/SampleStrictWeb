package fun.web.mvc.validator;

import fun.web.mvc.form.LoginForm;

import org.apache.commons.lang3.StringUtils;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

public class LoginValidator implements Validator {

  @Override
  public boolean supports(final Class<?> clazz) {
    return LoginForm.class.equals(clazz);
  }

  @Override
  public void validate(final Object target, final Errors errors) {
    if (!supports(target.getClass())) {
      errors.reject("omga", "Total Failure");
    }
    final LoginForm form = (LoginForm) target;
    if (StringUtils.isBlank(form.getPassword())) {
      errors.reject("invalid.password", new Object[] { "User Name" }, "Total Failure");
    }

  }

}
