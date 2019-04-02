package org.mcalvot.formacion.HandlingFormSubmission_2;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<FormularioUsuario, Integer> {


    FormularioUsuario findAllById(Integer i);

}
