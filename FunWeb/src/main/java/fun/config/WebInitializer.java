package fun.config;

import fun.util.ConstructorUtil;
import fun.web.mvc.filter.ApplicationFilter;

import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;

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

  private void addDefaultMappingUrlPattern(final javax.servlet.FilterRegistration.Dynamic filter) {
    filter.addMappingForUrlPatterns(null, false, "/*");
  }

  /**
   * Runs When Server Starts.
   */
  @Override
  public void onStartup(final ServletContext servletContext) throws ServletException {
    final AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
    ctx.register(ServletConfig.class);
    ctx.setServletContext(servletContext);
    servletContext.addListener(InitServlet.class);

    servletContext.addListener(new ContextLoaderListener(ctx));
    final javax.servlet.FilterRegistration.Dynamic filter = servletContext.addFilter(
        "ApplicationFilter", ApplicationFilter.class);
    addDefaultMappingUrlPattern(filter);
    final javax.servlet.ServletRegistration.Dynamic servlet = servletContext.addServlet(
        "dispatcher", new DispatcherServlet(ctx));
    servlet.addMapping("/");
    servlet.setLoadOnStartup(1);
  }

}
