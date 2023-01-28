package br.com.alura.forum.controller;

import br.com.alura.forum.controller.dto.TopicoDetalhadoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizaForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.service.TopicosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import javax.validation.Valid;


@RestController
@RequestMapping("/topicos")
public class TopicosController {
	
	@Autowired
	private TopicosService topicosService;


	@GetMapping
	@Cacheable(value = "listaDeTopicos")
	public Page<TopicoDto> listar(@RequestParam(required = false) String nomeCurso, @PageableDefault(sort = "id", direction = Sort.Direction.DESC, size = 3) Pageable pagina) {
		return topicosService.listarTopicos(nomeCurso, pagina);
	}

	@PostMapping
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> cadastrar(@RequestBody @Valid TopicoForm form, UriComponentsBuilder uriBuilder) {
		return topicosService.cadastrarTopico(form, uriBuilder);
	}

	@GetMapping("/{id}")
	public ResponseEntity<TopicoDetalhadoDto> detalhar(@PathVariable Long id){
		return topicosService.detalharTopico(id);
	}

	@PutMapping("/{id}")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<TopicoDto> atualizar(@PathVariable Long id, @RequestBody AtualizaForm form){
	return topicosService.atualizarTopico(id, form);
	}

	@DeleteMapping("/{id}")
	@CacheEvict(value = "listaDeTopicos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		return topicosService.removerTopico(id);
	}
}
