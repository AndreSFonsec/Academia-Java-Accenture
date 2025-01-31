package com.accenture.consumo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.accenture.consumo.interfaces.CepService;
import com.accenture.consumo.model.Endereco;
import com.accenture.consumo.repository.EnderecoRepository;

@RestController
public class CepRestController {

	@Autowired
	private CepService cepService;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	@GetMapping("/{cep}")
    public ResponseEntity<Endereco> getCep(@PathVariable String cep) {

        // Consomindo a API externa
        Endereco endereco = cepService.buscaEnderecoPorCep(cep);

        if (endereco != null)
        {
            // Salvando no banco de dados H2
            enderecoRepository.save(endereco);
            return ResponseEntity.ok().body(endereco);
        } else
        {
            return ResponseEntity.notFound().build();
        }
    }
}
