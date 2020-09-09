package br.com.jonataslaet.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.jonataslaet.controller.dto.LancamentoDto;
import br.com.jonataslaet.model.Lancamento;
import br.com.jonataslaet.repository.LancamentoRepository;

@Service
public class LancamentoService {

	@Autowired
	LancamentoRepository lr;

	public ResponseEntity<List<LancamentoDto>> listar() {
		List<LancamentoDto> lancamentosDto = LancamentoDto.lancamentosDto(lr.findAll());
		return ResponseEntity.ok(lancamentosDto);
	}

	public ResponseEntity<LancamentoDto> buscarLancamento(Long codigo) {
		Optional<Lancamento> lancamento = lr.findById(codigo);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		LancamentoDto lancamentoDto = new LancamentoDto(lancamento.get());
		return ResponseEntity.ok().body(lancamentoDto);
	}

	public ResponseEntity<?> deletarLancamento(Long codigo) {
		Optional<Lancamento> lancamento = lr.findById(codigo);
		if (!lancamento.isPresent()) {
			return ResponseEntity.notFound().build();
		}
		lr.deleteById(codigo);
		return ResponseEntity.noContent().build();
	}
}
