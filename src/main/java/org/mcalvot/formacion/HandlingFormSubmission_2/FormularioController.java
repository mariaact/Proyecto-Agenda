package org.mcalvot.formacion.HandlingFormSubmission_2;

        import org.slf4j.Logger;
        import org.slf4j.LoggerFactory;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.stereotype.Controller;
        import org.springframework.ui.Model;
        import org.springframework.web.bind.annotation.*;

@Controller
public class FormularioController {

    private static final Logger log =  LoggerFactory.getLogger(FormularioController.class);

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/formulario")
    public String greetingForm(Model model) {
        model.addAttribute("form", new Formulario());

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


    //eliminar un usuario
    @GetMapping("user/borrar/{id}")
    public @ResponseBody String deleteUser (@PathVariable("id") Integer id) {
        //Formulario form  = userRepository.findAllById(id);
        //model.addAttribute("borrarEl", userRepository.findById(id));
        userRepository.deleteById(id);
        log.info("Invocado eliminar usuario con id"+id);

        //return "<a href=\"/info\">VOLVER</a>";
        return "Usuario Eliminado";
    }

    /*@GetMapping(path="/user/modificar")
    public @ResponseBody String updateUser(){

     /* Formulario form = userRepository.findAllById(id);
      model.addAttribute("UserUpdate", form);

      formu.setId(form.getId());
      formu.setNombre(form.getNombre());
      formu.setApellido(form.getApellido());
      userRepository.save(formu);

      return "modificaruser";
    }*/

    @GetMapping("/update")
    public String update() {

        return "update";
    }

    @GetMapping("/user/modificar/{id}")
    public String userGetUpdate(Model modelo) {
        modelo.addAttribute("userUpdateGET", new Formulario());
       // log.info("UsuarioUpdate: --> " + form.getId() + form.getApellido() + form.getNombre());
        log.info("Estoy en el metodo GET ");
        return "update";
    }


    @PostMapping("/user/modificar/{id}")
    public String userPostUpdate(@ModelAttribute Formulario form,Model modelo) {
        //Formulario form = new Formulario();
        log.info("Estoy en el metodo POST");
        modelo.addAttribute("userUpdatePOST", form);
        log.info("UsuarioUpdatePOST: --> " + form.getId() + form.getApellido() + form.getNombre());
        Formulario userUpdate = userRepository.findAllById(form.getId());
        userUpdate.setNombre(form.getNombre());
        userUpdate.setApellido(form.getApellido());
        userRepository.save(userUpdate);

        return "User Modificado";
    }



}
