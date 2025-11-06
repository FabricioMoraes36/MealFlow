package com.dev.MealFood.Services;

import com.dev.MealFood.Enums.PratoCategoria;
import com.dev.MealFood.Exceptions.PratoNaoEncontradoException;
import com.dev.MealFood.Models.Prato;
import com.dev.MealFood.Models.PratoIngrediente;
import com.dev.MealFood.Models.PratoIngredienteId;
import com.dev.MealFood.Repositories.PratoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class PratoService {

    private final PratoRepository pratoRepository;

    public PratoService(PratoRepository pratoRepository) {
        this.pratoRepository = pratoRepository;
    }

    public Prato createPrato(Prato prato) {
        if (Objects.isNull(prato)) {
            throw new RuntimeException("Prato invalido");
        }
        if (prato.getCategoria() == PratoCategoria.PRINCIPAL) {
            if (prato.getPratoIngredientes() == null || prato.getPratoIngredientes().size() < 3) {
                throw new RuntimeException("Prato principal deve ter pelo menos 3 ingredientes");
            }
        }

        // Garantir relacionamento bidirecional e preencher id parcialmente (ingredienteId)
        attachPratoToPratoIngredientes(prato);

        return pratoRepository.save(prato);
    }

    public Prato findPratoByName(String nome) {
        Prato prato = pratoRepository.findPratoByNome(nome);
        if(prato == null){
            throw new PratoNaoEncontradoException(nome);
        }
        return prato;
    }

    public Prato findPratoById(Long id) {
        return pratoRepository.findById(id).orElseThrow(()-> new RuntimeException("prato nao encontrado"));
    }

    public Prato updatePratoByName(String nome, Prato pratoNovo){
        Prato prato = pratoRepository.findPratoByNome(nome);
        if (prato != null){
            prato.setNome(pratoNovo.getNome());
            prato.setPreco(pratoNovo.getPreco());
            prato.setCategoria(pratoNovo.getCategoria());
            prato.setPratoIngredientes(pratoNovo.getPratoIngredientes());
            // garantir bidirecionalidade e ids parciais
            attachPratoToPratoIngredientes(prato);
            return pratoRepository.save(prato);
        }
        throw new RuntimeException("Prato nao encontrado");
    }

    public List<Prato> findAll() {
        return pratoRepository.findAll();
    }
    public void deletarPrato(Long id){
        pratoRepository.deleteById(id);
    }
    public List<Prato> findByIngredientName(String nome) {
        return pratoRepository.findPratosWithIngredientName(nome);
    }

    public List<Prato> findWithoutIngredientName(String nome) {
        return pratoRepository.findPratosWithoutIngredientName(nome);
    }

    // helper para garantir que cada PratoIngrediente aponte para o Prato e que o id.embedded tenha ingredientId
    private void attachPratoToPratoIngredientes(Prato prato) {
        if (prato.getPratoIngredientes() == null) return;
        for (PratoIngrediente pi : prato.getPratoIngredientes()) {
            // assegurar referência ao prato (necessário para @MapsId funcionar)
            pi.setPrato(prato);
            // garantir que o embedded id exista e contenha o ingredienteId
            if (pi.getId() == null) {
                Long ingredienteId = (pi.getIngrediente() != null) ? pi.getIngrediente().getId() : null;
                pi.setId(new PratoIngredienteId(null, ingredienteId));
            } else if (pi.getId().getIngredienteId() == null && pi.getIngrediente() != null) {
                pi.getId().setIngredienteId(pi.getIngrediente().getId());
            }
        }
    }

}
