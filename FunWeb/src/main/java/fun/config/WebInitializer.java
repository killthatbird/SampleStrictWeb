package fun.config;

import fun.util.ConstructorUtil;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;

/**
 * Web Initializer.
 *
 * @author None
 *
 */
public final class WebInitializer implements org.springframework.web.WebApplicationInitializer {

  /**
   * Constructor.
   */
  public WebInitializer() {
    super();
    ConstructorUtil.logInstanceCreation(WebInitializer.class);
  }

  /**
   * Runs When Server Starts.
   */
  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(ServletConfig.class);
    ctx.setServletContext(servletContext);
    servletContext.addListener(new ContextLoaderListener(ctx));
    final Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
  }

}
