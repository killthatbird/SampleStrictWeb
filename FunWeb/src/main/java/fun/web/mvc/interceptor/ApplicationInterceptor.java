package fun.web.mvc.interceptor;

import fun.constant.Constant;
import fun.util.ApplicationContext;
import fun.web.util.ControllerUtil;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationInterceptor implements HandlerInterceptor {

  /**
   * Logger for specific class.
   */
  private static final Logger LOGGER = Logger.getLogger(ApplicationInterceptor.class.getName());

  @Override
  public void afterCompletion(final HttpServletRequest arg0, final HttpServletResponse arg1,
      final Object arg2, final Exception arg3) {

    //Save Exceptions

    ApplicationContext.remove();
    // Prevent Double Submission
    final String requestMethod = ControllerUtil.getRequestMethod(arg0);
    if ("POST".equals(requestMethod)) {
      final HttpSession session = arg0.getSession(false);
      ControllerUtil.removeSessionAttribute(session, Constant.POST_TOKEN);
    }

  }

  @Override
  public void postHandle(final HttpServletRequest request, final HttpServletResponse response,
      final Object handler, final ModelAndView modelAndView) {
    //Everything is handled in afterCompletion
  }

  @Override
  public boolean preHandle(final HttpServletRequest request, final HttpServletResponse arg1,
      final Object handler) throws IOException {
    // Prevent Double Submission
    final String requestMethod = ControllerUtil.getRequestMethod(request);
    if ("POST".equals(requestMethod)) {
      final HttpSession session = request.getSession();
      final Object obj = ControllerUtil.getSessionAttribute(session, Constant.POST_TOKEN);
      if (obj == null) {
        ControllerUtil.setSessionAttribute(session, Constant.POST_TOKEN, Constant.POST_TOKEN);
      } else {
        ControllerUtil.invalidate(session);
        arg1.sendRedirect(request.getContextPath() + "/sessionError.do");
        return false;
      }
    }
    return true;
  }
}
