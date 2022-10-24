package com.example.crud.services;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.crud.entities.Pessoa;
import com.example.crud.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository pessoaRepo;

	//Create
	public Pessoa salvarPessoa(Pessoa objP) {
		Pessoa novaPessoa = pessoaRepo.save(objP);
		return novaPessoa;
	}
	
	//Read
	public List<Pessoa> buscarTodos() {
		return pessoaRepo.findAll();
	}

	//Read
	public Optional<Pessoa> buscarUm(Long id) {
		Optional<Pessoa> pessoaEncontrada = pessoaRepo.findById(id);
		return pessoaEncontrada;
	}
	
	//Update
	public Pessoa atualizarPessoa(Long id, Pessoa novosDados) {
		try {
			Pessoa pessoaParaAtualizar = pessoaRepo.getOne(id);
			updateBanco(pessoaParaAtualizar, novosDados);

			return pessoaRepo.save(pessoaParaAtualizar);

		} catch (EntityNotFoundException e) {
			throw new EntityNotFoundException(e.getMessage());
		}
	}
	
	//Delete
	public void deletarPessoa(Long id) {
		Pessoa pessoaParaDeletar = pessoaRepo.getOne(id);
		pessoaRepo.delete(pessoaParaDeletar);
	}

	//MetodoAuxiliar
	private void updateBanco(Pessoa pessoaParaAtualizar, Pessoa objP) {
		pessoaParaAtualizar.setNome(objP.getNome());
		pessoaParaAtualizar.setEmail(objP.getEmail());
		pessoaParaAtualizar.setIdade(objP.getIdade());
	}

}
