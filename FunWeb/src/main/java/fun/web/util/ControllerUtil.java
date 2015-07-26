package fun.web.util;

import fun.constant.Constant;

import org.apache.commons.lang3.StringUtils;

import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public final class ControllerUtil {

  private static final String NOT_APPLICABLE = "NotApplicable";

  private static final Logger LOGGER = Logger.getLogger(ControllerUtil.class.getName());

  private ControllerUtil() {

  }

  public static int getId(final Object idObject) {
    if (!(idObject instanceof Integer)) {
      return Constant.INVALID;
    }
    final int userId = ((Integer) idObject).intValue();
    if (userId <= 0) {
      return Constant.INVALID;
    }
    return userId;
  }

  public static int getIdFromRequestParameter(final HttpServletRequest request, final String name) {
    if (request == null || StringUtils.isBlank(name)) {
      return Constant.INVALID;
    }
    final String requestParam = request.getParameter(name);
    if (StringUtils.isBlank(requestParam)) {
      return Constant.INVALID;
    }
    if (org.apache.commons.lang3.math.NumberUtils.isDigits(requestParam)) {
      return org.apache.commons.lang3.math.NumberUtils.toInt(requestParam, Constant.INVALID);
    }
    return Constant.INVALID;
  }

  public static String getRequestMethod(final HttpServletRequest request) {
    if (request == null) {
      return StringUtils.EMPTY;
    }
    final String retval = StringUtils.trimToEmpty(request.getMethod());
    return retval;
  }

  public static Object getSessionAttribute(final HttpServletRequest request, final String name) {
    if (StringUtils.isBlank(name) || request == null) {
      return null;
    }
    final HttpSession session = request.getSession(false);
    final Object retval = getSessionAttribute(session, name);
    return retval;
  }

  public static Object getSessionAttribute(final HttpSession session, final String name) {
    if (session == null || StringUtils.isBlank(name)) {
      return null;
    }
    final Object retval = session.getAttribute(name);
    return retval;
  }

  public static String getSessionId(final HttpServletRequest request) {
    if (request == null) {
      return NOT_APPLICABLE;
    }
    final HttpSession session = request.getSession(false);

    final String sessionId = getSessionId(session);
    return sessionId;
  }

  public static String getSessionId(final HttpSession session) {
    if (session == null) {
      return NOT_APPLICABLE;
    }
    String sessionId = session.getId();
    sessionId = StringUtils.trimToEmpty(sessionId);
    return sessionId;

  }

  public static void invalidate(final HttpSession session) {
    if (session == null) {
      return;
    }
    try {
      session.invalidate();
    } catch (final IllegalStateException exception) {
      LOGGER.warning("Invalidate called on an invalidated session: " + session.getId());
    }
  }

  public static void removeSessionAttribute(final HttpServletRequest request, final String name) {
    if (StringUtils.isBlank(name) || request == null) {
      return;
    }
    final HttpSession session = request.getSession(false);
    removeSessionAttribute(session, name);
  }

  public static void removeSessionAttribute(final HttpSession session, final String name) {
    if (session == null || StringUtils.isBlank(name)) {
      return;
    }
    session.removeAttribute(name);
  }

  public static void setSessionAttribute(final HttpServletRequest request, final String name,
      final Object value) {
    if (StringUtils.isBlank(name) || request == null) {
      return;
    }
    final HttpSession session = request.getSession(false);
    setSessionAttribute(session, name, value);
  }

  public static void setSessionAttribute(final HttpSession session, final String name,
      final Object value) {
    if (session == null || StringUtils.isBlank(name)) {
      return;
    }
    session.setAttribute(name, value);
  }

}
