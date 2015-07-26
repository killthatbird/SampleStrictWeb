package fun.web.mvc.form;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;

import java.io.Serializable;
import java.util.List;

public class BaseForm implements Serializable {

  private static final long serialVersionUID = 1L;

  @JsonIgnore
  private Errors errors;
  private String message;

  public List<ObjectError> getAllErrors() {
    if (errors == null) {
      return null;
    }
    return errors.getAllErrors();
  }

  public String getMessage() {
    return message;
  }

  public void setErrors(final Errors errors) {
    this.errors = errors;
  }

  public void setMessage(final String message) {
    this.message = message;
  }
}
