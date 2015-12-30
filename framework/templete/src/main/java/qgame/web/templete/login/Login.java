package qgame.web.templete.login;

/**
 * Created by DongLei on 2015/12/30.
 */

import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.*;

/**
 *
 */
@Theme("tests-valo")
public class Login extends UI {

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        final HorizontalLayout layout = new HorizontalLayout();
//        layout.setWidth("300px");

        // BEGIN-EXAMPLE: layout.panel.complex
        Panel panel = new Panel("登录"+vaadinRequest.getParameter("a"));
        panel.addStyleName("mypanelexample");
        panel.setSizeUndefined(); // Shrink to fit content
        layout.addComponent(panel);
//        layout.setSizeUndefined();
        layout.setSizeFull();
//        layout.setComponentAlignment(panel,Alignment.MIDDLE_CENTER);

        layout.setComponentAlignment(panel, Alignment.MIDDLE_CENTER);
        setContent(layout);

        // Create the content
        FormLayout content = new FormLayout();
        content.addStyleName("mypanelcontent");
        content.addComponent(new TextField("Participant"));
        content.addComponent(new TextField("Organization"));
        content.setSizeUndefined(); // Shrink to fit
        content.setMargin(true);
        panel.setContent(content);
        // END-EXAMPLE: layout.panel.complex

    }
}

