package fun.web.mvc.controller;

import fun.util.ConstructorUtil;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Home Page.
 * @author I have no name
 *
 */
@Controller
public class WelcomeController {
  /**
   * Constructor.
   */
  public WelcomeController() {
    super();
    ConstructorUtil.logInstanceCreation(WelcomeController.class);
  }

  /**
   * Handle Get requests.
   * @return view name
   */
  @RequestMapping("/")
  public String doGet() {
    return "welcome";
  }
}
