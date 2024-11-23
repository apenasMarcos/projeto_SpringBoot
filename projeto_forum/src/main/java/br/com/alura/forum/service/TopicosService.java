package br.com.alura.forum.service;

import br.com.alura.forum.controller.dto.TopicoDetalhadoDto;
import br.com.alura.forum.controller.dto.TopicoDto;
import br.com.alura.forum.controller.form.AtualizaForm;
import br.com.alura.forum.controller.form.TopicoForm;
import br.com.alura.forum.modelo.Topico;
import br.com.alura.forum.repository.CursoRepository;
import br.com.alura.forum.repository.TopicoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import java.net.URI;
import java.util.Optional;

@Service
public class TopicosService {

    private final TopicoRepository topicoRepository;

    private final CursoRepository cursoRepository;

    public TopicosService(TopicoRepository topicoRepository, CursoRepository cursoRepository) {
        this.topicoRepository = topicoRepository;
        this.cursoRepository = cursoRepository;
    }

    public Page<TopicoDto> listarTopicos(String nomeCurso, Pageable pagina) {

        Page<Topico> topicos;
        if (nomeCurso == null || nomeCurso.isEmpty()) {
            topicos = topicoRepository.findAll(pagina);
        } else {
            topicos = topicoRepository.findByCursoNome(nomeCurso, pagina);
        }
        return TopicoDto.converter(topicos);
    }

    @Transactional
    public ResponseEntity<TopicoDto> cadastrarTopico(TopicoForm form, UriComponentsBuilder uriBuilder) {
        Topico topico = form.converter(cursoRepository);
        topicoRepository.save(topico);

        URI uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new TopicoDto(topico));
    }

    public ResponseEntity<TopicoDetalhadoDto> detalharTopico(Long id) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        return topicoOptional.map(topico -> ResponseEntity.ok(new TopicoDetalhadoDto(topico))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<TopicoDto> atualizarTopico(Long id, AtualizaForm form) {
        Optional<Topico> topicoOptional = topicoRepository.findById(id);
        Topico topicoAtualizar = form.atualizar(id, topicoRepository);
        return topicoOptional.map(topico -> ResponseEntity.ok(new TopicoDto(topicoAtualizar))).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<?> removerTopico(Long id) {
        Optional<Topico> optional = topicoRepository.findById(id);
        if (optional.isPresent()) {
            topicoRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }
}
