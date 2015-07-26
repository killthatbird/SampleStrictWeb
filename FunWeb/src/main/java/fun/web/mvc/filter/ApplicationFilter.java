package fun.web.mvc.filter;

import fun.util.ApplicationContext;
import fun.util.ConstructorUtil;
import fun.web.util.ControllerUtil;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ApplicationFilter extends OncePerRequestFilter {

  private static final Logger logger = Logger.getLogger(OncePerRequestFilter.class
      .getCanonicalName());

  private transient String[] notEnforcedPaths = new String[0];

  public static String getBasicPathName(final String path) {
    String pathb;
    if (path.charAt(0) == '/') {
      pathb = path.replaceFirst("/", "");
    } else {
      pathb = path;
    }

    if (StringUtils.isBlank(pathb)) {
      return "";
    }
    final String[] parts = pathb.split("\\.do");
    pathb = parts[0];
    return pathb;
  }

  @Override
  protected void doFilterInternal(final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain) throws ServletException,
      IOException {

    // Prevent caching
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP
    // 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0);

    ApplicationContext.remove();

    final String path = request.getServletPath() + StringUtils.defaultString(request.getPathInfo());

    if ("/".equals(path)) {
      chain.doFilter(request, response);
      return;
    }

    if (notEnforcedPath(path)) {
      chain.doFilter(request, response);
      return;
    }
    final boolean isAjax = StringUtils.endsWith(ApplicationFilter.getBasicPathName(path), "Ajax");

    if (isAjax) {
      chain.doFilter(request, response);
      return;
    }

    final HttpSession session = request.getSession(false);

    if (session == null) {
      handleNullSession(path, request, response, chain);
      return;
    }

    chain.doFilter(request, response);
  }

  public void handleNoTransaction(final String path, final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain) throws IOException,
      ServletException {
    if (path.charAt(0) == '/') {
      ApplicationContext.setSessionId(ControllerUtil.getSessionId(request));
      chain.doFilter(request, response);
      return;
    }
    response.sendRedirect(request.getContextPath() + "/");
  }

  private void handleNullSession(final String path, final HttpServletRequest request,
      final HttpServletResponse response, final FilterChain chain) throws IOException,
      ServletException {
    if (path.charAt(0) == '/') {
      chain.doFilter(request, response);
      return;
    }
    response.sendRedirect(request.getContextPath() + "/");
  }

  @Override
  protected void initFilterBean() throws ServletException {
    notEnforcedPaths = new String[] { "/images/", "/common/", "/error.do", "/sessionError.do",
        "/js/", "/css/", "/help/", "/staticpages/", "/helptext/" };
  }

  /**
   * Hard to get through PMD without some unusual code.
   * @param path the path
   * @return if its not enforced
   */
  private boolean notEnforcedPath(final String path) {
    boolean retval = false;
    //Close to a No Operation to satisfy PMD DD anomaly.
    if (retval) {
      ConstructorUtil.noOp();
    }

    int inc = 0;
    while (inc < notEnforcedPaths.length) {
      final String notEnforced = notEnforcedPaths[inc];
      if (notEnforced == null) {
        continue;
      }
      if (startsWithPath(path, notEnforced)) {
        //Return Value can only be false here. Strange logic for PMD DD anomaly.
        retval = !retval && true;
        break;
      }
      inc++;
    }

    return retval;
  }

  private boolean startsWithPath(final String first, final String second) {
    boolean retval;
    if (StringUtils.isEmpty(first) || StringUtils.isEmpty(second)) {
      retval = false;
    } else {
      retval = first.startsWith(second);
    }
    return retval;
  }
}
