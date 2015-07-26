package fun.web.mvc.common;

import fun.util.ConstructorUtil;

import org.springframework.beans.PropertyAccessException;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingErrorProcessor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

/**
 * Custom Error Processor.
 * @author Open source
 *
 */
public class CustomErrorProcessor implements BindingErrorProcessor {

  /**
   * Error code that a missing field error (i.e. a required field not found in
   * the list of property values) will be registered with: "required".
   */
  public static final String MISSING_FIELD = "required";

  /**
   * Default Constructor.
   */
  public CustomErrorProcessor() {
    super();
    ConstructorUtil.logInstanceCreation(CustomErrorProcessor.class);
  }

  /**
   * Arguments for bind error.
   * @param name name of object
   * @param field name of field
   * @return messages
   */
  protected Object[] getArgumentsForBindError(final String name, final String field) {
    final String[] codes = new String[] { name + Errors.NESTED_PATH_SEPARATOR + field, field };
    return new Object[] { new DefaultMessageSourceResolvable(codes, field) };
  }

  /**
   * Missing field.
   */
  @Override
  public void processMissingFieldError(final String missField, final BindingResult bindingResult) {
    // Create field error with code "required".
    final String fixedField = bindingResult.getNestedPath() + missField;
    final String[] codes = bindingResult.resolveMessageCodes(CustomErrorProcessor.MISSING_FIELD,
        missField);
    final Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), fixedField);
    bindingResult.addError(new FieldError(bindingResult.getObjectName(), fixedField, "", true,
        codes, arguments, "Field '" + fixedField + "' is required"));
  }

  /**
   * Property Access Exception.
   */
  @Override
  public void processPropertyAccessException(final PropertyAccessException exception,
      final BindingResult bindingResult) {
    final String field = exception.getPropertyName();
    final String[] codes = bindingResult.resolveMessageCodes(exception.getErrorCode(), field);
    final Object[] arguments = getArgumentsForBindError(bindingResult.getObjectName(), field);
    Object rejectedValue = exception.getValue();
    if (rejectedValue != null && ObjectUtils.isArray(rejectedValue)) {
      rejectedValue = StringUtils.arrayToCommaDelimitedString(ObjectUtils
          .toObjectArray(rejectedValue));
    }
    bindingResult.addError(new FieldError(bindingResult.getObjectName(), field, rejectedValue,
        true, codes, arguments, "Field " + field + " is Invalid."));
  }
}
