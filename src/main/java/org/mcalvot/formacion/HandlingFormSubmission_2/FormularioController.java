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
        import java.util.Optional;

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
        model.addAttribute("listaUsers", userRepository.findAll());
        return "info";
    }

    @GetMapping(path="/usuario/all")
    public @ResponseBody Iterable<Formulario> getAllUsers() {
        return userRepository.findAll();
    }


//@RequestMapping(method=RequestMethod.DELETE, value="{idOferta}")

    //eliminar un usuario
    @GetMapping(path="/user/borrar/{id}")
    public @ResponseBody String deleteUser (@PathVariable("id") Integer id) {

        //model.addAttribute("borrarEl", userRepository.findById(id));
        userRepository.deleteById(id);
        log.info("Invocado eliminar usuario con id"+id);

        return "userdelete";
    }

    @GetMapping(path="/user/modificar/{id}")
    public @ResponseBody String updateUser(@PathVariable("id") Integer id, Model model, Formulario formu){

      Formulario form = userRepository.findAllById(id);
      model.addAttribute("UserUpdate", form);

      form.setId(formu.getId());
      form.setNombre(formu.getNombre());
      form.setApellido(formu.getApellido());
      userRepository.save(form);

      return "update";
    }


}
