package org.mcalvot.formacion.HandlingFormSubmission_2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServiciosAcciones {

    private static final Logger log =  LoggerFactory.getLogger(ServiciosAcciones.class);

    @Autowired
    private UserRepository userRepository;

    //alta de usuario
    public FormularioUsuario altaUsuario(FormularioUsuario form){

        FormularioUsuario formul = new FormularioUsuario();
        formul.setNombre(form.getNombre());
        formul.setApellido(form.getApellido());
        return formul;
    }

    public boolean borrar(Integer id){

        if(userRepository.findById(id) == null){
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    //Editar usuario
    public FormularioUsuario editarUsuario (FormularioUsuario form,
                                            FormularioUsuario userEdit){

        //obtengo los datos del usuario
        userEdit.setNombre(form.getNombre());
        userEdit.setApellido(form.getApellido());

        return userEdit;

    }

    //Datos de un usuario
    public FormularioUsuario detallesUsuario(Integer id){

        log.info("id en el metodo detallesUsuario --> " + id);

        log.info("DetallesUsuarios usuario que tenga como id el " + id +" es --> "
                + userRepository.findById(id).get());



        if(id == null){
            return null;
        }
        //FormularioUsuario form = userRepository.findById(id).get();
        if(userRepository.findById(id).get() ==  null){
            return null;
        }

        return userRepository.findById(id).get();
    }

    //lista de todos los usuarios
    public List<FormularioUsuario> userAll() {

        List<FormularioUsuario> listaUser = (List<FormularioUsuario>) userRepository.findAll();
        return listaUser;
    }




}
