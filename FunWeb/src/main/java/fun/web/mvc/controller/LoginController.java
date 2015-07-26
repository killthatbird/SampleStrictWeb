package fun.web.mvc.controller;

import fun.web.mvc.form.LoginForm;
import fun.web.mvc.validator.LoginValidator;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
  private final Validator validator = new LoginValidator();

  @RequestMapping(value = "/login/loginAjax.do", method = RequestMethod.POST)
  @ResponseStatus(HttpStatus.OK)
  public LoginForm doPost(@RequestBody final LoginForm form, final BindingResult errors) {
    form.setErrors(errors);
    validator.validate(form, errors);
    return form;
  }
}
