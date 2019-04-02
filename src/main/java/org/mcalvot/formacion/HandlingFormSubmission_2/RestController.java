package org.mcalvot.formacion.HandlingFormSubmission_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@org.springframework.web.bind.annotation.RestController
public class RestController {

    private static final Logger log =  LoggerFactory.getLogger(FormularioController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiciosAcciones acc;

    //alta de usuario
    @PutMapping("rest/formulario/alta")
    public @ResponseBody FormularioUsuario altaUsuario (@RequestBody FormularioUsuario form){

        /*FormularioUsuario newUser = new FormularioUsuario();
        newUser.setNombre(form.getNombre());
        newUser.setApellido(form.getApellido());*/

        return acc.altaUsuario(form);
    }


    //Eliminar un usuario
    @DeleteMapping("/rest/user/borrar/{id}")
    public @ResponseBody Boolean deleteUser (@PathVariable("id") Integer id){

        /*if(userRepository.findById(id) == null){
            return false;
        }
        userRepository.deleteById(id);
        return true;*/
        return acc.borrar(id);
    }


    //editar un usuario
    @PutMapping("/rest/user/detalles/{id}")
    public @ResponseBody FormularioUsuario editarUsuario
            (@PathVariable("id") Integer id, @RequestBody FormularioUsuario form){
        //obtengo los datos del usuario
        FormularioUsuario userEdit = detalleUser(id);
        /*userEdit.setNombre(form.getNombre());
        userEdit.setApellido(form.getApellido());*/
        return acc.editarUsuario(form,userEdit);

    }

    //Detalles de un usuario
    @GetMapping("/rest/user/detalles/{id}")
    public FormularioUsuario detalleUser(@PathVariable("id") Integer id){
       /* FormularioUsuario form = userRepository.findAllById(id);
        if(userRepository.findById(id).get() ==  null){
            return null;
        }
        return userRepository.findById(id).get();*/
       return acc.detallesUsuario(id);
    }

    //lista de todos los usuarios
    @GetMapping("/rest/listaUsuarios")
    public List<FormularioUsuario>  userAll() {
       /* List<FormularioUsuario> listaUser = (List<FormularioUsuario>) userRepository.findAll();
        return listaUser;*/

       return acc.userAll();
    }



}
