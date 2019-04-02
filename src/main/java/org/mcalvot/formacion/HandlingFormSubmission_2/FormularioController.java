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

    Integer IdUserModificar;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiciosAcciones acc;

    //creo un usuario nuevo
    @GetMapping("/formulario")
    public String greetingForm(Model model) {
        model.addAttribute("form", new FormularioUsuario());
        return "formulario";
    }

    //introduzco todos los valores del nuevo usuario y las guardo en la bbdd
    @PostMapping("/formulario")
    public String greetingSubmit(@ModelAttribute FormularioUsuario form, Model model) {
        model.addAttribute("FormularioUsuario", form);
        log.info("Usuario: --> " + form.getApellido() + form.getNombre() + form.getId());
       /* FormularioUsuario formul = new FormularioUsuario();
        formul.setNombre(form.getNombre());
        formul.setApellido(form.getApellido());*/
        userRepository.save(acc.altaUsuario(form));
        model.addAttribute("listaUsers", userRepository.findAll());
        return "/enlacesInfoVolver";
    }

    /*-------------------------------------------------------------------------------------*/

    @GetMapping("/mostrarDatos")
    public String datosTodosUsuarios(Model model) {
        model.addAttribute("listaUsers", userRepository.findAll());
        return "/info";
    }

    /*-------------------------------------------------------------------------------------*/

    /*@GetMapping(path="/usuario/all")
    public @ResponseBody Iterable<FormularioUsuario> getAllUsers() {
        return userRepository.findAll();
    }*/


    //eliminar un usuario
    @GetMapping("user/borrar/{id}")
    public @ResponseBody String deleteUser (@PathVariable("id") Integer id) {
        //FormularioUsuario form  = userRepository.findAllById(id);
        //model.addAttribute("borrarEl", userRepository.findById(id));
        userRepository.deleteById(id);
        log.info("Invocado eliminar usuario con id"+id);

        return "usuarioeliminado";
    }

    /*@GetMapping("/update")
    public String update() {

        return "update";
    }*/

    @GetMapping("/user/modificar/{id}")
    public String userGetUpdate(Model modelo, @PathVariable("id") Integer id) {
        FormularioUsuario form = new FormularioUsuario();
        form.setId(id);
        modelo.addAttribute("userUpdateGET", form);
        modelo.addAttribute("id", id);
       // log.info("UsuarioUpdate: --> " + form.getId() + form.getApellido() + form.getNombre());
        log.info("GET ACTUALIZAR --> " +  id);
        IdUserModificar = id;
        log.info("Estoy en el metodo GET ");
        return "/update";
    }


    @PostMapping("/user/modificar")
    public String userPostUpdate(@ModelAttribute FormularioUsuario form, Model modelo) {
        //FormularioUsuario form = new FormularioUsuario();
        modelo.addAttribute("userUpdatePUT", form);
        log.info("Estoy en el metodo PUT");
        form.setId(IdUserModificar);
        log.info("UsuarioUpdatePUT: --> " + form.getId() + form.getApellido() + form.getNombre());
        FormularioUsuario userUpdate = acc.detallesUsuario(form.getId());
        //FormularioUsuario userUpdate = userRepository.findById(form.getId()).get();
        log.info("usuario que se quiere actualizar --> " + userUpdate.getNombre() + userUpdate.getApellido() + userUpdate.getId());
        /*userUpdate.setNombre(form.getNombre());
        userUpdate.setApellido(form.getApellido());*/
        userRepository.save(acc.editarUsuario(form, userUpdate));//userUpdate

        //return "redirect:/info";
        return "/enlacesInfoVolver";
    }

    //detalles de un usuario
    @GetMapping("/user/detalles/{id}")
    public String detalleUser(@PathVariable("id") Integer id, Model mode){
        mode.addAttribute("datosUser", userRepository.findAllById(id));

        return "detalleUser";
    }




}
