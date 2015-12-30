package qgame.web.templete.login;

import com.vaadin.annotations.VaadinServletConfiguration;
import com.vaadin.server.VaadinServlet;

import javax.servlet.annotation.WebServlet;

@WebServlet(urlPatterns = "/MyPanel", name = "MyPanel", asyncSupported = true)
@VaadinServletConfiguration(ui = Login.class, productionMode = false)
public  class MyUIServlet extends VaadinServlet {
}
