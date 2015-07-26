package fun.web.mvc.common;

import fun.util.ConstructorUtil;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.web.bind.support.WebBindingInitializer;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Binding Initializer.
 * @author Open Source
 *
 */
public class BindingInitializer implements WebBindingInitializer {

  /**
   * Default Constructor.
   */
  public BindingInitializer() {
    super();
    ConstructorUtil.logInstanceCreation(BindingInitializer.class);
  }

  /**
   * Initialization binder.
   */
  @Override
  @org.springframework.web.bind.annotation.InitBinder
  public void initBinder(final org.springframework.web.bind.WebDataBinder binder,
      final org.springframework.web.context.request.WebRequest request) {
    final SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.ENGLISH);
    final CustomDateEditor editor = new CustomDateEditor(dateFormat, true);
    binder.registerCustomEditor(Date.class, editor);

    final StringValidationEditor stringEditor = new StringValidationEditor();
    binder.registerCustomEditor(String.class, stringEditor);

    final CustomErrorProcessor errorProcessor = new CustomErrorProcessor();
    binder.setBindingErrorProcessor(errorProcessor);

  }

}
