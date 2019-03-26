package org.mcalvot.formacion.HandlingFormSubmission_2;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;
        import org.thymeleaf.context.WebContext;

        import java.awt.*;
        import java.util.ArrayList;
        import java.util.List;

@Controller
public class FormularioController {

    private static final Logger log =  LoggerFactory.getLogger(FormularioController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/formulario")
    public String greetingForm(Model model) {
        model.addAttribute("form", new Formulario());
        //List<Formulario> allUser = (List<Formulario>) userRepository.findAll();

        return "formulario";
    }

    @PostMapping("/formulario")
    public String greetingSubmit(@ModelAttribute Formulario form, Model model) {
        model.addAttribute("Formulario", form);
        log.info("Usuario: --> " + form.getApellido() + form.getNombre() + form.getId());
        Formulario formul = new Formulario();
        formul.setNombre(form.getNombre());
        formul.setApellido(form.getApellido());
        userRepository.save(formul);
        return "info";
    }

    @RequestMapping("/users")
    public String listaUsers(Model model) {
        model.addAttribute("listaUsers", userRepository.findAll());
        return "info";
    }

}
