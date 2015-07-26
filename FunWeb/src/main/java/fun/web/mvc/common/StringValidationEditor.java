package fun.web.mvc.common;

import fun.util.ConstructorUtil;

import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyEditorSupport;
import java.util.Objects;

/**
 * String Validation Editor.
 * @author Open Source
 *
 */
public class StringValidationEditor extends PropertyEditorSupport {

  /**
   * Max Length for parameters.
   */
  private static final int PARAMATER_MAX = 4000;

  /**
   * Default Constructor.
   */
  public StringValidationEditor() {
    super();
    ConstructorUtil.logInstanceCreation(StringValidationEditor.class);
  }

  /**
   * Get Text Value from Object.
   */
  @Override
  public String getAsText() {
    final Object value = getValue();
    return Objects.toString(value);
  }

  /**
   * Set Text Value to object.
   */
  @Override
  public void setAsText(final String text) {

    if (text == null || text.length() == 0) {
      setValue("");
      return;
    }
    final String retVal = text.trim();
    if (StringUtils.length(retVal) > PARAMATER_MAX) {
      StringUtils.substring(retVal, 0, PARAMATER_MAX);
    }
    setValue(retVal);
  }

}
