package org.mcalvot.formacion.HandlingFormSubmission_2;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<Formulario, Integer> {


    Formulario findAllById(int i);

}
